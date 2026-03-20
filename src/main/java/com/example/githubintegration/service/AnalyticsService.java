package com.example.githubintegration.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyticsService {

    private int totalRequests = 0;

    private final Map<String, Integer> languageStats = new HashMap<>();
    private final Map<String, Integer> sortStats = new HashMap<>();

    public void recordEvent(String language, String sort) {

        totalRequests++;

        if (language != null) {
            languageStats.put(language,
                    languageStats.getOrDefault(language, 0) + 1);
        }

        if (sort != null) {
            sortStats.put(sort,
                    sortStats.getOrDefault(sort, 0) + 1);
        }

        printAnalytics();
    }

    private void printAnalytics() {

        System.out.println("\n========= ANALYTICS =========");

        System.out.println("Total API Requests : " + totalRequests);

        System.out.println("Language Usage : " + languageStats);

        System.out.println("Sort Usage : " + sortStats);

        System.out.println("=============================\n");
    }
}