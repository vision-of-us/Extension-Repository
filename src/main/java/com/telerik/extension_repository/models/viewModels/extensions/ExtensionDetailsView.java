package com.telerik.extension_repository.models.viewModels.extensions;

import com.telerik.extension_repository.models.viewModels.tags.TagView;

import java.util.Date;
import java.util.Set;

public class ExtensionDetailsView {

    private Long id;

    private String name;

    private String description;

    private Long number_of_downloads;

    private Set<TagView> tags;

    private String owner;

    private Long version;

    private String download_link;

    private String source_repository_link;

    private Integer number_of_open_issues;

    private Integer number_of_pull_request;

    private Date last_commit_date;

    private boolean isFeatured;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<TagView> getTags() {
        return tags;
    }

    public void setTags(Set<TagView> tags) {
        this.tags = tags;
    }

    public Long getNumber_of_downloads() {
        return number_of_downloads;
    }

    public void setNumber_of_downloads(Long number_of_downloads) {
        this.number_of_downloads = number_of_downloads;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDownload_link() {
        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public String getSource_repository_link() {
        return source_repository_link;
    }

    public void setSource_repository_link(String source_repository_link) {
        this.source_repository_link = source_repository_link;
    }

    public Integer getNumber_of_open_issues() {
        return number_of_open_issues;
    }

    public void setNumber_of_open_issues(Integer number_of_open_issues) {
        this.number_of_open_issues = number_of_open_issues;
    }

    public Integer getNumber_of_pull_request() {
        return number_of_pull_request;
    }

    public void setNumber_of_pull_request(Integer number_of_pull_request) {
        this.number_of_pull_request = number_of_pull_request;
    }

    public Date getLast_commit_date() {
        return last_commit_date;
    }

    public void setLast_commit_date(Date last_commit_date) {
        this.last_commit_date = last_commit_date;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }
}
