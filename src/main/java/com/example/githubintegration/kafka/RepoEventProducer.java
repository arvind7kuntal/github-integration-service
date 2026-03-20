package com.example.githubintegration.kafka;

import com.example.githubintegration.event.RepoFetchEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RepoEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RepoEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRepoFetchEvent(RepoFetchEvent event) {

        try {

            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send("repo-events", json);

            System.out.println("EVENT SENT:");
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}