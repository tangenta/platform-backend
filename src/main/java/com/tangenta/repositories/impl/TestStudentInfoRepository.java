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
        add(new StudentInfo(2017000001L, "谷寄翠", "第1支部"));
        add(new StudentInfo(2017000002L, "陆青雪", "第2支部"));
        add(new StudentInfo(2017000003L, "狄雪艳", "第3支部"));
        add(new StudentInfo(2017000004L, "刘凝思", "第4支部"));
        add(new StudentInfo(2017000005L, "休涵意", "第5支部"));
        add(new StudentInfo(2017000006L, "梁苑博", "第6支部"));
        add(new StudentInfo(2017000007L, "闾绮琴", "第7支部"));
        add(new StudentInfo(2017000008L, "陆鸿卓", "第8支部"));
        add(new StudentInfo(2017000009L, "黄骞尧", "第9支部"));
        add(new StudentInfo(2017000010L, "吴春雪", "第10支部"));
        add(new StudentInfo(2017000011L, "周子琪", "第11支部"));
        add(new StudentInfo(2017000012L, "康飞英", "第12支部"));
        add(new StudentInfo(2017000013L, "何晗日", "第13支部"));
        add(new StudentInfo(2017000014L, "崔凝安", "第14支部"));
        add(new StudentInfo(2017000015L, "于雅丽", "第15支部"));
        add(new StudentInfo(2017000016L, "杨菀柳", "第16支部"));
        add(new StudentInfo(2017000017L, "于静曼", "第17支部"));
        add(new StudentInfo(2017000018L, "单又亦", "第18支部"));
        add(new StudentInfo(2017000019L, "高惜筠", "第19支部"));
        add(new StudentInfo(2017000020L, "胡曼吟", "第20支部"));
        add(new StudentInfo(2017000021L, "赵长平", "第21支部"));
        add(new StudentInfo(2017000022L, "冯善和", "第22支部"));
        add(new StudentInfo(2017000023L, "黎以云", "第23支部"));
        add(new StudentInfo(2017000024L, "匡思松", "第24支部"));
        add(new StudentInfo(2017000025L, "卢捷", "第25支部"));
        add(new StudentInfo(2017000026L, "邹诗蕊", "第26支部"));
        add(new StudentInfo(2017000027L, "李秋莲", "第27支部"));
        add(new StudentInfo(2017000028L, "唐玛丽", "第28支部"));
        add(new StudentInfo(2017000029L, "邱淑贞", "第29支部"));
        add(new StudentInfo(2017000030L, "张寄蕾", "第30支部"));
        add(new StudentInfo(2017000031L, "孙好慕", "第31支部"));
        add(new StudentInfo(2017000032L, "乐悦畅", "第32支部"));
        add(new StudentInfo(2017000033L, "疏施然", "第33支部"));
        add(new StudentInfo(2017000034L, "彭鹏煊", "第34支部"));
        add(new StudentInfo(2017000035L, "时嘉玉", "第35支部"));
        add(new StudentInfo(2017000036L, "曹语柳", "第36支部"));
        add(new StudentInfo(2017000037L, "赵乐儿", "第37支部"));
        add(new StudentInfo(2017000038L, "万雪凝", "第38支部"));
        add(new StudentInfo(2017000039L, "芈学林", "第39支部"));
        add(new StudentInfo(2017000040L, "凌雪", "第40支部"));
        add(new StudentInfo(2017000041L, "何以珊", "第41支部"));
        add(new StudentInfo(2017000042L, "针秋灵", "第42支部"));
        add(new StudentInfo(2017000043L, "武冰珍", "第43支部"));
        add(new StudentInfo(2017000044L, "齐经武", "第44支部"));
        add(new StudentInfo(2017000045L, "昌鹏涛", "第45支部"));
        add(new StudentInfo(2017000046L, "丰艳丽", "第46支部"));
        add(new StudentInfo(2017000047L, "项灵", "第47支部"));
        add(new StudentInfo(2017000048L, "柔依珊", "第48支部"));
        add(new StudentInfo(2017000049L, "阙安国", "第49支部"));
        add(new StudentInfo(2017000050L, "邓寄文", "第50支部"));
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
