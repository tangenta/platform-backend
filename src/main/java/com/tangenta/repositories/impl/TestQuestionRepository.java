package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.utils.QuestionIdGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestQuestionRepository implements QuestionRepository {
    public static final Long currentMaxLength = 2L;

    private QuestionIdGenerator questionIdGenerator;

    public TestQuestionRepository(QuestionIdGenerator questionIdGenerator) {
        this.questionIdGenerator = questionIdGenerator;
    }

    @Override
    public List<MQuestion> getAllQuestions() {
        return allQuestions;
    }

    @Override
    public List<MQuestion> getQuestionsByClassAndType(List<QuestionClassification> classifications, List<QuestionType> types) {
        return allQuestions.stream()
                .filter(q -> classifications.contains(q.getClassification()) && types.contains(q.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public MQuestion findQuestionById(Long questionId) {
        for (MQuestion q: allQuestions) {
            if (q.getQuestionId().equals(questionId)) return q;
        }
        return null;
    }

    @Override
    public void createQuestion(MQuestion q) {
        allQuestions.add(new MQuestion(q.getQuestionId(), q.getDescription(), q.getType(),
                q.getClassification(), q.getCorrectAnswer(), q.getAnswerDescription(),
                q.getPass(), q.getBelongToStudentId()));
    }

    private static List<MQuestion> allQuestions = new LinkedList<MQuestion>() {{
        add(new MQuestion(1L, "事物的价值是以（ ）为尺度的一种主客体统一的状态。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(2L, "贫穷不是社会主义，是谁提出的？（ ）", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "B", "略", true, 0L));
        add(new MQuestion(3L, "（ ）是信仰的目标化形象。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(4L, "新的社会主义本质观是（ ）和真理统一的本质观。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(5L, "社会主义价值体系的核心理念是什么？（ ）", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(6L, "价值和价值观的问题在哲学上是在何时星期的一个新的哲学的基本理论分支？（  ）", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(7L, "（ ）是价值观最核心、最实质的内容。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "B", "略", true, 0L));
        add(new MQuestion(8L, "人们关于基本价值的观念指的是（ ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(9L, "先秦时期就提出了“和而不同”、“和合中庸”、“政通人和”、“天人合一”、“协和万邦”等丰富多彩、意蕴深远的（ ）理念。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "A", "略", true, 0L));
        add(new MQuestion(10L, "科学发展观的核心是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "B", "略", true, 0L));
        add(new MQuestion(11L, "（  ），是我们党面对多样化的思想文化，价值观念的一个基本原则。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(12L, "社会主义民主是社会主义（  ）文明的重要标志。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(13L, "职业道德的核心是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "B", "略", true, 0L));
        add(new MQuestion(14L, "（  ）反映了“中国梦”的实现道路：中国特色社会主义。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "A", "略", true, 0L));
        add(new MQuestion(15L, "“爱国、敬业、诚信、友善”反映了“中国梦”的实现主体（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(16L, "近年来,我国加大在海外举办（ ）学院、文化交流中心和文艺演出、艺术活动等有效地在对外文化交往中传播社会主义核心价值观的重要作用。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "B", "略", true, 0L));
        add(new MQuestion(17L, "中国特色社会主义的总布局是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(18L, "（  ）是社会主义道德建设的基础。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "A", "略", true, 0L));
        add(new MQuestion(19L, "（  ）是公民个体道德化的摇篮。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(20L, "社会公德的内容不包括（  ）", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(21L, "职业道德的内容不包括（  ）", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(22L, "（  ）是高尚的，是服务精神的精髓。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "A", "略", true, 0L));
        add(new MQuestion(23L, "全面提高党的建设科学化水平，全党要增强紧迫感和责任感，姥姥把握的主线是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(24L, "开展党的群众路线教育实践活动，以（  ）为主要内容。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(25L, "中国共产党是根据自己的（  ），按照民主集中制组织起来的统一整体。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(26L, "对马克思主义的信仰，对社会主义和共产主义的信念，是共产党人的（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "A", "略", true, 0L));
        add(new MQuestion(27L, "党的十八届四中全会的主题是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(28L, "党内（  ）是党的生命。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(29L, "中国共产党的宗旨是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(30L, "2014件2月，习近平总书记在中央政治局第十三次集体学习时指出：“要把培育和弘扬（  ）作为凝魂聚气，强基固本的基础工程，作为一项根本任务，切实抓紧抓好。”", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(31L, "党的基层组织是党在社会基层组织中的（  ），是党的全部工作和战斗力的基础。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "B", "略", true, 0L));
        add(new MQuestion(32L, "党的（  ）纪律是密切党羽群众血肉联系的重要保证。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(33L, "（  ）是马克思主义政党的基本政治观点和根本工作路线。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(34L, "民族精神的核心是爱国主义，时代精神的核心是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "C", "略", true, 0L));
        add(new MQuestion(35L, "党的领导主要是（  ）的领导。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "B", "略", true, 0L));
        add(new MQuestion(36L, "党的思想路线是一起从实际出发，理论联系实际，（  ），在实践中检验真理和发展真理。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "A", "略", true, 0L));
        add(new MQuestion(37L, "党的群众路线教育实践活动要集中解决的“四风”问题是（  ）。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "A", "略", true, 0L));
        add(new MQuestion(38L, "协调推进“四个全面”的根本保证是（  ）。  ", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(39L, "我们现在所提倡的（  ），内在的吸收了历史上所创造的一切思想精华，是对马克思主义人本价值理念认识的发展和深化，体现着价值尺度从“物”到“人”的转移。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(40L, "建设社会主义核心价值体系，抓住了我国社会主义（  ）建设的关键和根本。", QuestionType.SingleChoice, QuestionClassification.Jiazhiguan, "D", "略", true, 0L));
        add(new MQuestion(41L, "正义包括哪两种？（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "AD", "略", true, 0L));
        add(new MQuestion(42L, "社会主义本质的北韩包裹下列哪些？（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(43L, "党作为核心主题的价值观表现为哪两个层次？（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "AD", "略", true, 0L));
        add(new MQuestion(44L, "价值观念就是人们关于基本价值的（  ）的总和。", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABD", "略", true, 0L));
        add(new MQuestion(45L, "社会主义核心价值观的基本特征，包括社会主义核心价值观的（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "AB", "略", true, 0L));
        add(new MQuestion(46L, "加强职业道德建设的基本途径（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(47L, "当代中国需要培育的国民心态包括（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(48L, "“讲文明、树新风”活动的具体包括以下内容（  ） ", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABC", "略", true, 0L));
        add(new MQuestion(49L, "创建文明城市、（  ）、（  ）是人民群众移风易俗、改造社会、建设美好生活的伟大创造。", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "CD", "略", true, 0L));
        add(new MQuestion(50L, "志愿精神的主要内容包括（  ）。", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(51L, "弘扬传统节日文化，发挥其所具有的强烈的（ ）认同感、（ ）认同感、（ ）认同感，能够最大限度地凝聚民心，有力地抵制西方文化的冲击和渗透。", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABC", "略", true, 0L));
        add(new MQuestion(52L, "目前，我国社会发展面对的挑战有（  ）。", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ACD", "略", true, 0L));
        add(new MQuestion(53L, "以下对树立政府和公共管理者的公共利益典范理解正确的是（  ）。", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABC", "略", true, 0L));
        add(new MQuestion(54L, "培育和践行社会主义核心价值观的重大意义有（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(55L, "如何把培育和践行核心价值观融入国民教育全过程？（  ）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(56L, "十八大提出如何扎实推进社会主义文化强国？", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(57L, "“中国梦”有哪些时代特征？（  ） ", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(58L, "人的全面发展的基本内涵包括（  ）。", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(59L, "为什么说以爱国主义为核心的民族精神具有重大的现实意义和时代价值？（）", QuestionType.MultipleChoice, QuestionClassification.Jiazhiguan, "ABCD", "略", true, 0L));
        add(new MQuestion(60L, "一切好与坏的现象都有一个根本特点、一个根本标准、一个根本前提、就是好与坏总是因人而异。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(61L, "主导价值观的建设是决定我们未来命运和前提的一件大事。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(62L, "现在价值论已经是哲学理论基础的正式组成部分。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(63L, "价值观的建设是事关党和国家的全局、国家和民族的命运全局的大事，是事关我们长远的大事。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(64L, "看待人的价值观念，更为重要的是理解人的社会存在。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(65L, "马克思主义价值论告诉我们，事物的价值是在主客观体关系中形成的，它是以主体为尺度的一种主客观体统一的状态。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(66L, "价值论就是探讨价值的本质规律的一个学科分支。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(67L, "科学发展观的核心是以人为本。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(68L, "事物的价值是以主体为尺度的一种主客体统一的状态。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(69L, "理想是信仰的目标化形象。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(70L, "新的社会主义本质观是价值和真理统一的本质观。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(71L, "社会主义价值体系的核心理念是公平。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(72L, "价值和价值观的问题在哲学上是在十九世纪末二十世纪初兴起的一个新的哲学的基本理论分支。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(73L, "信仰是价值观最核心、最实质的内容。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(74L, "人们关于基本价值的观念指的是价值观念。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(75L, "正义包括以自由为核心的正义和以公平为核心的正义。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(76L, "党作为核心主题的价值观表现为当自己的价值观。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "F", "党作为核心主题的价值观表现为党自己的价值观和代表全国人民表达和提出的价值观。", true, 0L));
        add(new MQuestion(77L, "价值观念就是人们关于基本价值的信念、信仰和理想的总和。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(78L, "社会公德是社会主义道德建设的基础。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(79L, "社会公德是公民个体道德化的摇篮。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "F", "家庭美德是公民个体道德化的摇篮。", true, 0L));
        add(new MQuestion(80L, "奉献精神是高尚的，是志愿服务精神的精髓。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(81L, "职业道德的内容仅包括保护环境。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "F", "职业道德的内容包括爱岗敬业、服务群众和奉献社会。", true, 0L));
        add(new MQuestion(82L, "以人与自然和谐为核心的生态文明是对工业文明反思的必然要求。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(83L, "贫穷是产生社会矛盾的重要根源，而发展则是促进社会和谐的基础。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(84L, "社会主义核心价值观必须广泛借鉴世界文明成果，符合人类最美好的价值追求。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(85L, "法律是硬约束、道德是软约束，要做到“法以诛恶，得意劝善”，而这功能能互补、相辅相成。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(86L, "“自由、平等、公正、法治”反映了“中国梦”的社会属性：资本主义性质。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "F", "“自由、平等、公正、法治”反映了“中国梦”的社会属性：社会主义性质。", true, 0L));
        add(new MQuestion(87L, "“诚”与“信”有着密切的联系。诚是人内在的德信，信则是诚的外在表现。诚于中，必信于外。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(88L, "维护社会公正是形成橄榄型社会的主要途径。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "T", "略", true, 0L));
        add(new MQuestion(89L, "社会主义民主是社会主义精神文明的重要标志。", QuestionType.TrueOrFalse, QuestionClassification.Jiazhiguan, "F", "社会主义民主是社会主义政治文明的重要标志。", true, 0L));
        add(new MQuestion(90L, "为什么要坚持马克思主义的指导地位？根本原因就在于马克思主义是社会主义核心价值观的（ ）。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "灵魂和精髓", "略", true, 0L));
        add(new MQuestion(91L, "（ ），是党领导人民治理国家的基本方略，是社会文明进步的重要标志。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "依法治国", "略", true, 0L));
        add(new MQuestion(92L, "（ ），为依法治国提供思想基础和精神支柱。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "增强法制观念", "略", true, 0L));
        add(new MQuestion(93L, "中华民族形成了以（  ）为核心的团结统一、爱好和平、勤劳勇敢、自强不息的伟大民族精神。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "爱国主义", "略", true, 0L));
        add(new MQuestion(94L, "爱国主义教育要抓住（ ）这个重点。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "广大青少年", "略", true, 0L));
        add(new MQuestion(95L, "倡导以（）、（）为主要内容的职业道德。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "爱岗敬业，奉献社会", "略", true, 0L));
        add(new MQuestion(96L, "以（）为核心的民族精神和以（）为核心的时代精神，是社会主义核心价值体系的精髓。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "爱国主义，改革创新", "略", true, 0L));
        add(new MQuestion(97L, "社会主义核心价值观必须立足（），符合广大人民群众的期待。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "中国特色社会主义伟大实践", "略", true, 0L));
        add(new MQuestion(98L, "社会主义核心价值观24字中，（）属于社会层面。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "自由、平等、公正、法治", "略", true, 0L));
        add(new MQuestion(99L, "社会主义核心价值观24字中，（）属于国家层面。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "富强、民主、文明、和谐", "略", true, 0L));
        add(new MQuestion(100L, "社会主义核心价值观24字中，（）属于个人层面。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "爱国、敬业、诚信、友善", "略", true, 0L));
        add(new MQuestion(101L, "志愿服务以（）、（）为前提，以弘扬志愿精神为核心。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "自愿、无偿", "略", true, 0L));
        add(new MQuestion(102L, "（）、（）、（）是我国党和政府处理民族关系的基本原则。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "民族平等、民族团结、各民族共同繁荣", "略", true, 0L));
        add(new MQuestion(103L, "大力整治交通秩序。全面开展“（）”，大力倡导六大文明交通行为，自觉摒弃六大交通陋习。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "文明交通行动计划", "略", true, 0L));
        add(new MQuestion(104L, "科学发展观的核心是（）。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "以人为本", "略", true, 0L));
        add(new MQuestion(105L, "（）是公民个体道德化的摇篮。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "家庭美德", "略", true, 0L));
        add(new MQuestion(106L, "（）是高尚的，是志愿服务精神的精髓。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "奉献精神", "略", true, 0L));
        add(new MQuestion(107L, "中国特色社会主义的总布局是（）。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "经济、政治、文化、社会、生态文明建设“五位一体”", "略", true, 0L));
        add(new MQuestion(108L, "（）是社会主义道德建设的基础", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "社会公德", "略", true, 0L));
        add(new MQuestion(109L, "弘扬传统节日文化，发挥其所具有的强烈的（）认同感、（）认同感、（）认同感，能够最大限度地凝聚民心，有力地抵制西方文化的冲击和渗透。", QuestionType.BlanksFilling, QuestionClassification.Jiazhiguan, "民族、文化、国家", "略", true, 0L));
        add(new MQuestion(110L, "党的（  ）通过的《关于社会主义精神文明建设指导方针的决议》，为加强社会主义精神文明建设制定了第一个纲领文件。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(111L, "“三个代表”重要思想是在科学判断（  ）基础上提出来的。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(112L, "党的十六届四中全会通过了《中共中央关于（  ）的决定》。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(113L, "河南省（  ）已列入《世界文化遗产名录》。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(114L, "不说粗话脏话，不随地吐痰，不损坏公物，是符合（  ）的良好行为。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(115L, "新的历史条件下，党执政的一个基本方式是（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(116L, "我国的基本经济制度是（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(117L, "人们接受道德教育最早的地点是（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(118L, "党的十六大报告指出：必须尊重劳动力，尊重知识，尊重人才，尊重（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(119L, "邓小平理论的精髓为（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(120L, "提高党的执政能力，关键在于搞好（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(121L, "公民基本道德规范是：（  ）、明礼诚信、团结友善、勤俭自强、敬业奉献。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(122L, "公民道德建设的基本要求是爱祖国、爱人民、爱劳动、爱科学、（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(123L, "（  ）是最活跃的、最革命的因素，是社会发展的最终决定力量。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(124L, "从未成年人抓起，培养和早就千千万万具有高尚思想品质和良好道德修养的合格（  ）和接班人，既是一项长远的战略任务，又是一项紧迫的的现实任务。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(125L, "包括（  ）在内的工人阶级、广大农民、始终是推动我国先进生产力发展和社会全面进步的根本力量。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(126L, "有没有高昂的（  ），是衡量一个国家综合国力强弱的重要尺度。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(127L, "河南省加强和改进未成年人思想道德建设总的活动载体是（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(128L, "科学发展观的本质和核心是（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(129L, "中国共产党必须翅中代表中国先进生产力的发展要求，代表（  ），代表中国最广大人民的根本利益。这是对“三个代表”重要思想的集中概括。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(130L, "中共文明委确定每年的9月20日是（   ）宣传日。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(131L, "“三个代表”重要思想的本质是：立党为公、（   ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(132L, "“三讲一树”活动的内容是（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(133L, "坚持最广泛、最充分地调动一切积极因素，不断提高构建社会主义（  ）的能力，是提高党的执政能力的任务之一。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(134L, " 党的十六大报告提出要“确立劳动、资本、技术和管理等生产要素按（  ）参与分配的原则。”", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(135L, "社会主义民主政治的本质是（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(136L, "公民道德建设的过程，是教育和（  ）相结合的过程。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(137L, "中国共产党最大的政治优势是（   ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(138L, "贯彻“三个代表”重要思想，关键在坚持与时俱进，核心在坚持党的先进性，本质在坚持（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(139L, "我国非配制度中初次分配和再分配的原则是初次分配注重效率，发挥（  ）的作用，再分配注重公平。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(140L, "在群众性精神文明创建活动中形成的三大创建活动是之创建文明城市、文明村镇、（   ）活动。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(141L, "当今时代的主题是：（  ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(142L, "公民道德必须坚持社会主义道德建设与（  ）相适应的方针原则。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(143L, "开展创建文明单位活动，要以（  ）为中心。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(144L, "全面建设小康社会的政治保证是（   ）。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(145L, "提高党的执政能力，首先要提高党的领导（  ）的能力。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "B", "略", true, 0L));
        add(new MQuestion(146L, "加强社会主义（  ），始发站下您文化的重要内容和中心环节。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(147L, "发展先进文化，就是发展面向（  ）、面向世界、面向未来的，民族的科学的大众的社会主义文化。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(148L, "以（  ）为重点，这是十六大阐述加强思想道德建设是提出的一个重要论断。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "C", "略", true, 0L));
        add(new MQuestion(149L, "（  ）是人民群众移风易俗、改造社会的伟大创造。", QuestionType.SingleChoice, QuestionClassification.Daodepingjia, "A", "略", true, 0L));
        add(new MQuestion(150L, "社会主义精神文明建设包括（  ）、（  ）两个方面的内容。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "CD", "略", true, 0L));
        add(new MQuestion(151L, "在中国八大古都中，河南省有洛阳、（  ）、（  ）、（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "BCD", "略", true, 0L));
        add(new MQuestion(152L, "“三讲一树”活动的内容是（  ）、（  ）、（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABC", "略", true, 0L));
        add(new MQuestion(153L, "“四进社区”指的是“科教、（  ）、（  ）、（  ）”进社区。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "BCD", "略", true, 0L));
        add(new MQuestion(154L, "河南新石器时代的代表文明是（  ）、（  ）、（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABD", "略", true, 0L));
        add(new MQuestion(155L, "科学发展观的主要内容包括：（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABC", "略", true, 0L));
        add(new MQuestion(156L, "未成年人思想建设的三个环节是：（   ）", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABD", "略", true, 0L));
        add(new MQuestion(157L, "我国政府主张海峡两岸尽快实现的“三通”是指（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABC", "略", true, 0L));
        add(new MQuestion(158L, "公民道德建设的着力点是（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABC", "略", true, 0L));
        add(new MQuestion(159L, "文明单位是社会主义（  ）建设中的综合性荣誉称号。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ACD", "略", true, 0L));
        add(new MQuestion(160L, "共产党要将修养的原因包括（   ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABCD", "略", true, 0L));
        add(new MQuestion(161L, "温家宝说：“道德是世界上最伟大的，她的光芒甚至比太阳还要灿烂。”此论述表达了（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "BC", "略", true, 0L));
        add(new MQuestion(162L, "我国古代官员道德建设有一套较为完整的保障制度，包括（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABCD", "略", true, 0L));
        add(new MQuestion(163L, "我国古代官德大致包括（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABCD", "略", true, 0L));
        add(new MQuestion(164L, "“正”是我国古代官员职业道德的重要内容，其含义有（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "AB", "略", true, 0L));
        add(new MQuestion(165L, "“慎”是我国古代官员职业道德的重要内容，其含义有（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ACD", "略", true, 0L));
        add(new MQuestion(166L, "《公务员法》将公务员的基本素质要求归结为五大方面，除了勤、绩之外，还包括（  ）方面。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABD", "略", true, 0L));
        add(new MQuestion(167L, "行政伦理是政府行政过程中所应当确立和遵守的伦理价值、原则和规范，它是整个政府管理的价值观念体系，包括以下层次：（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABCD", "略", true, 0L));
        add(new MQuestion(168L, "共产党员修养的主要内容包括（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABC", "略", true, 0L));
        add(new MQuestion(169L, "公务员的职业纪律就类别来说，有（  ）。", QuestionType.MultipleChoice, QuestionClassification.Daodepingjia, "ABD", "略", true, 0L));
        add(new MQuestion(170L, "党必须始终紧紧抓住发展这个执政兴国的第一要务。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(171L, "任何时候都不能以牺牲精神文明为代价换区经济一时的发展。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(172L, "社会主义道德风尚的形成，巩固和发展，要考教育们也要靠法制。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(173L, "开展创建文明单位活动，要以生产经营和业务工作为中心。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(174L, "文明单位是社会主义物质文明、政治文明、精神文明建设中的综合性荣誉称号。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(175L, "有没有高昂的民族精神，是衡量一个国家综合实力强弱的重要尺度。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(176L, "爱国主义和社会主义本质上是一致的。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(177L, "在改革发展稳定的关系中，稳定是前提、改革是动力、发展是目的。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(178L, "科学发展观就是以人为本、全面发展，协调发展和可持续发展。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(179L, "公民道德建设必须坚持注重效率与维护社会公平相协调。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(180L, "社会主义道德建设的原则是集体主义。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(181L, "公有制实现形式可以而且应当多样化。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(182L, "公民道德建设过程是教育和时间相结合的过程。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(183L, "思想道德建设是社会主义精神文明建设的中心环节。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(184L, "精神文明建设必须紧紧围绕经济建设这个中心。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(185L, "党的领导是依法治国的根本保证。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(186L, "改革是全面的改革，既包括体制层面又包括思想观念层面。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(187L, "公民道德建设必须坚持继承优良传统和发扬时代精神相结合。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(188L, "坚持注重效率与维护社会公平相协调是公民道德建设的一个重要方针原则。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(189L, "社会主义精神文明建设实社会主义社会的重要特征。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "T", "略", true, 0L));
        add(new MQuestion(190L, "群众性精神文明建设，要从创建文明家庭这个社会细胞开始。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "群众性精神文明建设，要从优化家庭这个社会细胞开始。", true, 0L));
        add(new MQuestion(191L, "建设精神文明的关键在人民。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "建设精神文明的关键在党。", true, 0L));
        add(new MQuestion(192L, "公民道德建设的原则是社会公德。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "公民道德建设的原则是集体主义。", true, 0L));
        add(new MQuestion(193L, "生产力和生产关系是社会基本矛盾。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "生产力和生产关系、经济基础和上层建筑的矛盾是社会基本矛盾。", true, 0L));
        add(new MQuestion(194L, "思想道德教育是人民群众移风易俗、改造社会的伟大创造。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "群众性精神文明创建活动是人民群众移风易俗、改造社会的伟大创造。", true, 0L));
        add(new MQuestion(195L, "加强社会主义文学艺术事业，是发展先进文化的重要内容和中心环节。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "加强社会主义思想道德建设，是发展先进文化的重要内容和中心环节。", true, 0L));
        add(new MQuestion(196L, "未成年人思想建设的三个环节是：学校、家庭、单位。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "未成年人思想建设的三个环节是：学校、家庭、社会。", true, 0L));
        add(new MQuestion(197L, "全面建设小康社会的政治保证是政治文明。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "全面建设小康社会的政治保证是党的领导。", true, 0L));
        add(new MQuestion(198L, "科学发展观的主要内容包括：全面发展观、协调发展观和跨越发展观。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "科学发展观的主要内容包括：全面发展观、协调发展观和可持续发展观。", true, 0L));
        add(new MQuestion(199L, "公民道德建设的过程，是教育和监督相结合的过程。", QuestionType.TrueOrFalse, QuestionClassification.Daodepingjia, "F", "公民道德建设的过程，是教育和实践相结合的过程。", true, 0L));
        add(new MQuestion(200L, "面对世界范围各种思想文化的相互激荡，必须把弘扬和培育（  ）作为文化建设即为重要的任务，纳入精神文明建设全过程。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "民族精神", "略", true, 0L));
        add(new MQuestion(201L, "“四进社区”指的是（  ）、文体、法律、卫生进社区。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "科教", "略", true, 0L));
        add(new MQuestion(202L, "公民基本道德规范是：爱国守法、（  ）、团结友善、勤俭自强、敬业奉献。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "明礼诚信", "略", true, 0L));
        add(new MQuestion(203L, "我们进行的精神文明建设，是以经济建设为中心，坚持四项基本原则和坚持（  ）的精神文明建设。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "改革开放", "略", true, 0L));
        add(new MQuestion(204L, "将强和改进未成年人思想道德建设，要把（  ）与社会教育、学校教育紧密结合起来。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "家庭教育", "略", true, 0L));
        add(new MQuestion(205L, "群众性精神文明创建活动始于20世纪80年代初全国各地开展的（  ）活动。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "五讲四美三热爱", "略", true, 0L));
        add(new MQuestion(206L, "开展创建文明城市活动要以提高市民（  ）和城市文明程度为目标。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "文明素质", "略", true, 0L));
        add(new MQuestion(207L, "“三个代表”重要思想的本质是立党为公、（   ）。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "执政为民", "略", true, 0L));
        add(new MQuestion(208L, "（  ）是我们党的立党之本、执政之基、力量之源。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "“三个代表”重要思想", "略", true, 0L));
        add(new MQuestion(209L, "要大力倡导以爱岗敬业、诚实守信、（  ）、服务群众、奉献社会为主要内容分的职业道德。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "办事公道", "略", true, 0L));
        add(new MQuestion(210L, "全面推进党的建设新的伟大工程，必须以提高党的（  ）为重点。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "执政能力", "略", true, 0L));
        add(new MQuestion(211L, "发展是党执政兴国的（  ）。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "第一要务", "略", true, 0L));
        add(new MQuestion(212L, " 在群众性精神文明创建活动中形成的三大创建活动是：创建（  ）、创建文明村镇和创建文明行业活动。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "文明城市", "略", true, 0L));
        add(new MQuestion(213L, "要大力倡导以文明礼貌、助人为乐、爱护公物、（  ）、遵纪守法为主要内容的社会公德。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "保护环境", "略", true, 0L));
        add(new MQuestion(214L, "在五千多年的发展中，中华民族形成了以（  ）为核心的伟大民族精神。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "爱国主义", "略", true, 0L));
        add(new MQuestion(215L, "新时期中国最鲜明的特征是（  ）。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "改革开放", "略", true, 0L));
        add(new MQuestion(216L, "（  ）是社会主义的自我完善和发展，是经济和社会发展的强大动力。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "改革", "略", true, 0L));
        add(new MQuestion(217L, "要大力倡导以尊老爱幼、男女平等、夫妻和睦、勤俭持家、（  ）为主要内容的家庭美德。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "邻里团结", "略", true, 0L));
        add(new MQuestion(218L, "公民道德建设必须坚持尊重个人合法权益与承担（  ）相统一。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "社会责任", "略", true, 0L));
        add(new MQuestion(219L, "公民道德建设要坚持先进性要求和（  ）要求相结合。", QuestionType.BlanksFilling, QuestionClassification.Daodepingjia, "广泛性", "略", true, 0L));
    }};
}
