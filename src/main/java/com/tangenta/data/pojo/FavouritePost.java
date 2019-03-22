package com.tangenta.data.pojo;

public class FavouritePost {
    private Long studentId;
    private Long postId;

    public FavouritePost() {
    }

    public FavouritePost(Long studentId, Long postId) {
        this.studentId = studentId;
        this.postId = postId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getPostId() {
        return postId;
    }
}
