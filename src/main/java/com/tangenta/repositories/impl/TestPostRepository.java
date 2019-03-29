package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.MPost;
import com.tangenta.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestPostRepository implements PostRepository {
    private static Logger logger = LoggerFactory.getLogger(TestPostRepository.class);
    private static List<MPost> allMPost = new LinkedList<MPost>(){{
        add(new MPost(1L, new Date(), "“不忘初心，方得始终。”九十六年前的使命尚未完成，入党宣誓时的声音不会褪去。坚持学习党的理论确保党旗永葆红色，坚决抵制诱惑不忘初心确保党旗永不变色，是沉甸甸压在每一名共产党员肩上的责任，更是每一名党员的天职。请各位党员就如何保持初心发表一下自己的看法。",
                0L, 0L, 2017000001L, "“不忘初心”跟党走"));
        add(new MPost(2L, new Date(), "榜样让我们筑牢终身的信仰；榜样让我们专注脚下的道路；榜样让我们凝聚无悔的担当。我们共产党人要坚定信仰，坚持党的领导不动摇，传承和发扬优秀共产党员的精神品质，坚守平凡的岗位，努力学习、加强创新，以服务群众和担当作为为使命，不忘初心，继续前进。请大家谈谈身边的榜样事迹。",
                0L, 0L, 2017000002L, "向榜样学习，继续前进"));
        add(new MPost(3L, new GregorianCalendar(1990, Calendar.APRIL, 13, 13, 10).getTime(),
                "看齐意识是基础，坚持在政治上看齐，始终与党中央保持高度一致。在落实决策部署上看齐,确保党的路线方针政策贯彻执行，做到政令畅通。在工作上看齐，在律己上看齐，加强自我约束，自觉发挥好表率作用。党员干部要自觉增强看齐意识，始终做到经常看齐、主动看齐、全面看齐，确保党和国家的事业沿着正确方向阔步前进。",
                2L, 1L, 2017000001L, "增强看齐意识，做合格党员"));
        add(new MPost(4L, new Date(),
                "“三严三实”丰富和发展了党的建设理论，明确了领导干部的修身之本、为政之道、成事之要，体现了党要管党、从严治党的基本要求，是世界观和方法论的有机统一、内在自律和外在约束的有机统一，充分彰显了我们党在作风建设上一鼓作气、一抓到底的鲜明立场和坚定决心，为加强新形势下党的思想政治建设和作风建设提供了重要遵循，也是各级领导干部修身用权律己的基本遵循、干事创业的行为准则。",
                0L, 0L, 2017000001L, "“三严三实”的深刻内涵"));
        add(new MPost(5L, new GregorianCalendar(1990, Calendar.AUGUST, 13, 14, 10).getTime(),
                "清正廉洁是各级党委政府在人民群众心中形象的最好诠释。在“八项规定”出台至今，各地缩减三公消费成效明显，腐败浪费风气逐渐扭转，趁节日发放奖金、礼品现象骤减，人民大众对政府官员的公仆形象逐渐认可。由俭入奢易，由奢入俭难，作风建设永远在路上。请大家就作风建设提出自己的建议。",
                1L, 1L, 2017000001L, "让廉洁之风刮一会儿"));
    }};
    private static Long postId = 4L;

    @Override
    public List<MPost> getAllPosts() {
        return allMPost;
    }

    @Override
    public void create(MPost p) {
        allMPost.add(new MPost(postId++, p.getPublishTime(), p.getContent(),
                0L, 0L, p.getStudentId(), p.getTitle()));
    }

    @Override
    public MPost findById(Long postId) {
        return allMPost.stream()
                .filter(p -> p.getPostId().equals(postId))
                .findFirst().orElse(null);
    }

    @Override
    public List<MPost> findByUserId(Long studentId) {
        return allMPost.stream()
                .filter(p -> p.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long postId) {
        Iterator<MPost> i = allMPost.iterator();
        while (i.hasNext()) {
            MPost p = i.next();
            if (p.getPostId().equals(postId)) {
                i.remove();
                break;
            }
        }
    }

    @Override
    public void update(Long postId, String title, String content) {
        Iterator<MPost> i = allMPost.iterator();
        MPost newPost = null;
        while (i.hasNext()) {
            MPost p = i.next();
            if (p.getPostId().equals(postId)) {
                newPost = new MPost(postId, p.getPublishTime(), content, p.getViewNumber(),
                        p.getReplyNumber(), p.getStudentId(), title);
                i.remove();
                break;
            }
        }
        Objects.requireNonNull(newPost);
        allMPost.add(newPost);
    }

    @Override
    public void increaseViewNumber(Long postId) {
        Iterator<MPost> iter = allMPost.iterator();
        MPost newPost = null;

        while (iter.hasNext()) {
            MPost p = iter.next();
            if (p.getPostId().equals(postId)) {
                newPost = new MPost(p.getPostId(), p.getPublishTime(), p.getContent(),
                        p.getViewNumber() + 1, p.getReplyNumber(), p.getStudentId(), p.getTitle());
                iter.remove();
                break;
            }
        }
        allMPost.add(newPost);
    }

    @Override
    public void increaseReplyNumber(Long postId) {
        Iterator<MPost> iter = allMPost.iterator();
        MPost newPost = null;

        while (iter.hasNext()) {
            MPost p = iter.next();
            if (p.getPostId().equals(postId)) {
                newPost = new MPost(p.getPostId(), p.getPublishTime(), p.getContent(),
                        p.getViewNumber(), p.getReplyNumber() + 1, p.getStudentId(), p.getTitle());
                iter.remove();
                break;
            }
        }
        allMPost.add(newPost);
    }


}
