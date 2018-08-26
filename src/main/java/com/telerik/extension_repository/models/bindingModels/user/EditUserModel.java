package com.telerik.extension_repository.models.bindingModels.user;

public class EditUserModel {
    private Integer id;
    private String username;
    private Boolean isEnabled;
    private String[] authorities;

    public Integer getId() {
        return id;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
}
