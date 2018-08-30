package com.telerik.extension_repository.models.viewModels.extensions;

import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.viewModels.tags.TagView;

import java.util.Date;
import java.util.Set;

public class ExtensionDetailsView {

    private Long id;

    private String name;

    private String description;

    private Long number_of_downloads;

    private String tagString;

    //private Set<TagView> tags;

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

    private LoggedUser owner;

    private Status status;

    private Long version;

    private String download_link;

    private String source_repository_link;

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

    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

//    public Set<TagView> getTags() {
//        return tags;
//    }
//
//    public void setTags(Set<TagView> tags) {
//        this.tags = tags;
//    }

    public Long getNumber_of_downloads() {
        return number_of_downloads;
    }

    public void setNumber_of_downloads(Long number_of_downloads) {
        this.number_of_downloads = number_of_downloads;
    }

    public LoggedUser getOwner() {
        return owner;
    }

    public void setOwner(LoggedUser owner) {
        this.owner = owner;
    }

    public String getSource_repository_link() {
        return source_repository_link;
    }

    public void setSource_repository_link(String source_repository_link) {
        this.source_repository_link = source_repository_link;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }
}
