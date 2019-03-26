package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.MPost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("select * from post")
    @ResultMap("baseResultMap")
    List<MPost> getAllPosts();

    void createPost(@Param("p") MPost MPost);

    @Select("select * from post where post_id = #{postId}")
    @ResultMap("baseResultMap")
    MPost findById(Long postId);

    @Select("select * from post where Student_id = #{studentId}")
    @ResultMap("baseResultMap")
    List<MPost> findByStudentId(Long studentId);

    @Delete("delete from post where post_id = #{postId}")
    void deleteById(@Param("postId") long postId);

    @Update("update post set title = #{title}, content = #{content} where post_id = #{postId}")
    void update(@Param("postId") Long postId,
                @Param("title") String title,
                @Param("content") String content);

    void increaseViewNumber(Long postId);

    void increaseReplyNumber(Long postId);
}
