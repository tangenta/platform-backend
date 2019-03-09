package com.tangenta.data.mapper;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionStatisticMapper {
    QuestionStatistic findByKeys(@Param("id") Long studentId,
                                       @Param("class") QuestionClassification classification,
                                       @Param("type") QuestionType type);

//    void insert(@Param("id") Long studentId,
//                @Param("class") QuestionClassification classification,
//                @Param("type") QuestionType type,
//                @Param("total") Long total,
//                @Param("correct") Long correct);
//
    void insert(@Param("qs") QuestionStatistic questionStatistic);

//    void update(@Param("id") Long studentId,
//                @Param("class") QuestionClassification classification,
//                @Param("type") QuestionType type,
//                @Param("total") Long total,
//                @Param("correct") Long correct);

    void update(@Param("qs") QuestionStatistic questionStatistic);
}
