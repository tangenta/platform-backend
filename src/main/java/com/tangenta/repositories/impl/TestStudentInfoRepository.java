package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.StudentInfo;
import com.tangenta.repositories.StudentInfoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestStudentInfoRepository implements StudentInfoRepository {
    private static List<StudentInfo> allStudentInfo = new ArrayList<StudentInfo>(){{
        add(new StudentInfo(0L, "谷寄翠", "第1支部"));
        add(new StudentInfo(1L, "陆青雪", "第2支部"));
        add(new StudentInfo(2L, "狄雪艳", "第3支部"));
        add(new StudentInfo(3L, "刘凝思", "第4支部"));
        add(new StudentInfo(4L, "休涵意", "第5支部"));
        add(new StudentInfo(5L, "梁苑博", "第6支部"));
        add(new StudentInfo(6L, "闾绮琴", "第7支部"));
        add(new StudentInfo(7L, "陆鸿卓", "第8支部"));
        add(new StudentInfo(8L, "黄骞尧", "第9支部"));
        add(new StudentInfo(9L, "吴春雪", "第10支部"));
        add(new StudentInfo(10L, "周子琪", "第11支部"));
        add(new StudentInfo(11L, "康飞英", "第12支部"));
        add(new StudentInfo(12L, "何晗日", "第13支部"));
        add(new StudentInfo(13L, "崔凝安", "第14支部"));
        add(new StudentInfo(14L, "于雅丽", "第15支部"));
        add(new StudentInfo(15L, "杨菀柳", "第16支部"));
        add(new StudentInfo(16L, "于静曼", "第17支部"));
        add(new StudentInfo(17L, "单又亦", "第18支部"));
        add(new StudentInfo(18L, "高惜筠", "第19支部"));
        add(new StudentInfo(19L, "胡曼吟", "第20支部"));
        add(new StudentInfo(20L, "赵长平", "第21支部"));
        add(new StudentInfo(21L, "冯善和", "第22支部"));
        add(new StudentInfo(22L, "黎以云", "第23支部"));
        add(new StudentInfo(23L, "匡思松", "第24支部"));
        add(new StudentInfo(24L, "卢捷", "第25支部"));
        add(new StudentInfo(25L, "邹诗蕊", "第26支部"));
        add(new StudentInfo(26L, "李秋莲", "第27支部"));
        add(new StudentInfo(27L, "唐玛丽", "第28支部"));
        add(new StudentInfo(28L, "邱淑贞", "第29支部"));
        add(new StudentInfo(29L, "张寄蕾", "第30支部"));
        add(new StudentInfo(30L, "孙好慕", "第31支部"));
        add(new StudentInfo(31L, "乐悦畅", "第32支部"));
        add(new StudentInfo(32L, "疏施然", "第33支部"));
        add(new StudentInfo(33L, "彭鹏煊", "第34支部"));
        add(new StudentInfo(34L, "时嘉玉", "第35支部"));
        add(new StudentInfo(35L, "曹语柳", "第36支部"));
        add(new StudentInfo(36L, "赵乐儿", "第37支部"));
        add(new StudentInfo(37L, "万雪凝", "第38支部"));
        add(new StudentInfo(38L, "芈学林", "第39支部"));
        add(new StudentInfo(39L, "凌雪", "第40支部"));
        add(new StudentInfo(40L, "何以珊", "第41支部"));
        add(new StudentInfo(41L, "针秋灵", "第42支部"));
        add(new StudentInfo(42L, "武冰珍", "第43支部"));
        add(new StudentInfo(43L, "齐经武", "第44支部"));
        add(new StudentInfo(44L, "昌鹏涛", "第45支部"));
        add(new StudentInfo(45L, "丰艳丽", "第46支部"));
        add(new StudentInfo(46L, "项灵", "第47支部"));
        add(new StudentInfo(47L, "柔依珊", "第48支部"));
        add(new StudentInfo(48L, "阙安国", "第49支部"));
        add(new StudentInfo(49L, "邓寄文", "第50支部"));
    }};

    @Override
    public StudentInfo findById(Long studentId) {
        return allStudentInfo.stream()
                .filter(si -> si.getStudentId()
                        .equals(studentId))
                .findFirst()
                .orElse(new StudentInfo(studentId));
    }

    @Override
    public void update(StudentInfo studentInfo) {
        Iterator<StudentInfo> iter = allStudentInfo.iterator();
        while (iter.hasNext()) {
            StudentInfo sInfo = iter.next();
            if (sInfo.getStudentId().equals(studentInfo.getStudentId())) {
                iter.remove();
                break;
            }
        }
        allStudentInfo.add(studentInfo);
    }
}
