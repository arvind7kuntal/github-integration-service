package com.example.githubintegration.client;


import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class GithubClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> fetchRepositories() {

        String url = "https://api.github.com/orgs/spring-projects/repos";

        return restTemplate.getForObject(url, List.class);
    }
}
