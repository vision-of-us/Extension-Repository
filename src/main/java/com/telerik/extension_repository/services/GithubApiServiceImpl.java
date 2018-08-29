package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.Date;

public class GithubApiServiceImpl implements GithubApiService {

    private static final String GITHUB_URL = "https://github.com/";   //TODO -> put in Constants

    @Override
    public GitHub getGHConnection() throws IOException {
        return GitHub.connectUsingOAuth("4d3a0f046ede5e3a9ab2e2f3b133e66129f0104b");
    }

    @Override
    public void updateGithubData(GitHubData data) throws IOException {
        String fullUrl = data.getExtension().getSource_repository_link();
        String url = fullUrl.substring(GITHUB_URL.length());

        data.setIssuesCount(getOpenIssues(url));
        data.setLastCommit(getCommitDate(url));
        data.setPullsCount(getPullsCount(url));
    }

    @Override
    public String getOpenIssues(String url) throws IOException {
        GitHub git = getGHConnection();
        return Integer.toString(git.getRepository(url).getOpenIssueCount());
    }

    @Override
    public Date getCommitDate(String url) throws IOException {
        GitHub git = getGHConnection();
//        return null;
        return git.getRepository(url).getUpdatedAt();
     }

    @Override
    public String getPullsCount(String url) throws IOException {
        GitHub git = getGHConnection();
        return Integer.toString(git.getRepository(url).getPullRequests(GHIssueState.ALL).size());
    }
}
