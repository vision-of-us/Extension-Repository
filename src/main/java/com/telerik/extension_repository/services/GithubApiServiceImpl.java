package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class GithubApiServiceImpl implements GithubApiService {

    private static final String GITHUB_URL = "https://github.com/";   //TODO -> put in Constants

    @Override
    public GitHub getGHConnection() throws IOException {
//        return GitHub.connectUsingOAuth("4d3a0f046ede5e3a9ab2e2f3b133e66129f0104b");
        return GitHub.connectUsingOAuth("5c1a77eec3047ae6b562a55a7c0e4d4735cb38ef");
    }

    @Override
    public GitHubData updateGithubData(String fullUrl) throws IOException {
        String url = fullUrl.substring(GITHUB_URL.length());
        GitHubData gitHubData = new GitHubData();
        gitHubData.setIssuesCount(getOpenIssues(url));
        gitHubData.setLastCommit(getCommitDate(url));
        gitHubData.setPullsCount(getPullsCount(url));
        return gitHubData;
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
