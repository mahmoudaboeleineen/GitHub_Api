package com.example.maboe.github_api.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.maboe.github_api.Adapter.RepoAdapter;
import com.example.maboe.github_api.R;
import com.example.maboe.github_api.api.ApiService;
import com.example.maboe.github_api.api.Client;
import com.example.maboe.github_api.model.GitHubRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesActivity extends AppCompatActivity {
    List<GitHubRepo> repos = new ArrayList<>();
    String login;
    RecyclerView repoRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        getSupportActionBar().setTitle("User's Repos");
        initialiseReposViews();
        login = Objects.requireNonNull(getIntent().getExtras()).getString("login");
        loadRepos();
    }

    public void initialiseReposViews() {
        repoRecyclerView = findViewById(R.id.repo_recyclerView);
        repoRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void loadRepos() {
        ApiService apiService =
                Client.getClient().create(ApiService.class);
        Call<List<GitHubRepo>> call = apiService.getRepo(login);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                repos = response.body();
                repoRecyclerView.setAdapter(new RepoAdapter(repos, getApplicationContext()));
                repoRecyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {

            }
        });


    }
}
