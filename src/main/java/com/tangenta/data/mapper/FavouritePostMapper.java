package com.tangenta.data.mapper;

import com.tangenta.data.pojo.FavouritePost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavouritePostMapper {
    List<FavouritePost> findByUser(@Param("studentId") Long studentId);
}
