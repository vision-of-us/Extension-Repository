package com.telerik.extension_repository.entities;

import javax.persistence.*;


@Entity
@Table(name="git_hub_data")
public class GitHubData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "gitHubData") //TODO -> where is used with name  ..info
    private Extension extension;

    @Column(name = "num_of_pulls")
    private String pullsCount;

    @Column(name = "num_of_issues")
    private String issuesCount;

    @Column(name = "latest_commit")
    private String lastCommit;

    public GitHubData(){

    }

    public GitHubData(Extension extension, String pullsCount, String issuesCount, String lastCommit) {
        this.extension = extension;
        this.pullsCount = pullsCount;
        this.issuesCount = issuesCount;
        this.lastCommit = lastCommit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Extension getParent() {
        return extension;
    }

    public void setParent(Extension extension) {
        this.extension = extension;
    }

    public String getPullsCount() {
        return pullsCount;
    }

    public void setPullsCount(String pullCount) {
        this.pullsCount = pullCount;
    }

    public String getIssuesCount() {
        return issuesCount;
    }

    public void setIssuesCount(String issuesCount) {
        this.issuesCount = issuesCount;
    }

    public String getLastCommit() {
        return lastCommit;
    }

    public void setLastCommit(String lastCommit) {
        this.lastCommit = lastCommit;
    }
}
