package com.tangenta.data.mapper;

import com.tangenta.types.User;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    List<User> getAllUsers();
}
