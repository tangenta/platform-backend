package com.tangenta.repositories;

import com.tangenta.data.pojo.mybatis.FriendRelationship;

import java.util.List;

public interface FriendListRepository {
    void addFriend(Long subjectId, Long objectId);
    List<FriendRelationship> following(Long studentId);
    List<FriendRelationship> followers(Long studentId);

    boolean isFriendEachOther(Long aStudentId, Long anotherStudentId);
    boolean isFriend(Long studentId, Long friendId);

    void unFriend(Long studentId, Long friendId);
}
