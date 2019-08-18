package com.example.maboe.github_api.api;

import com.example.maboe.github_api.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/search/users?q=language:java+location:cairo")
    Call<ItemResponse> getItems();
}
