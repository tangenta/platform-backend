package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.FriendListMapper;
import com.tangenta.data.pojo.mybatis.FriendRelationship;
import com.tangenta.repositories.FriendListRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("dev")
public class MyFriendListRepository implements FriendListRepository {
    private FriendListMapper friendListMapper;

    public MyFriendListRepository(FriendListMapper friendListMapper) {
        this.friendListMapper = friendListMapper;
    }

    @Override
    public void addFriend(Long subjectId, Long objectId) {
        friendListMapper.addFriend(subjectId, objectId);
    }

    @Override
    public List<FriendRelationship> following(Long studentId) {
        return friendListMapper.findByStudentId(studentId);
    }

    @Override
    public List<FriendRelationship> followers(Long studentId) {
        return friendListMapper.findByObjectId(studentId);
    }

    @Override
    public boolean isFriendEachOther(Long aStudentId, Long anotherStudentId) {
        return friendListMapper.friendEachOther(aStudentId, anotherStudentId).size() == 2;
    }

    @Override
    public boolean isFriend(Long studentId, Long friendId) {
        return friendListMapper.findRelationById(studentId, friendId) != null;
    }

    @Override
    public void unFriend(Long studentId, Long friendId) {
        friendListMapper.unFriend(studentId, friendId);
    }
}
