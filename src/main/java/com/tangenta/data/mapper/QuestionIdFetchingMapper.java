package com.tangenta.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface QuestionIdFetchingMapper {

    @Select("select max(question_id) from question")
    Long getMaxQuestionId();
}
