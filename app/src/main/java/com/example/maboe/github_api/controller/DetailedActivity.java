package com.example.maboe.github_api.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ShareCompat;
import androidx.appcompat.app.AppCompatActivity;


import android.text.util.Linkify;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maboe.github_api.R;
import com.example.maboe.github_api.api.ApiService;
import com.example.maboe.github_api.api.Client;
import com.example.maboe.github_api.model.GitHubUser;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
    TextView gitHubLink;
    TextView name;
    TextView followers;
    TextView following;
    TextView bio;
    TextView location;
    TextView repoNumber;
    TextView gistsNumber;
    TextView blog;
    CircularImageView avatar;
    String login;
    ImageView options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        avatar = findViewById(R.id.details_userImage);
        name = findViewById(R.id.details_userName);
        followers = findViewById(R.id.details_userFollowers);
        following = findViewById(R.id.details_userFollowing);
        bio = findViewById(R.id.details_userBio);
        location = findViewById(R.id.details_userLocation);
        repoNumber = findViewById(R.id.details_userRepo);
        gistsNumber = findViewById(R.id.details_userGists);
        blog = findViewById(R.id.details_userBlog);
        gitHubLink = findViewById(R.id.details_userGitHub);
        options = findViewById(R.id.options_menu);

        login = Objects.requireNonNull(getIntent().getExtras()).getString("login");
        loadUserData();

    }

    public void onSettingsClicked(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.more_options, popup.getMenu());
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_user_repositories:
                Intent repoIntent = new Intent(this, RepositoriesActivity.class);
                repoIntent.putExtra("login", login);
                this.startActivity(repoIntent);
                Toast.makeText(this, "Repo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share_user_account:
                String username = Objects.requireNonNull(getIntent().getExtras()).getString("login");
                String link = getIntent().getExtras().getString("html_url");
                Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                        .setType("text/plain")
                        .setText("Check out this awesome developer @" + username + ", " + link)
                        .getIntent();
                this.startActivity(shareIntent);
                return true;
            default:
                return false;
        }
    }

    public void loadUserData() {
        ApiService apiService =
                Client.getClient().create(ApiService.class);
        Call<GitHubUser> call = apiService.getUser(login);
        call.enqueue(new Callback<GitHubUser>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                String avatarUrl = Objects.requireNonNull(getIntent().getExtras()).getString("avatar_url");

                Picasso.with(DetailedActivity.this)
                        .load(avatarUrl)
                        .placeholder(R.drawable.loading)
                        .into(avatar);

                assert response.body() != null;
                name.setText(response.body().getName());
                location.setText(response.body().getLocation());
                followers.setText(response.body().getFollowers());
                following.setText(response.body().getFollowing());
                gitHubLink.setText(response.body().getHtmlUrl());
                Linkify.addLinks(gitHubLink, Linkify.WEB_URLS);
                if (response.body().getBio() == null || response.body().getBio().isEmpty()
                        || Objects.equals(response.body().getBio(), "null")) {
                    bio.setText("Ops Don't Have a Bio");
                } else {
                    bio.setText(response.body().getBio());
                    if (response.body().getBio() != null) {
                    }
                }
                if (response.body().getBlog().isEmpty()) {
                    blog.setText("Ops Don't Have a Blog");
                } else {
                    blog.setText(response.body().getBlog());
                    Linkify.addLinks(blog, Linkify.WEB_URLS);

                }
                repoNumber.setText(response.body().getPublicRepos());
                gistsNumber.setText(response.body().getPublicGists());
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });
    }
}