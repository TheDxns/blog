package io.github.thedxns.blog.pojo;

public class CommentContent
{
    private String text;
    private int postId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}