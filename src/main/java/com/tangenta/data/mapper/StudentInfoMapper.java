package com.tangenta.data.mapper;

import com.tangenta.data.pojo.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentInfoMapper {
    void set(@Param("i") StudentInfo studentInfo);
    void create(@Param("i") StudentInfo studentInfo);
    StudentInfo get(@Param("studentId") Long studentId);
}
