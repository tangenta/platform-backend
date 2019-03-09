package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.MStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatisticMapper {
    MStatistic getByStudentId(@Param("studentId") Long studentId);
}
