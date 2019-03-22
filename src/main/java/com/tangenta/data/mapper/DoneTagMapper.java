package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.data.pojo.mybatis.DoneTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
public interface DoneTagMapper {

    DoneTag findByStudentAndQuestion(@Param("studentId") Long studentId,
                                     @Param("questionId") Long questionId);

    void insert(@Param("studentId") Long studentId,
                @Param("questionId") Long questionId, @Param("doneDate") LocalDate doneDate);


    List<AnswerCountDatePair> countAndGroupByDate(Long studentId);
}
