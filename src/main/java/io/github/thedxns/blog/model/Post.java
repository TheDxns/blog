package io.github.thedxns.blog.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 200)
    @NotBlank(message = "The post title must not be empty")
    private String title;

    @Column(length = 10000)
    private String sneakPeak;

    @NotBlank(message = "The content of the post must not be empty")
    @Column(length = 100000)
    private String content;

    @NotBlank(message = "The category of the post must not be empty")
    private String category;

    @NotBlank(message = "The ID of the creator of the post must be set")
    private String creatorId;

    @NotBlank(message = "The username of the creator of the post must be set")
    private String creatorUsername;

    @NotNull(message = "Missing 'featured' field")
    private boolean featured;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public Post() {
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSneakPeak() {
        return sneakPeak;
    }

    public void setSneakPeak(String sneakPeak) {
        this.sneakPeak = sneakPeak;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public boolean getFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public void updateFrom(final Post source) {
        content = source.content;
        sneakPeak = source.sneakPeak;
        category = source.category;
        creatorId = source.creatorId;
        creatorUsername = source.creatorUsername;
        featured = source.featured;
    }

    @PrePersist
    void prePersist() {
        this.setCreatedOn(LocalDateTime.now());
    }

    @PreUpdate
    void preUpdate() {
        this.setUpdatedOn(LocalDateTime.now());
    }
}

