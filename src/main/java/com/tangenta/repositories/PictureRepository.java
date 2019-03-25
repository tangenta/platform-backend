package com.tangenta.repositories;

public interface PictureRepository {
    String getUserPicture(Long studentId);
    void setUserPicture(Long studentId, String filename);
}
