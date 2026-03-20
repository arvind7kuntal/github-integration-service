package com.example.githubintegration.service;

import com.example.githubintegration.client.GithubClient;
import com.example.githubintegration.event.RepoFetchEvent;
import com.example.githubintegration.kafka.RepoEventProducer;
import com.example.githubintegration.model.GithubRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class GithubService {


    private final RepoEventProducer producer;
    private static final Logger log = LoggerFactory.getLogger(GithubService.class);
    private final GithubClient githubClient;
    public GithubService(GithubClient githubClient,RepoEventProducer producer)
    {
        this.githubClient=githubClient;
        this.producer=producer;
    }

    @Cacheable(value = "repos")
    public List<GithubRepo> getRepositories(String language, String sort,int page, int size) {

        log.info("Fetching repositories from GitHub API");
        List<Map<String, Object>> response =
                githubClient.fetchRepositories();
        log.info("Received {} repositories from GitHub", response.size());

        List<GithubRepo> repos = new ArrayList<>();

        log.info("Applying language filter: {}", language);
        for (Map<String, Object> repo : response) {

            String name = (String) repo.get("name");
            String htmlUrl = (String) repo.get("html_url");
            String repoLanguage = (String) repo.get("language");
            Integer stars = (Integer) repo.get("stargazers_count");

            GithubRepo githubRepo = new GithubRepo(
                    name,
                    htmlUrl,
                    repoLanguage,
                    stars != null ? stars : 0
            );

            // LANGUAGE FILTER
            if (language == null ||
                    (repoLanguage != null && repoLanguage.equalsIgnoreCase(language))) {


                repos.add(githubRepo);
            }
        }

        // SORTING
        if (sort != null) {

            log.info("Sorting repositories by: {}", sort);
            if (sort.equalsIgnoreCase("stars")) {
                repos.sort(Comparator.comparingInt(GithubRepo::getStars).reversed());
            }

            if (sort.equalsIgnoreCase("name")) {
                repos.sort(Comparator.comparing(GithubRepo::getName));
            }
        }


        // PAGINATION
        int start = page * size;
        int end = Math.min(start + size, repos.size());
        log.info("Applying pagination page={} size={}", page, size);
        if (start > repos.size()) {
            return new ArrayList<>();
        }

        List<GithubRepo> result = repos.subList(start, end);

        log.info("Returning {} repositories after processing", result.size());

/*
    Create Kafka Event
*/
        RepoFetchEvent event = new RepoFetchEvent(
                "REPO_FETCH",
                result.size(),
                language,
                sort,
                java.time.LocalDateTime.now().toString()
        );

/*
    Send event to Kafka
*/
        producer.sendRepoFetchEvent(event);

        log.info("Kafka event sent for repo fetch");

        return result;
    }
}