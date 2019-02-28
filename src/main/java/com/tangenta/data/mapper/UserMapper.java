package com.tangenta.data.mapper;

import com.tangenta.data.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    @Results(value = {
            @Result(property = "studentId", column = "student_id", id = true),
            @Result(property = "creationDate", column = "creation_time"),
    })
    List<User> getAllUsers();
//
//    @Select("select * from user where student_id = #{id}")
//    @Results(
//            @Result(property = "studentId", column = "student_id", id = true)
//    )
//    User getUserById(@Param("id") Long id);
}
