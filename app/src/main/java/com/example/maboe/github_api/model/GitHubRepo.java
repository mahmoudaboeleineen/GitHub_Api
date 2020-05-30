package com.example.maboe.github_api.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class GitHubRepo {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("html_url")
    private String repoUrl;

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("language")
    private String language;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("stargazers_count")
    @Expose
    private String stargazersCount;

    @SerializedName("watchers_count")
    @Expose
    private String watchersCount;

    @SerializedName("forks_count")
    @Expose
    private String forksCount;

    public String getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(String stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getForksCount() {
        return forksCount;
    }

    public void setForksCount(String forksCount) {
        this.forksCount = forksCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
