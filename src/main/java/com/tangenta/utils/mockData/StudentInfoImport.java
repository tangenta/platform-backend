package com.tangenta.utils.mockData;

import com.tangenta.data.pojo.StudentInfo;

import java.util.ArrayList;

public class StudentInfoImport {
    public static void main(String[] args){
        studentInfoImport().forEach(si -> {
            System.out.println("add(new StudentInfo(" +
                    si.getStudentId() +
                    "L, \"" +
                    si.getStudentName() +
                    "\", \"" +
                    si.getPartyBranch() +
                    "\"));");
        });
    }

    public static ArrayList<StudentInfo> studentInfoImport(){
        ArrayList<StudentInfo> studentInfos = new ArrayList<>();

    String[] studentName = {"谷寄翠", "陆青雪", "狄雪艳", "刘凝思", "休涵意",
            "梁苑博", "闾绮琴", "陆鸿卓", "黄骞尧", "吴春雪", "周子琪",
            "康飞英", "何晗日", "崔凝安", "于雅丽", "杨菀柳", "于静曼",
            "单又亦", "高惜筠", "胡曼吟", "赵长平", "冯善和", "黎以云",
            "匡思松", "卢捷", "邹诗蕊", "李秋莲", "唐玛丽", "邱淑贞",
            "张寄蕾", "孙好慕", "乐悦畅", "疏施然", "彭鹏煊", "时嘉玉",
            "曹语柳", "赵乐儿", "万雪凝", "芈学林", "凌雪", "何以珊",
            "针秋灵", "武冰珍", "齐经武", "昌鹏涛", "丰艳丽", "项灵",
            "柔依珊", "阙安国", "邓寄文", "杜绮兰"};

    String[] partyBranch = new String[50];
    for(int i = 0;i<partyBranch.length;i++)
        partyBranch[i] = "第" + (i+1) + "支部";
    for (int i = 0;i<50;i++) {
        studentInfos.add(new StudentInfo((long)i, studentName[i], partyBranch[i]));
//        System.out.println(studentInfos.get(i).toString());
    }

    return  studentInfos;
    }

}
