package com.tangenta.repositories;

import com.tangenta.data.pojo.Announcement;

import java.util.List;

public interface AnnouncementRepository {
    List<Announcement> allAnnouncements();
}
