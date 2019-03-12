package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.QuestionSolution;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionSolutionMapper {
    @Select("select * from question_solution where question_id = #{questionId}")
    List<QuestionSolution> getByQuestionId(@Param("questionId") Long questionId);

    @Insert("insert into question_solution value (#{questionId}, #{solution})")
    void createQuestionSolution(@Param("questionId") Long questionId, @Param("solution") String solution);
}
