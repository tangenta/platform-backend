package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.StatisticRepository;
import com.tangenta.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.tangenta.data.pojo.QuestionClassification.*;
import static com.tangenta.data.pojo.QuestionType.*;

@Repository
@Profile("dev-test")
public class TestStatisticRepository implements StatisticRepository {
    private static Logger logger = LoggerFactory.getLogger(TestStatisticRepository.class);

    private static List<MStatistic> allStatistic = new LinkedList<MStatistic>() {{
        add(new MStatistic(2017000001L, 89L, 92L, 85L, 81L, 93.55518580311903, 79.32382360400591, 83.53698614209078, 88.96617760025899, 91L, 94L));
        add(new MStatistic(2017000002L, 86L, 85L, 76L, 88L, 89.85722321573532, 86.0931290214287, 92.66188524791328, 85.90793013150117, 85L, 91L));
        add(new MStatistic(2017000003L, 82L, 79L, 78L, 94L, 83.13162356235611, 82.93383398557376, 84.06839168786719, 75.1862333242455, 77L, 90L));
        add(new MStatistic(2017000004L, 75L, 92L, 89L, 78L, 75.91889813910144, 75.91294626029905, 87.43075272620887, 85.10806745663479, 80L, 75L));
        add(new MStatistic(2017000005L, 84L, 92L, 84L, 83L, 76.5517100799772, 81.86369829435685, 92.34336210311601, 80.66635783657709, 85L, 84L));
        add(new MStatistic(2017000006L, 94L, 76L, 77L, 91L, 86.64968959375392, 82.9066976222617, 76.26173807195846, 87.808707178715, 83L, 82L));
        add(new MStatistic(2017000007L, 93L, 81L, 87L, 83L, 79.2188869024556, 76.57454369089336, 94.69761753862107, 92.15163449521759, 92L, 85L));
        add(new MStatistic(2017000008L, 86L, 86L, 77L, 79L, 85.25133556961136, 75.09256687373536, 87.1588158120706, 92.63364423332523, 75L, 80L));
        add(new MStatistic(2017000009L, 81L, 85L, 81L, 94L, 84.98679715200203, 93.3371827264645, 88.49262312176072, 94.72171347561643, 80L, 76L));
        add(new MStatistic(2017000010L, 81L, 79L, 79L, 89L, 75.09470145723571, 76.56194154472988, 85.19044764227985, 82.0872402938958, 77L, 80L));
        add(new MStatistic(2017000011L, 88L, 82L, 93L, 80L, 84.97388896388114, 83.20616301645713, 83.12945801389844, 92.19933453067765, 76L, 93L));
        add(new MStatistic(2017000012L, 84L, 84L, 81L, 92L, 83.96257882982165, 81.91304721338784, 92.22959849685344, 77.98563893616759, 88L, 93L));
        add(new MStatistic(2017000013L, 82L, 85L, 80L, 85L, 85.13004045154204, 90.46429711022327, 91.08203577280109, 85.45402101760601, 79L, 76L));
        add(new MStatistic(2017000014L, 88L, 85L, 82L, 91L, 79.1586458939774, 76.0325756799948, 88.74215702873354, 82.6029792013016, 79L, 87L));
        add(new MStatistic(2017000015L, 87L, 87L, 81L, 77L, 94.02115939582754, 81.23866705279846, 86.61573070730283, 75.89584192511265, 87L, 80L));
        add(new MStatistic(2017000016L, 76L, 83L, 87L, 92L, 82.41679005460702, 75.26717831289491, 79.762271745851, 90.05910936305597, 89L, 75L));
        add(new MStatistic(2017000017L, 77L, 86L, 84L, 79L, 85.62229361228205, 77.16600486554242, 93.01969412432314, 83.03092929841912, 85L, 94L));
        add(new MStatistic(2017000018L, 78L, 93L, 76L, 91L, 93.37216085514376, 85.71065216576861, 80.14203308110085, 77.11777632412583, 85L, 80L));
        add(new MStatistic(2017000019L, 92L, 88L, 94L, 84L, 77.06649823344203, 81.48044906771555, 75.29725926405473, 87.69502998665456, 82L, 78L));
        add(new MStatistic(2017000020L, 89L, 80L, 92L, 75L, 89.7492470925657, 85.84412092687124, 89.78032313690623, 91.06358650478433, 83L, 85L));
        add(new MStatistic(2017000021L, 90L, 76L, 91L, 80L, 94.63612994386318, 78.40242526367588, 85.96216074902108, 80.91636368811015, 94L, 86L));
        add(new MStatistic(2017000022L, 84L, 94L, 84L, 88L, 88.25081493246408, 88.56374794199048, 82.07698560781257, 88.37338674139603, 91L, 84L));
        add(new MStatistic(2017000023L, 87L, 84L, 83L, 92L, 94.21347180068719, 82.42590121535675, 85.16288506008847, 82.88816972916764, 80L, 92L));
        add(new MStatistic(2017000024L, 83L, 84L, 87L, 79L, 75.79193303287461, 79.7002146056015, 84.96909414941885, 84.45001098005372, 87L, 91L));
        add(new MStatistic(2017000025L, 93L, 78L, 76L, 93L, 93.10362473228288, 94.43737808263677, 75.68714808498974, 86.00106133355756, 79L, 90L));
        add(new MStatistic(2017000026L, 82L, 90L, 86L, 79L, 91.39526048094133, 75.50815528861436, 93.45551487833126, 86.46767993188058, 86L, 85L));
        add(new MStatistic(2017000027L, 81L, 77L, 90L, 93L, 76.02066589211906, 86.97528563742463, 78.01293099808983, 87.89440760907765, 93L, 83L));
        add(new MStatistic(2017000028L, 75L, 81L, 80L, 88L, 88.6191606291986, 81.33062239070122, 83.39240416516174, 88.06012248367385, 79L, 91L));
        add(new MStatistic(2017000029L, 75L, 89L, 87L, 94L, 88.3955295161695, 86.02747489572305, 94.70520996486329, 93.85258644413928, 76L, 78L));
        add(new MStatistic(2017000030L, 87L, 80L, 87L, 91L, 80.02192463282498, 83.19356351963148, 91.04605585891153, 87.65905337740143, 86L, 93L));
        add(new MStatistic(2017000031L, 77L, 83L, 80L, 86L, 94.28521158074386, 84.39389650548651, 75.98842440589807, 77.27326259012267, 89L, 84L));
        add(new MStatistic(2017000032L, 93L, 90L, 80L, 91L, 80.92128216117179, 84.01804285827505, 91.16001196559989, 79.75690126969283, 92L, 78L));
        add(new MStatistic(2017000033L, 84L, 89L, 80L, 77L, 84.16109281183797, 79.69536317838214, 91.69118012553737, 92.40505225298512, 91L, 84L));
        add(new MStatistic(2017000034L, 84L, 86L, 88L, 79L, 90.95977096599512, 92.3427997273669, 81.41062900373572, 89.76123141484626, 83L, 84L));
        add(new MStatistic(2017000035L, 76L, 75L, 91L, 84L, 85.0319042635479, 84.93251211599896, 75.73063380130509, 87.36111439750226, 91L, 80L));
        add(new MStatistic(2017000036L, 82L, 88L, 88L, 85L, 81.16423489442798, 93.96244712592049, 83.7910761998892, 86.8030258838699, 93L, 81L));
        add(new MStatistic(2017000037L, 85L, 81L, 78L, 82L, 94.8493104602143, 89.98031762933749, 89.3466350127533, 94.84399802534973, 79L, 88L));
        add(new MStatistic(2017000038L, 81L, 84L, 77L, 79L, 82.2208936225792, 86.97416687704873, 81.67720413315183, 91.5437569653125, 78L, 89L));
        add(new MStatistic(2017000039L, 92L, 76L, 76L, 92L, 76.14192298201338, 78.98100986480014, 92.72406673257582, 79.0387434283929, 79L, 86L));
        add(new MStatistic(2017000040L, 91L, 90L, 94L, 93L, 94.5944380149979, 91.13933716084378, 93.66243972750756, 89.7385481131189, 91L, 87L));
        add(new MStatistic(2017000041L, 90L, 80L, 80L, 85L, 91.47305499041393, 90.36420311004672, 81.70298005982754, 92.94897149253849, 89L, 87L));
        add(new MStatistic(2017000042L, 77L, 90L, 93L, 94L, 90.3915600466822, 86.43397161607159, 93.30650450005466, 78.60710532538006, 84L, 94L));
        add(new MStatistic(2017000043L, 79L, 78L, 90L, 75L, 77.48859464637111, 93.27539638747898, 81.88878689869136, 75.89806063942157, 85L, 94L));
        add(new MStatistic(2017000044L, 88L, 80L, 75L, 87L, 92.90338722235484, 91.61053963907425, 75.1701528171649, 76.20997356109343, 76L, 91L));
        add(new MStatistic(2017000045L, 92L, 81L, 87L, 93L, 86.21816025062778, 93.59209110822354, 79.2498827723977, 94.78951451461997, 80L, 75L));
        add(new MStatistic(2017000046L, 83L, 78L, 75L, 88L, 89.28122929068559, 77.36315146383467, 85.28900743028176, 86.62364288884707, 94L, 76L));
        add(new MStatistic(2017000047L, 85L, 75L, 83L, 77L, 78.59135618538622, 79.70860701459097, 83.76734390976455, 82.40533870688283, 92L, 89L));
        add(new MStatistic(2017000048L, 93L, 85L, 77L, 92L, 76.41796163660531, 90.97747849970266, 77.56855200074038, 90.05701770912044, 94L, 77L));
        add(new MStatistic(2017000049L, 89L, 79L, 91L, 81L, 92.50649855259391, 76.51434854638609, 80.27185539686225, 81.09806225931419, 84L, 85L));
        add(new MStatistic(2017000050L, 78L, 94L, 78L, 90L, 90.10836882913769, 93.49854539726232, 87.6591856306615, 89.69309385273218, 86L, 82L));

    }};

    private static List<QuestionStatistic> allQuestionStatistic = new LinkedList<QuestionStatistic>() {{
        add(new QuestionStatistic(2017000001L, Lilunjichu, BlanksFilling, 3L, 1L));
        add(new QuestionStatistic(2017000001L, Lilunjichu, SingleChoice, 4L, 3L));
        add(new QuestionStatistic(2017000001L, Lilunjichu, MultipleChoice, 5L, 2L));
        add(new QuestionStatistic(2017000001L, Lilunjichu, BlanksFilling, 6L, 2L));
        add(new QuestionStatistic(2017000001L, Lilunjichu, TrueOrFalse, 3L, 2L));
        add(new QuestionStatistic(2017000001L, Daodepingjia, BlanksFilling, 15L, 5L));
        add(new QuestionStatistic(2017000001L, Jiazhiguan, SingleChoice, 15L, 8L));
        add(new QuestionStatistic(2017000001L, Sixiangxianjinxing, SingleChoice, 17L, 12L));
        add(new QuestionStatistic(2017000001L, Jilvxing, MultipleChoice, 13L, 8L));
        add(new QuestionStatistic(2017000002L, Lilunjichu, BlanksFilling, 1L, 1L));
        add(new QuestionStatistic(2017000003L, Lilunjichu, BlanksFilling, 2L, 1L));
    }};

    private static LocalDate buildDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    private static List<DoneTag> mockDoneTag = new LinkedList<DoneTag>() {{
        add(new DoneTag(2017000001L, 1L, buildDate(2019, 3, 23)));
        add(new DoneTag(2017000001L, 2L, buildDate(2019, 3, 23)));
        add(new DoneTag(2017000001L, 3L, buildDate(2019, 3, 23)));
        add(new DoneTag(2017000001L, 4L, buildDate(2019, 3, 23)));
        add(new DoneTag(2017000001L, 5L, buildDate(2019, 3, 23)));
        add(new DoneTag(2017000001L, 6L, buildDate(2019, 3, 24)));
        add(new DoneTag(2017000001L, 7L, buildDate(2019, 3, 25)));
        add(new DoneTag(2017000001L, 8L, buildDate(2019, 3, 25)));
        add(new DoneTag(2017000001L, 9L, LocalDate.now()));
        add(new DoneTag(2017000001L, 10L, LocalDate.now()));
    }};


    @Override
    public List<MStatistic> allStatistics() {
        return allStatistic;
    }

    @Override
    public Optional<MStatistic> getUserStatisticByStudentId(Long studentId) {
        return allStatistic.stream()
                .filter(ms -> ms.getStudentId().equals(studentId))
                .findFirst();
    }

    @Override
    public void increaseQuestionCreation(Long studentId) {
        Utils.substitute(allStatistic, m -> m.getStudentId().equals(studentId),
                m -> new MStatistic(m.getStudentId(), m.getOfflineLearningTime(),
                        m.getOnlineLearningTime(), m.getPostQuestionNumber() + 1, m.getPassQuestionNumber(),
                        m.getAttendanceRate(), m.getPaperScore(), m.getHomeworkScore(), m.getAnnualScore(),
                        m.getAnswerQuestionNumber(), m.getAnswerQuestionScore()));
    }

    @Override
    public void increaseQuestionPassing(Long studentId) {
        Utils.substitute(allStatistic, m -> m.getStudentId().equals(studentId),
                m -> new MStatistic(m.getStudentId(), m.getOfflineLearningTime(),
                        m.getOnlineLearningTime(), m.getPostQuestionNumber(), m.getPassQuestionNumber() + 1,
                        m.getAttendanceRate(), m.getPaperScore(), m.getHomeworkScore(), m.getAnnualScore(),
                        m.getAnswerQuestionNumber(), m.getAnswerQuestionScore()));
    }

    @Override
    public void updateStatistic(MStatistic mStatistic) {
        Utils.substitute(allStatistic,
                m -> m.getStudentId().equals(mStatistic.getStudentId()),
                m -> mStatistic);
    }

    @Override
    public QuestionStatistic getByKeys(Long studentId, QuestionClassification classification, QuestionType type) {
        for (QuestionStatistic qs: allQuestionStatistic) {
            if (qs.getStudentId().equals(studentId) && qs.getClassification().equals(classification)
                    && qs.getType().equals(type)) return qs;
        }
        return null;
    }

    @Override
    public List<QuestionStatistic> getQuestionStatisticGroupByClasses(Long studentId) {
        return allQuestionStatistic.stream()
                .filter(qs -> qs.getStudentId().equals(studentId))
                .collect(Collectors.groupingBy(
                        QuestionStatistic::getClassification,
                        Collectors.reducing(
                                (QuestionStatistic a, QuestionStatistic b) ->
                            new QuestionStatistic(studentId, b.getClassification(), null,
                                    a.getTotal() + b.getTotal(), a.getCorrect() + b.getCorrect())
                        ))).values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionStatistic> getQuestionStatisticGroupByTypes(Long studentId) {
        return allQuestionStatistic.stream()
                .filter(qs -> qs.getStudentId().equals(studentId))
                .collect(Collectors.groupingBy(
                        QuestionStatistic::getType,
                        Collectors.reducing(
                                (QuestionStatistic a, QuestionStatistic b) ->
                                        new QuestionStatistic(studentId, null, b.getType(),
                                                a.getTotal() + b.getTotal(), a.getCorrect() + b.getCorrect())
                        ))).values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    @Override
    public void insertQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        allQuestionStatistic.add(new QuestionStatistic(studentId, classification, type, total, correct));
    }

    @Override
    public void updateQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        Utils.substitute(allQuestionStatistic,
                qs -> qs.getStudentId().equals(studentId) &&
                        qs.getClassification().equals(classification) &&
                        qs.getType().equals(type),
                qs -> new QuestionStatistic(qs.getStudentId(), qs.getClassification(), qs.getType(),
                        total, correct));
    }

    @Override
    public DoneTag getDoneTagByKeys(Long studentId, Long questionId) {
        DoneTag result = null;
        for (DoneTag dt: mockDoneTag) {
            if (dt.getStudentId().equals(studentId) && dt.getQuestionId().equals(questionId)) {
                result = dt;
            }
        }
        return result;
    }

    @Override
    public void insertDoneTag(Long studentId, Long questionId, LocalDate doneDate) {
        mockDoneTag.add(new DoneTag(studentId, questionId, doneDate));
    }

    @Override
    public List<AnswerCountDatePair> countAndGroupByDate(Long studentId) {
        List<AnswerCountDatePair> answerCountDatePairs = new LinkedList<>();
        mockDoneTag.stream()
                .filter(dt -> dt.getStudentId().equals(studentId))
                .collect(Collectors.groupingBy(DoneTag::getDoneDate,
                        Collectors.counting()))
            .forEach((date, count) -> answerCountDatePairs.add(
                new AnswerCountDatePair(count.intValue(), date)));
        return answerCountDatePairs;
    }

}
