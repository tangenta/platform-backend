package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.FriendRelationship;
import org.apache.ibatis.annotations.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Mapper
public interface FriendListMapper {

    @Select("select * from friendlist where student_id = #{studentId}")
    @ResultMap("baseResultMap")
    List<FriendRelationship> findByStudentId(Long studentId);

    @Select("select * from friendlist where friend_id = #{objectId}")
    @ResultMap("baseResultMap")
    List<FriendRelationship> findByObjectId(Long objectId);

    @Insert("insert into friendlist value (#{studentId}, #{friendId})")
    void addFriend(@Param("studentId") Long studentId, @Param("friendId") Long friendId);

    @Select("select * from friendlist where (student_id = #{studentA} and friend_id = #{studentB}) " +
            "or (student_id = #{studentB} and friend_id = #{studentA})")
    @ResultMap("baseResultMap")
    List<FriendRelationship> friendEachOther(Long studentA, Long studentB);

    @Select("select * from friendlist where student_id = #{studentId} and friend_id = #{friendId}")
    @ResultMap("baseResultMap")
    FriendRelationship findRelationById(@Param("studentId") Long studentId, @Param("friendId") Long friendId);

    @Delete("delete from friendlist where student_id = #{studentId} and friend_id = #{friendId}")
    void unFriend(@Param("studentId") Long studentId, @Param("friendId") Long friendId);
}
