package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.MComment;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestCommentRepository implements CommentRepository {
    private static List<MComment> allComments = new LinkedList<MComment>() {{
        add(new MComment(1L, 1L, 1L, "I am the king of singer yo", new Date()));
        add(new MComment(2L, 2L, 1L, "I don't think so", new Date()));
        add(new MComment(3L, 3L, 1L, "What are you talking about?", new Date()));
        add(new MComment(4L, 1L, 2L, "Will you marry me?",
                new GregorianCalendar(2020, Calendar.APRIL, 13).getTime()));
        add(new MComment(5L, 1L, 3L, "Just give me a reason, just a little bit enough",
                new GregorianCalendar(2020, Calendar.APRIL, 14).getTime()));
        add(new MComment(6L, 1L, 4L, "Oh, look what you made me dooo",
                new GregorianCalendar(2020, Calendar.APRIL, 13).getTime()));
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
