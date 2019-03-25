package com.tangenta.repositories.impl;

import com.tangenta.repositories.PictureRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestPictureRepository implements PictureRepository {
    private class UserPicture {
        Long studentId;
        byte[] picture;

        UserPicture(Long studentId, byte[] picture) {
            this.studentId = studentId;
            this.picture = picture;
        }
    }
    private static List<UserPicture> allPictures = new LinkedList<>();

    @Override
    public byte[] getUserPicture(Long studentId) {
        return allPictures.stream()
                .filter(p -> p.studentId.equals(studentId))
                .map(p -> p.picture)
                .findFirst().orElse(null);
    }

    @Override
    public void setUserPicture(Long studentId, byte[] blob) {
        Iterator<UserPicture> iter = allPictures.iterator();
        while (iter.hasNext()) {
            UserPicture up = iter.next();
            if (up.studentId.equals(studentId)) {
                iter.remove();
                break;
            }
        }
        allPictures.add(new UserPicture(studentId, blob));
    }
}
