package com.telerik.extension_repository.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {
    private String id;

    private String authority;

    private Set<User> users;

    public Authority() {
        this.users = new HashSet<>();
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public String getId() {
        return id;
    }

//    @Transient
//    public String getSimpleName(){
//        return StringUtils.capitalize(this.getName().substring(5).toLowerCase());
//    }
    @Override
    public String getAuthority() {
        return authority;
    }

    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
