package com.tangenta.data.pojo;

public class Post {
    private Long postId;
    private String publishTime;
    private String content;
    private Long viewNumber;
    private Long replyNumber;
    private Long studentId;
    private String title;

    public Post() {}

    public Post(Long postId, String publishTime, String content,
                Long viewNumber, Long replyNumber, Long studentId, String title) {
        this.postId = postId;
        this.publishTime = publishTime;
        this.content = content;
        this.viewNumber = viewNumber;
        this.replyNumber = replyNumber;
        this.studentId = studentId;
        this.title = title;
    }

    public Long getPostId() {
        return postId;
    }

    public String getPublishTime() {
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

    public Long getStudentId() {
        return studentId;
    }

    public String getTitle() {
        return title;
    }
}
