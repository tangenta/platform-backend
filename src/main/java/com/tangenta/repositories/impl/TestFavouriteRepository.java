package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.FavouritePost;
import com.tangenta.repositories.FavouriteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestFavouriteRepository implements FavouriteRepository {
    private static List<FavouritePost> allFavPosts = new ArrayList<FavouritePost>() {{
        add(new FavouritePost(2017000001L, 1L));
        add(new FavouritePost(2017000001L, 2L));
        add(new FavouritePost(2017000001L, 3L));
    }};

    @Override
    public List<FavouritePost> favouritePosts(Long studentId) {
        return allFavPosts.stream()
                .filter(fp -> fp.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public void addFavouritePost(Long studentId, Long postId) {
        allFavPosts.add(new FavouritePost(studentId, postId));
    }

    @Override
    public void deleteFavouritePost(Long studentId, Long postId) {
        Iterator<FavouritePost> iter = allFavPosts.iterator();
        while (iter.hasNext()) {
            FavouritePost fp = iter.next();
            if (fp.getStudentId().equals(studentId) && fp.getPostId().equals(postId)) {
                iter.remove();
                break;
            }
        }
    }
}
