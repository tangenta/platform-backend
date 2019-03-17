package com.tangenta.data.pojo.graphql;

import com.tangenta.data.pojo.User;

import java.util.Date;

public class Post {
    private Long postId;
    private Date publishTime;
    private String content;
    private Long viewNumber;
    private Long replyNumber;
    private User user;
    private String title;

    public Post(Long postId, Date publishTime, String content,
                Long viewNumber, Long replyNumber, User user, String title) {
        this.postId = postId;
        this.publishTime = publishTime;
        this.content = content;
        this.viewNumber = viewNumber;
        this.replyNumber = replyNumber;
        this.user = user;
        this.title = title;
    }

    public Long getPostId() {
        return postId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public String getContent() {
        return content;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public Long getReplyNumber() {
        return replyNumber;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }
}
