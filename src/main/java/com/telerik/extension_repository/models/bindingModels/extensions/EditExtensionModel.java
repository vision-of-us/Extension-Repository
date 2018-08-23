package com.telerik.extension_repository.models.bindingModels.extensions;

import com.telerik.extension_repository.models.viewModels.tags.TagView;

import java.util.Set;

public class EditExtensionModel {
    private Long id;

    private String name;

    private String description;

    private String link;

    private byte[] file;

    private Set<TagView> tags;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Set<TagView> getTags() {
        return tags;
    }

    public void setTags(Set<TagView> tags) {
        this.tags = tags;
    }
}
