package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.MComment;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where post_id = #{postId}")
    @ResultMap("baseResultMap")
    List<MComment> findByPostId(@Param("postId") Long postId);

    @Select("select * from comment where comment_id = #{commentId}")
    @ResultMap("baseResultMap")
    MComment findByCommentId(@Param("commentId") Long commentId);

    @Insert("insert into comment(student_id, content, post_id, creation_time) " +
            "value (#{studentId}, #{content}, #{postId}, #{creationTime})")
    void addComment(@Param("studentId") Long studentId,
                    @Param("postId") Long postId,
                    @Param("content") String content,
                    @Param("creationTime") Date creationTime);

    @Delete("delete from comment where comment_id = #{commentID}")
    void deleteComment(@Param("commentId") Long commentId);

}
