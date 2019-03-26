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
        add(new Announcement("第18支部就学习十九大精神开展读书报告交流会", "2019年3月24日，第18支部为了提倡党员积极学习党的十九大精神，开展了关于十九大精神的读书报告交流会，并取得圆满的成功。该支部为党员不断学习做出了一个榜样示范作用。", new GregorianCalendar(2018, Calendar.APRIL, 13).getTime()));
        add(new Announcement("第20支部胡曼吟同学荣获先进个人第一名", "胡曼吟同学积极响应党员不断深入学习，加强自身党员修养的号召，在党培网上坚持学习党的有关知识，表现优秀，荣获先进个人第一名的好成绩，特此通报表扬！", new GregorianCalendar(2018, Calendar.MAY, 13).getTime()));
        add(new Announcement("关于举办2018-2019年度党支部总结大会的通知", "2018-2019年度支部总结大会将于2019年3月26日星期二晚19:00在报告厅举行，届时将对本学年的工作情况进行总结，请各党支部代表做好相关准备并按时出席！", new GregorianCalendar(2018, Calendar.JUNE, 13).getTime()));
        add(new Announcement("第2支部陆青雪同学荣获见义勇为奖", "2019年2月24日陆青雪同学在中山大道及时把一孩子从马路中央抱回，挽救了该孩子的性命，获得见义勇为奖，特此通报表扬！", new GregorianCalendar(2018, Calendar.JULY, 13).getTime()));
        add(new Announcement("中共中央印发《党政领导干部选拔任用工作条例》", "近日，中共中央印发了修订后的《党政领导干部选拔任用工作条例》（以下简称《干部任用条例》），并发出通知，要求各地区各部门结合实际认真遵照执行。\n" +
                "　　通知指出，党的十八大以来，以习近平同志为核心的党中央鲜明提出新时期好干部标准，进一步强化党组织领导和把关作用，完善选人用人制度机制，严把选人用人政治关、品行关、能力关、作风关、廉洁关，坚决匡正选人用人风气，推动选人用人工作取得显著成效、发生重大变化。为深入贯彻习近平新时代中国特色社会主义思想和党的十九大精神，全面贯彻新时代党的建设总要求和新时代党的组织路线，更好坚持和落实党管干部原则，党中央对《干部任用条例》进行了修订。\n", new GregorianCalendar(2018, Calendar.AUGUST, 13).getTime()));

    }};

    @Override
    public List<Announcement> allAnnouncements() {
        return allAnnouncements;
    }
}
