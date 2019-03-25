package com.tangenta.repositories;

public interface PictureRepository {
    byte[] getUserPicture(Long studentId);
    void setUserPicture(Long studentId, byte[] blob);
}
