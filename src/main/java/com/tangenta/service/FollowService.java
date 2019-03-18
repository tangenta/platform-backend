package com.tangenta.service;

import com.tangenta.data.pojo.User;
import com.tangenta.data.pojo.mybatis.FriendRelationship;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.FriendListRepository;
import com.tangenta.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {
    private FriendListRepository friendListRepository;
    private UserRepository userRepository;

    public FollowService(FriendListRepository friendListRepository, UserRepository userRepository) {
        this.friendListRepository = friendListRepository;
        this.userRepository = userRepository;
    }


    public void follow(Long subjectId, Long objectId) {
        if (subjectId.equals(objectId)) throw new BusinessException("无法关注自己");
        if (friendListRepository.isFriend(subjectId, objectId)) throw new BusinessException("已经关注");
        friendListRepository.addFriend(subjectId, objectId);
    }

    public void unFollow(Long subjectId, Long objectId) {
        if (!friendListRepository.isFriend(subjectId, objectId)) throw new BusinessException("尚未关注");
        friendListRepository.unFriend(subjectId, objectId);
    }

    public List<User> following(Long studentId) {
        return friendListRepository.following(studentId).stream()
                .map(FriendRelationship::getFriendId)
                .map(userRepository::findById)
                .collect(Collectors.toList());
    }

    public List<User> followers(Long studentId) {
        return friendListRepository.followers(studentId).stream()
                .map(FriendRelationship::getStudentId)
                .map(userRepository::findById)
                .collect(Collectors.toList());
    }
}
