//package com.telerik.extension_repository.entities;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.Set;
//
//@Entity
//@Table(name = "extensions")
//public class Extension {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String name;
//
//    private String description;
//
//    private String version;
//
//    private int number_of_downloads;
//
//    private String source_repository_link;
//
//    private String download_link;
//
//    private int number_of_open_issues;
//
//    private int number_of_pull_request;
//
//    private Date last_commit_date;
//
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    private User owner;
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "customers_extensions",
//            joinColumns =  {@JoinColumn(name = "extension_id")} ,
//            inverseJoinColumns =  {@JoinColumn(name = "user_id")})
//    private Set<User>  customers;
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//    public int getNumber_of_downloads() {
//        return number_of_downloads;
//    }
//
//    public void setNumber_of_downloads(int number_of_downloads) {
//        this.number_of_downloads = number_of_downloads;
//    }
//
//    public String getSource_repository_link() {
//        return source_repository_link;
//    }
//
//    public void setSource_repository_link(String source_repository_link) {
//        this.source_repository_link = source_repository_link;
//    }
//
//    public String getDownload_link() {
//        return download_link;
//    }
//
//    public void setDownload_link(String download_link) {
//        this.download_link = download_link;
//    }
//
//    public int getNumber_of_open_issues() {
//        return number_of_open_issues;
//    }
//
//    public void setNumber_of_open_issues(int number_of_open_issues) {
//        this.number_of_open_issues = number_of_open_issues;
//    }
//
//    public int getNumber_of_pull_request() {
//        return number_of_pull_request;
//    }
//
//    public void setNumber_of_pull_request(int number_of_pull_request) {
//        this.number_of_pull_request = number_of_pull_request;
//    }
//
//    public Date getLast_commit_date() {
//        return last_commit_date;
//    }
//
//    public void setLast_commit_date(Date last_commit_date) {
//        this.last_commit_date = last_commit_date;
//    }
//
//    public User getOwner() {
//        return owner;
//    }
//
//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
//
//    public Set<User> getCustomers() {
//        return customers;
//    }
//
//    public void setCustomers(Set<User> customers) {
//        this.customers = customers;
//    }
//}