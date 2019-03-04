package com.tangenta.data.mapper;

import com.tangenta.data.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from question")
    @ResultMap("baseResultMap")
    List<Question> getAllQuestions();
}
