package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.FavouritePost;
import com.tangenta.repositories.FavouriteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestFavouriteRepository implements FavouriteRepository {
    private static List<FavouritePost> allFavPosts = new ArrayList<FavouritePost>() {{
        add(new FavouritePost(1L, 1L));
        add(new FavouritePost(1L, 2L));
        add(new FavouritePost(1L, 3L));
    }};

    @Override
    public List<FavouritePost> favouritePosts(Long studentId) {
        return allFavPosts.stream()
                .filter(fp -> fp.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }
}
