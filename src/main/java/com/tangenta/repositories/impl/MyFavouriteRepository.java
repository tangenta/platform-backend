package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.FavouritePostMapper;
import com.tangenta.data.pojo.FavouritePost;
import com.tangenta.repositories.FavouriteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("dev")
public class MyFavouriteRepository implements FavouriteRepository {
    private FavouritePostMapper favouritePostMapper;

    public MyFavouriteRepository(FavouritePostMapper favouritePostMapper) {
        this.favouritePostMapper = favouritePostMapper;
    }

    @Override
    public List<FavouritePost> favouritePosts(Long studentId) {
        return favouritePostMapper.findByUser(studentId);
    }
}
