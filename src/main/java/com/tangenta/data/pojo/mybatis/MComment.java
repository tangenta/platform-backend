package com.tangenta.data.pojo.mybatis;

import java.util.Date;

public class MComment {
    private Long commentId;
    private Long studentId;
    private Long postId;
    private String content;
    private Date creationTime;

    public MComment() {}

    public MComment(Long commentId, Long studentId, Long postId,
                    String content, Date creationTime) {
        this.commentId = commentId;
        this.studentId = studentId;
        this.postId = postId;
        this.content = content;
        this.creationTime = creationTime;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
