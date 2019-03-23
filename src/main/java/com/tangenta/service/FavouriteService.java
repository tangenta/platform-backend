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
    private PagingService pagingService;

    public FavouriteService(FavouriteRepository favouriteRepository, PostService postService, PagingService pagingService) {
        this.favouriteRepository = favouriteRepository;
        this.postService = postService;
        this.pagingService = pagingService;
    }

    public List<Post> favouritePosts(Long studentId, int number, int from) {
        return pagingService.paging(favouriteRepository.favouritePosts(studentId), number, from)
                .stream()
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
