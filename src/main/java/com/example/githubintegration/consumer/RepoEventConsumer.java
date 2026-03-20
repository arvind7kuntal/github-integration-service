package com.example.githubintegration.consumer;

import com.example.githubintegration.event.RepoFetchEvent;
import com.example.githubintegration.service.AnalyticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RepoEventConsumer {

    private final AnalyticsService analyticsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RepoEventConsumer(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @KafkaListener(topics = "repo-events", groupId = "github-service")
    public void consume(String message) {

        try {

            RepoFetchEvent event =
                    objectMapper.readValue(message, RepoFetchEvent.class);

            System.out.println("\nEVENT RECEIVED:");
            System.out.println(event);

            analyticsService.recordEvent(
                    event.getLanguage(),
                    event.getSort()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}