package com.telerik.extension_repository.models.bindingModels.extensions;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AddExtensionModel {
    private String name;
    private String description;

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
}
