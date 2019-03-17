package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.MComment;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestCommentRepository implements CommentRepository {
    private static List<MComment> allComments = new LinkedList<MComment>() {{
        add(new MComment(1L, 1L, 1L, "I am the king of dance yo", new Date()));
        add(new MComment(2L, 2L, 1L, "I don't think so", new Date()));
        add(new MComment(3L, 3L, 1L, "What are you talking about?", new Date()));
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
