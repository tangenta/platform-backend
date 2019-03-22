package com.tangenta.repositories;

import com.tangenta.data.pojo.FavouritePost;

import java.util.List;

public interface FavouriteRepository {
    List<FavouritePost> favouritePosts(Long studentId);
    void addFavouritePost(Long studentId, Long postId);
    void deleteFavouritePost(Long studentId, Long postId);
}
