package com.tangenta.service;

import com.tangenta.data.pojo.graphql.Comment;
import com.tangenta.data.pojo.mybatis.MComment;
import com.tangenta.repositories.CommentRepository;
import com.tangenta.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PagingService pagingService;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PagingService pagingService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.pagingService = pagingService;
    }

    public MComment findCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public void addComment(Long studentId, Long postId, String content) {
        commentRepository.addComment(studentId, postId, content, new Date());
    }

    public List<Comment> showComments(Long postId, int number, int from) {
        List<MComment> result = pagingService.paging(commentRepository.showComments(postId),
                number, from);
        return mapMCommentToComment(result);
    }

    public List<Comment> showUserComments(Long studentId, int number, int from) {
        List<MComment> result = pagingService.paging(commentRepository.showUserComments(studentId),
                number, from);
        return mapMCommentToComment(result);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private List<Comment> mapMCommentToComment(List<MComment> source) {
        return source.stream()
                .map(mc -> new Comment(mc.getCommentId(), mc.getPostId(),
                        userRepository.findById(mc.getStudentId()).getUsername(),
                        mc.getContent(), mc.getCreationTime()))
                .sorted(Comparator.comparing(Comment::getCreationTime))
                .collect(Collectors.toList());
    }
}
