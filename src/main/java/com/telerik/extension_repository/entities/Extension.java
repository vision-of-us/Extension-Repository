package com.telerik.extension_repository.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "extensions")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private String version;

    private int number_of_downloads;

    private String source_repository_link;

    private String download_link;

    private int number_of_open_issues;

    private int number_of_pull_request;

    private Date last_commit_date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "customers_extensions",
            joinColumns =  {@JoinColumn(name = "extension_id")} ,
            inverseJoinColumns =  {@JoinColumn(name = "user_id")})
    private Set<User>  customers;



}