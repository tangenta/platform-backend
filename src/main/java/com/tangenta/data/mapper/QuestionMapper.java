package com.tangenta.data.mapper;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from question")
    @ResultMap("baseResultMap")
    List<MQuestion> getAllQuestions();

    List<MQuestion> getQuestionsByClassAndType(
            @Param("classification") QuestionClassification classification,
            @Param("type") QuestionType type);

    MQuestion getById(@Param("id") Long questionId);
}
