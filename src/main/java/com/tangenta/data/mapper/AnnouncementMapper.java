package com.tangenta.data.mapper;

import com.tangenta.data.pojo.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    @Select("select * from announcement")
    @ResultMap("baseResultMap")
    List<Announcement> allAnnouncements();
}
