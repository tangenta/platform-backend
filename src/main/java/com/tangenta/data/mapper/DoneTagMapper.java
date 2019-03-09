package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.DoneTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface DoneTagMapper {

    DoneTag findByStudentAndQuestion(@Param("studentId") Long studentId,
                                     @Param("questionId") Long questionId);

    void insert(@Param("studentId") Long studentId,
                @Param("questionId") Long questionId, @Param("doneDate") Date doneDate);

}
