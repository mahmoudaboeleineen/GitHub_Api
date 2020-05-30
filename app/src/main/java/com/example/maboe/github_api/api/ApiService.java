package com.example.maboe.github_api.api;

import com.example.maboe.github_api.model.GitHubRepo;
import com.example.maboe.github_api.model.GitHubUser;
import com.example.maboe.github_api.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/search/users?q=language:java+location:cairo")
    Call<ItemResponse> getItems();

    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);
}
