package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.UserPictureMapper;
import com.tangenta.repositories.PictureRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
public class MyPictureRepository implements PictureRepository {
    private UserPictureMapper userPictureMapper;

    public MyPictureRepository(UserPictureMapper userPictureMapper) {
        this.userPictureMapper = userPictureMapper;
    }

    @Override
    public byte[] getUserPicture(Long studentId) {
        return userPictureMapper.get(studentId);
    }

    @Override
    public void setUserPicture(Long studentId, byte[] blob) {
        if (userPictureMapper.get(studentId) == null) {
            userPictureMapper.set(studentId, blob);
        } else {
            userPictureMapper.update(studentId, blob);
        }
    }
}
