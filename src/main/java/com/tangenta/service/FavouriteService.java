package com.tangenta.service;

import com.tangenta.data.pojo.graphql.Post;
import com.tangenta.repositories.FavouriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouriteService {
    private FavouriteRepository favouriteRepository;
    private PostService postService;

    public FavouriteService(FavouriteRepository favouriteRepository, PostService postService) {
        this.favouriteRepository = favouriteRepository;
        this.postService = postService;
    }

    public List<Post> favouritePosts(Long studentId) {
        return favouriteRepository.favouritePosts(studentId).stream()
                .map(fp -> postService.viewPost((fp.getPostId())))
                .collect(Collectors.toList());
    }

    public void addFavouritePost(Long studentId, Long postId) {
        favouriteRepository.addFavouritePost(studentId, postId);
    }

    public void deleteFavouritePost(Long studentId, Long postId) {
        favouriteRepository.deleteFavouritePost(studentId, postId);
    }
}
