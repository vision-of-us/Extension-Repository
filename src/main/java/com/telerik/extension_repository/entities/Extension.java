package com.telerik.extension_repository.entities;

import com.telerik.extension_repository.entities.enums.Status;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "extensions")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String version;

    private int number_of_downloads;

    private String source_repository_link;

    private String download_link;

//    private int number_of_open_issues;
//
//    private int number_of_pull_request;
//
//    private Date last_commit_date;

    private boolean isFeatured;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private byte[] file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "git_hub_data_id")
    private GitHubData gitHubData;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tags_extensions",
            joinColumns =  {@JoinColumn(name = "extension_id")} ,
            inverseJoinColumns =  {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags;

    public Long getId() {
        return id;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getNumber_of_downloads() {
        return number_of_downloads;
    }

    public void setNumber_of_downloads(int number_of_downloads) {
        this.number_of_downloads = number_of_downloads;
    }

    public String getSource_repository_link() {
        return source_repository_link;
    }

    public void setSource_repository_link(String source_repository_link) {
        this.source_repository_link = source_repository_link;
    }

    public String getDownload_link() {
        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public GitHubData getGitHubData() {
        return gitHubData;
    }

    public void setGitHubData(GitHubData gitHubData) {
        this.gitHubData = gitHubData;
    }
}