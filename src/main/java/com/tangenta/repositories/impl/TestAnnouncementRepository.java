package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.Announcement;
import com.tangenta.repositories.AnnouncementRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestAnnouncementRepository implements AnnouncementRepository {
    private List<Announcement> allAnnouncements = new LinkedList<Announcement>() {{
        add(new Announcement("title1", "content1", new GregorianCalendar(2018, Calendar.APRIL, 13).getTime()));
        add(new Announcement("title2", "content2", new GregorianCalendar(2018, Calendar.MAY, 13).getTime()));
        add(new Announcement("title3", "content3", new GregorianCalendar(2018, Calendar.JUNE, 13).getTime()));
        add(new Announcement("title4", "content4", new GregorianCalendar(2018, Calendar.JULY, 13).getTime()));
        add(new Announcement("title5", "content5", new GregorianCalendar(2018, Calendar.AUGUST, 13).getTime()));

    }};

    @Override
    public List<Announcement> allAnnouncements() {
        return allAnnouncements;
    }
}
