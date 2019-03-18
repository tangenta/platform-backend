package com.tangenta.data.pojo.mybatis;

public class FriendRelationship {
    private Long studentId;
    private Long friendId;

    public FriendRelationship() {
    }

    public FriendRelationship(Long studentId, Long friendId) {
        this.studentId = studentId;
        this.friendId = friendId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getFriendId() {
        return friendId;
    }
}
