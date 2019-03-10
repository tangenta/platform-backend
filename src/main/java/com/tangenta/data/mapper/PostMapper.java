package com.tangenta.data.mapper;

import com.tangenta.data.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("select * from post")
    @ResultMap("baseResultMap")
    List<Post> getAllPosts();

    void createPost(@Param("p") Post post);
}
