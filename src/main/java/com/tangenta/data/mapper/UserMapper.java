package com.tangenta.data.mapper;

import com.tangenta.data.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    @ResultMap("baseResultMap")
    List<User> getAllUsers();

    @Select("select * from user where username = #{username}")
    @ResultMap("baseResultMap")
    User findByUserName(String username);

}
