package io.github.thedxns.blog.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "The content of the comment must not be empty")
    @Column(length = 100000)
    private String content;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    private String creator;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public Comment() {
    }

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void updateFrom(final Comment source) {
        content = source.content;
        post = source.post;
        creator = source.creator;
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