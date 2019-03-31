package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.MComment;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestCommentRepository implements CommentRepository {
    private static Logger logger = LoggerFactory.getLogger(TestCommentRepository.class);
    private static List<MComment> allComments = new LinkedList<MComment>() {{

    }};
    private static Long currentMaxId = 3L;

    @Override
    public void addComment(Long studentId, Long postId, String content, Date creationDate) {
        allComments.add(new MComment(currentMaxId++, studentId, postId, content, creationDate));
    }

    @Override
    public List<MComment> showComments(Long postId) {
        return allComments.stream()
                .filter(p -> p.getPostId().equals(postId))
                .collect(Collectors.toList());
    }

    @Override
    public MComment findById(Long commentId) {
        return allComments.stream()
                .filter(m -> m.getCommentId().equals(commentId))
                .findFirst().orElse(null);
    }

    @Override
    public List<MComment> showUserComments(Long studentId) {
        return allComments.stream()
                .filter(m -> m.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long commentId) {
        Iterator<MComment> i = allComments.iterator();
        while (i.hasNext()) {
            MComment mc = i.next();
            if (mc.getCommentId().equals(commentId)) {
                i.remove();
                break;
            }
        }
        assert false;
    }

}
