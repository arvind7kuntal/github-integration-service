package com.example.githubintegration.event;

public class RepoFetchEvent {

    private String eventType;
    private int repoCount;
    private String language;
    private String sort;
    private String timestamp;

    // REQUIRED for Jackson
    public RepoFetchEvent() {
    }

    public RepoFetchEvent(String eventType, int repoCount, String language, String sort, String timestamp) {
        this.eventType = eventType;
        this.repoCount = repoCount;
        this.language = language;
        this.sort = sort;
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public int getRepoCount() {
        return repoCount;
    }

    public String getLanguage() {
        return language;
    }

    public String getSort() {
        return sort;
    }

    public String getTimestamp() {
        return timestamp;
    }
}