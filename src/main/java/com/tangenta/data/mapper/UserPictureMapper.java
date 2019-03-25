package com.tangenta.data.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserPictureMapper {
    @Select("select picture from picture where student_id = #{studentId}")
    String getFilename(@Param("studentId") Long studentId);

    @Insert("insert into picture value (#{studentId}, #{blob})")
    void set(@Param("studentId") Long studentId, @Param("blob") String filename);

    @Update("update picture set picture = #{blob} where student_id = #{studentId}")
    void update(@Param("studentId") Long studentId, @Param("blob") String filename);
}
