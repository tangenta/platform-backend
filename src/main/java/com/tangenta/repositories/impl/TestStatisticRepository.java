package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.tangenta.data.pojo.QuestionClassification.Lilunjichu;
import static com.tangenta.data.pojo.QuestionType.BlanksFilling;

@Repository
@Profile("dev-test")
public class TestStatisticRepository implements StatisticRepository {
    private static Logger logger = LoggerFactory.getLogger(TestStatisticRepository.class);

    private static List<MStatistic> allStatistic = new LinkedList<MStatistic>() {{
        add(new MStatistic(0L, 80L, 80L, 93L, 92L, 87.65878058963715, 85.76658114500242, 87.8507979577499, 86.99250964705098, 77L, 92L));
        add(new MStatistic(1L, 92L, 89L, 84L, 85L, 79.18905691896907, 88.61368639106547, 85.68642762970697, 92.42457689060342, 87L, 92L));
        add(new MStatistic(2L, 91L, 91L, 80L, 80L, 87.38369082243737, 81.25013789087818, 87.26340452938945, 82.75791093442578, 75L, 93L));
        add(new MStatistic(3L, 80L, 93L, 75L, 85L, 90.68804424507087, 78.7621560683356, 78.40180907685179, 75.83508174122055, 80L, 79L));
        add(new MStatistic(4L, 90L, 89L, 88L, 89L, 82.30475936245621, 78.01560004951985, 82.99431494310329, 86.91037459992012, 75L, 89L));
        add(new MStatistic(5L, 75L, 91L, 76L, 87L, 81.94457487696202, 77.14371841807694, 81.63164457560212, 86.10997852969999, 90L, 78L));
        add(new MStatistic(6L, 76L, 87L, 81L, 75L, 94.69891670563608, 83.6715547291131, 91.043239947824, 89.96352388538398, 82L, 79L));
        add(new MStatistic(7L, 78L, 90L, 77L, 92L, 78.56378064668868, 75.75375728022904, 90.92619624217849, 80.97880271132793, 92L, 81L));
        add(new MStatistic(8L, 94L, 86L, 86L, 83L, 84.27670653299492, 85.83774328016113, 92.73249479587972, 83.6620813463873, 91L, 79L));
        add(new MStatistic(9L, 77L, 90L, 86L, 75L, 91.82184025410433, 89.05273897919336, 83.11843146703738, 91.55431133343167, 94L, 94L));
        add(new MStatistic(10L, 84L, 93L, 81L, 77L, 94.91525797638857, 84.89077211254416, 82.83453037566693, 94.55958999579914, 76L, 85L));
        add(new MStatistic(11L, 78L, 76L, 84L, 85L, 91.4495174109854, 90.76140507310629, 85.01211716681502, 87.86194654245445, 78L, 81L));
        add(new MStatistic(12L, 83L, 82L, 82L, 86L, 94.27039243306653, 79.0994760138922, 90.38325707810324, 77.27030344811921, 75L, 85L));
        add(new MStatistic(13L, 88L, 85L, 81L, 88L, 78.24506389384592, 81.39659542063029, 88.88497700616163, 91.46908008844288, 90L, 90L));
        add(new MStatistic(14L, 82L, 79L, 85L, 82L, 85.13704092183029, 89.45817660607597, 91.85334535465283, 83.46027708381959, 87L, 86L));
        add(new MStatistic(15L, 79L, 88L, 89L, 78L, 90.2506826781041, 90.78209092641632, 92.7937476161899, 84.2880702130339, 83L, 92L));
        add(new MStatistic(16L, 80L, 91L, 91L, 86L, 83.3507210585635, 88.57935721351673, 76.48704907511892, 88.60742122463408, 88L, 91L));
        add(new MStatistic(17L, 84L, 78L, 84L, 80L, 85.69673949887793, 82.52307867479476, 93.69748121784782, 90.5496155235005, 75L, 77L));
        add(new MStatistic(18L, 82L, 88L, 75L, 78L, 84.04813322001385, 85.94282680817567, 83.71782640059523, 92.21782844055261, 88L, 90L));
        add(new MStatistic(19L, 91L, 86L, 85L, 90L, 83.36954068466198, 94.94864046963703, 90.39687132086087, 89.42061975394155, 92L, 86L));
        add(new MStatistic(20L, 82L, 87L, 79L, 87L, 90.88107175593122, 87.06929449567086, 83.85414773785071, 84.4932141151327, 75L, 85L));
        add(new MStatistic(21L, 82L, 87L, 89L, 76L, 83.13381751420415, 86.8204973410744, 93.71811502901289, 88.75733735491383, 78L, 80L));
        add(new MStatistic(22L, 81L, 90L, 80L, 88L, 88.97158568810426, 78.93749723007485, 75.02805982623332, 82.6165897821873, 94L, 84L));
        add(new MStatistic(23L, 89L, 77L, 91L, 76L, 84.68781289336279, 80.96864798962716, 87.51903199523491, 88.23496180017943, 80L, 81L));
        add(new MStatistic(24L, 84L, 78L, 76L, 91L, 83.71859841966999, 75.35778493317066, 77.88713203229943, 89.68803559438913, 80L, 92L));
        add(new MStatistic(25L, 76L, 91L, 80L, 93L, 75.83605497960296, 92.21804364674304, 93.67938391436181, 86.43367059802979, 91L, 92L));
        add(new MStatistic(26L, 84L, 77L, 82L, 79L, 89.00898347483555, 87.01705469468381, 81.86841424185559, 90.49823900369624, 80L, 91L));
        add(new MStatistic(27L, 80L, 82L, 91L, 92L, 88.2209573937544, 88.90720687050022, 76.48590259563049, 76.33753331908314, 94L, 78L));
        add(new MStatistic(28L, 92L, 80L, 77L, 89L, 93.42392972362437, 82.144567884255, 88.972710787871, 82.27550687983292, 80L, 90L));
        add(new MStatistic(29L, 81L, 93L, 89L, 87L, 89.63706754497291, 83.3039028974103, 90.57490519040154, 77.79096246886095, 90L, 80L));
        add(new MStatistic(30L, 83L, 77L, 80L, 79L, 85.1201080030874, 86.96917820178012, 82.24356965067015, 77.34112160499913, 81L, 78L));
        add(new MStatistic(31L, 83L, 84L, 88L, 78L, 80.98287039065968, 76.91007948225734, 83.66507333120583, 79.82329961756024, 92L, 76L));
        add(new MStatistic(32L, 89L, 88L, 77L, 84L, 75.49140465470322, 76.97527629107461, 88.37870467790894, 94.84606007486899, 94L, 94L));
        add(new MStatistic(33L, 90L, 87L, 79L, 75L, 78.84313420849092, 85.16132073434865, 83.70661226315464, 91.90798210115328, 79L, 88L));
        add(new MStatistic(34L, 84L, 83L, 75L, 85L, 79.42675747752334, 82.87131423070262, 86.34011447880445, 75.26777664915099, 93L, 75L));
        add(new MStatistic(35L, 78L, 80L, 83L, 91L, 87.68336869520627, 77.85892000836046, 88.10971569442296, 76.41734222579343, 78L, 75L));
        add(new MStatistic(36L, 94L, 83L, 82L, 87L, 76.69911621139329, 75.80303547510317, 85.21107937905788, 76.73297770754544, 82L, 93L));
        add(new MStatistic(37L, 93L, 93L, 93L, 93L, 94.99380232242935, 94.61931832930875, 80.12812962846591, 82.65316527378548, 75L, 91L));
        add(new MStatistic(38L, 76L, 94L, 84L, 90L, 78.23391887481007, 82.46401143252132, 76.70459792795845, 84.59292948283193, 76L, 84L));
        add(new MStatistic(39L, 89L, 84L, 75L, 84L, 85.04448722995795, 82.67623359958733, 76.66151804850738, 75.79143551457238, 84L, 93L));
        add(new MStatistic(40L, 80L, 89L, 83L, 81L, 82.66342047062355, 80.08048746874984, 76.16185938926489, 78.3447233687168, 77L, 91L));
        add(new MStatistic(41L, 91L, 85L, 93L, 90L, 81.96072779314615, 93.00181262195412, 85.13021359703208, 90.71960961669704, 89L, 83L));
        add(new MStatistic(42L, 91L, 91L, 86L, 88L, 81.00721361141146, 76.13011112006598, 89.45241511275863, 76.40480004484814, 86L, 76L));
        add(new MStatistic(43L, 86L, 89L, 75L, 92L, 78.69707907668729, 93.83617961740624, 77.14561020628675, 91.62581998852097, 86L, 76L));
        add(new MStatistic(44L, 79L, 87L, 81L, 88L, 90.41207516998557, 92.82570167508428, 79.30591475606508, 86.09213574436164, 86L, 78L));
        add(new MStatistic(45L, 76L, 86L, 82L, 75L, 93.61403188725633, 77.09709271128187, 80.52025393526061, 88.58103507798583, 91L, 79L));
        add(new MStatistic(46L, 87L, 85L, 93L, 87L, 85.08163008337635, 77.65960609841515, 90.41881369739107, 89.76175703997548, 88L, 81L));
        add(new MStatistic(47L, 87L, 80L, 77L, 89L, 82.28420267707172, 76.61363895238598, 80.9769115755843, 93.82098305585171, 81L, 93L));
        add(new MStatistic(48L, 79L, 80L, 91L, 82L, 76.67077898641266, 80.54493610878225, 91.5520492183987, 78.37047387433043, 83L, 77L));
        add(new MStatistic(49L, 85L, 85L, 77L, 94L, 78.45289625341204, 94.82475761149392, 81.60984974894856, 92.35062946594033, 78L, 89L));

    }};

    private static List<QuestionStatistic> allQuestionStatistic = new LinkedList<QuestionStatistic>() {{
        add(new QuestionStatistic(1L, Lilunjichu, BlanksFilling, 3L, 1L));
        add(new QuestionStatistic(2L, Lilunjichu, BlanksFilling, 1L, 1L));
        add(new QuestionStatistic(3L, Lilunjichu, BlanksFilling, 2L, 1L));
    }};

    private static LocalDate buildDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    private static List<DoneTag> mockDoneTag = new LinkedList<DoneTag>() {{
        add(new DoneTag(1L, 1L, buildDate(2018, 5, 12)));
        add(new DoneTag(1L, 2L, buildDate(2018, 5, 12)));
        add(new DoneTag(1L, 3L, buildDate(2018, 5, 12)));
        add(new DoneTag(1L, 4L, buildDate(2018, 5, 13)));
        add(new DoneTag(1L, 5L, buildDate(2018, 5, 14)));
        add(new DoneTag(1L, 6L, buildDate(2018, 5, 15)));
        add(new DoneTag(1L, 7L, buildDate(2018, 5, 15)));
        add(new DoneTag(1L, 8L, buildDate(2018, 5, 16)));
        add(new DoneTag(1L, 9L, LocalDate.now()));
        add(new DoneTag(1L, 10L, LocalDate.now()));
    }};


    @Override
    public List<MStatistic> allStatistics() {
        return allStatistic;
    }

    @Override
    public MStatistic getUserStatisticByStudentId(Long studentId) {
        return allStatistic.stream()
                .filter(ms -> ms.getStudentId().equals(studentId))
                .findFirst().orElse(null);
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
    public List<QuestionStatistic> getQuestionStatisticByStudentId(Long studentId) {
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
    public void insertQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        allQuestionStatistic.add(new QuestionStatistic(studentId, classification, type, total, correct));
    }

    @Override
    public void updateQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        QuestionStatistic copied = null;

        Iterator<QuestionStatistic> iter = allQuestionStatistic.iterator();
        while (iter.hasNext()) {
            QuestionStatistic qs = iter.next();
            if (qs.getStudentId().equals(studentId) && qs.getClassification().equals(classification)
                    && qs.getType().equals(type)) {
                copied = new QuestionStatistic(qs.getStudentId(), qs.getClassification(), qs.getType(),
                        total, correct);
                iter.remove();
                break;
            }
        }
        if (copied == null) {
            throw new BusinessException("找不到更新对象");
        } else {
            allQuestionStatistic.add(copied);
        }

    }

    @Override
    public DoneTag getDoneTagByKeys(Long studentId, Long questionId) {
        for (DoneTag dt: mockDoneTag) {
            if (dt.getStudentId().equals(studentId) && dt.getQuestionId().equals(questionId)) {
                return dt;
            }
        }
        return null;
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
