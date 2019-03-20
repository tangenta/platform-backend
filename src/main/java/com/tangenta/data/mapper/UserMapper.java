package com.tangenta.data.mapper;

import com.tangenta.data.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    @ResultMap("baseResultMap")
    List<User> getAllUsers();

    @Select("select * from user where student_id = #{studentId}")
    @ResultMap("baseResultMap")
    User findById(Long studentId);

    @Select("select * from user where username = #{username}")
    @ResultMap("baseResultMap")
    User findByUserName(String username);

    @Select("select * from user where username = #{email}")
    @ResultMap("baseResultMap")
    User findByEmail(String email);

    void createUser(@Param("username") String username,
                    @Param("password") String password,
                    @Param("email") String email,
                    @Param("creationDate") String creationDate);

    @Update("update user set username = #{username}, password = #{password}, email = #{email} " +
            "where student_id = #{studentId}")
    void updateUser(Long studentId, String username, String password, String email);
}
