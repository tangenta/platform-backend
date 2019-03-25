package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.AnnouncementMapper;
import com.tangenta.data.pojo.Announcement;
import com.tangenta.repositories.AnnouncementRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("dev")
public class MyAnnouncementRepository implements AnnouncementRepository {
    private AnnouncementMapper announcementMapper;

    public MyAnnouncementRepository(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @Override
    public List<Announcement> allAnnouncements() {
        return announcementMapper.allAnnouncements();
    }
}
