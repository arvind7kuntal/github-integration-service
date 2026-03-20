package com.example.githubintegration.model;

public class GithubRepo {
    private String name;
    private String url;
    private String language;
    private int stars;


public GithubRepo(String name,String url, String language,int stars)
{
    this.name=name;
    this.url=url;
    this.language=language;
    this.stars=stars;
}

public String getName()
    {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguage() {
        return language;
    }

    public int getStars() {
        return stars;
    }

}


