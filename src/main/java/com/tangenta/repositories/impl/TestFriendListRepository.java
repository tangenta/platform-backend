package com.tangenta.repositories.impl;

import com.google.j2objc.annotations.ObjectiveCName;
import com.tangenta.data.pojo.mybatis.FriendRelationship;
import com.tangenta.repositories.FriendListRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestFriendListRepository implements FriendListRepository {
    private static List<FriendRelationship> allFriendRelationships = new ArrayList<FriendRelationship>() {{
        add(new FriendRelationship(1L, 2L));
        add(new FriendRelationship(1L, 3L));
    }};

    @Override
    public void addFriend(Long subjectId, Long objectId) {
        allFriendRelationships.add(new FriendRelationship(subjectId, objectId));
    }

    @Override
    public List<FriendRelationship> following(Long studentId) {
        return allFriendRelationships.stream()
                .filter(fr -> fr.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendRelationship> followers(Long studentId) {
        return allFriendRelationships.stream()
                .filter(fr -> fr.getFriendId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFriendEachOther(Long aStudentId, Long anotherStudentId) {
        return isFriend(aStudentId, anotherStudentId) && isFriend(anotherStudentId, aStudentId);
    }

    @Override
    public boolean isFriend(Long studentId, Long friendId) {
        return allFriendRelationships.stream()
                .anyMatch(fr -> fr.getStudentId().equals(studentId) && fr.getFriendId().equals(friendId));
    }

    @Override
    public void unFriend(Long studentId, Long friendId) {
        Iterator<FriendRelationship> i = allFriendRelationships.iterator();
        while (i.hasNext()) {
            FriendRelationship fr = i.next();
            if (fr.getFriendId().equals(friendId) && fr.getStudentId().equals(studentId)) {
                i.remove();
                break;
            }
        }
    }
}
