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

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User creator;

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
