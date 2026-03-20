package com.example.githubintegration.controller;

import com.example.githubintegration.model.GithubRepo;
import com.example.githubintegration.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repos")
    public List<GithubRepo> getRepos(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return githubService.getRepositories(language, sort,page ,size);
    }
}