package com.tangenta.data.mapper;

import com.tangenta.data.pojo.mybatis.MStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticMapper {
    List<MStatistic> allStatistics();
    MStatistic getByStudentId(@Param("studentId") Long studentId);
}
