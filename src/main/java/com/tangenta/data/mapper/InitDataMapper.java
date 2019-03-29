package com.tangenta.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface InitDataMapper {

    @Select("select max(question_id) from question")
    Long getMaxQuestionId();

    @Select("select max(student_id - #{currentYear} * floor(pow(10, #{paddingDigitNum}))) as max_student_id " +
            "from user where floor(student_id / pow(10, #{paddingDigitNum})) = #{currentYear}")
    Long getMaxStudentId(@Param("currentYear") int currentYear, @Param("paddingDigitNum") int paddingDigitNum);
}
