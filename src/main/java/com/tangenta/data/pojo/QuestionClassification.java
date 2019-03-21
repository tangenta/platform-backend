package com.tangenta.data.pojo;

import java.util.LinkedList;
import java.util.List;

public enum QuestionClassification {
    Lilunjichu,
    Jilvxing,
    Jiazhiguan,
    Daodepingjia,
    Sixiangxianjinxing;

    public static List<QuestionClassification> allClasses = new LinkedList<QuestionClassification>(){{
       add(Lilunjichu);
       add(Jilvxing);
       add(Jiazhiguan);
       add(Daodepingjia);
       add(Sixiangxianjinxing);
    }};
}
