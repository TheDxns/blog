package io.github.thedxns.blog.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "The creator of the post must be set")
    private String creator;

    private int likeNum;

    private int dislikeNum;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public Post() {
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

    public String getCreator() {
        return creator;
    }

    public void setCreatorId(String creator) {
        this.creator = creator;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getDislikeNum() {
        return likeNum;
    }

    public void setDislikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public void updateFrom(final Post source) {
        content = source.content;
        sneakPeak = source.sneakPeak;
        category = source.category;
        creator = source.creator;
        likeNum = source.likeNum;
        dislikeNum = source.dislikeNum;
    }

    @PrePersist
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
