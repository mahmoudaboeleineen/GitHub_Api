package com.example.maboe.github_api.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maboe.github_api.R;
import com.example.maboe.github_api.model.GitHubRepo;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<GitHubRepo> repoList;
    private Context context;


    public RepoAdapter(List<GitHubRepo> repoList, Context context) {
        this.repoList = repoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.repoName.setText(repoList.get(position).getName());
        holder.repoDescription.setText(repoList.get(position).getDescription());
        holder.repoLanguage.setText(repoList.get(position).getLanguage());
        holder.repoDate.setText(repoList.get(position).getCreatedAt());
        holder.repoForks.setText(repoList.get(position).getForksCount());
        holder.repoStar.setText(repoList.get(position).getStargazersCount());
        holder.repoWatcher.setText(repoList.get(position).getWatchersCount());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView repoName;
        TextView repoDescription;
        TextView repoLanguage;
        TextView repoDate;
        TextView repoForks;
        TextView repoStar;
        TextView repoWatcher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repo_name);
            repoDescription = itemView.findViewById(R.id.repo_description);
            repoLanguage = itemView.findViewById(R.id.repo_language);
            repoDate = itemView.findViewById(R.id.repo_createdDate);
            repoForks = itemView.findViewById(R.id.repo_forks);
            repoStar = itemView.findViewById(R.id.repo_star);
            repoWatcher = itemView.findViewById(R.id.repo_watch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        GitHubRepo clickedDataItem = repoList.get(pos);
                        Intent repoUrlIntent = new Intent(Intent.ACTION_VIEW);
                        repoUrlIntent.putExtra(Intent.EXTRA_STREAM, clickedDataItem.getRepoUrl());
                        repoUrlIntent.setData(Uri.parse(clickedDataItem.getRepoUrl()));
                        repoUrlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(repoUrlIntent);
                    }
                }
            });
        }
    }
}


