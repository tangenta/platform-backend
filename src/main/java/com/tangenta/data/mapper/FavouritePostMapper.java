package com.tangenta.data.mapper;

import com.tangenta.data.pojo.FavouritePost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavouritePostMapper {
    List<FavouritePost> findByUser(@Param("studentId") Long studentId);

    void add(@Param("studentId") Long studentId, @Param("postId") Long postId);

    void delete(@Param("studentId") Long studentId, @Param("postId") Long postId);
}
