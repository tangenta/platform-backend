package com.tangenta.data.pojo.graphql;

import java.util.Date;

public class Comment {
    private Long commentId;
    private Long postId;
    private String username;
    private String content;
    private Date creationTime;

    public Comment(Long commentId, Long postId, String username, String content, Date creationTime) {
        this.commentId = commentId;
        this.postId = postId;
        this.username = username;
        this.content = content;
        this.creationTime = creationTime;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
