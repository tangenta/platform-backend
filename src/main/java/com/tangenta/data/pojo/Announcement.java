package com.tangenta.data.pojo;

import java.util.Date;

public class Announcement {
    private String title;
    private String content;
    private Date creationTime;

    public Announcement() {
    }

    public Announcement(String title, String content, Date creationTime) {
        this.title = title;
        this.content = content;
        this.creationTime = creationTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
