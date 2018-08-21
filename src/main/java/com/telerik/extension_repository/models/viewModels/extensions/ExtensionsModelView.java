package com.telerik.extension_repository.models.viewModels.extensions;

public class ExtensionsModelView {
    private Long id;

    private String name;

    private String description;

    private String number_of_downloads;

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

    public String getNumber_of_downloads() {
        return number_of_downloads;
    }

    public void setNumber_of_downloads(String number_of_downloads) {
        this.number_of_downloads = number_of_downloads;
    }
}
