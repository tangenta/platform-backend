package com.tangenta;

import com.tangenta.data.mapper.QuestionIdFetchingMapper;
import com.tangenta.repositories.impl.TestQuestionRepository;
import com.tangenta.utils.QuestionIdGenerator;
import graphql.language.StringValue;
import graphql.schema.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


@SpringBootApplication
public class Application implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Bean
    ServletWebServerFactory servletWebServerFactory(){
        return new TomcatServletWebServerFactory();
    }

    @Bean
    @Profile("dev")
    QuestionIdGenerator initQuestionIdGeneratorDev(QuestionIdFetchingMapper questionIdFetchingMapper) {
        Long maxQuestionId = questionIdFetchingMapper.getMaxQuestionId();
        QuestionIdGenerator ret = new QuestionIdGenerator(maxQuestionId == null ? 0L : maxQuestionId);
        logger.info("QuestionIdGenerator set up. Current max id: {}", ret.currentId());
        return ret;
    }

    @Bean
    @Profile("dev-test")
    QuestionIdGenerator initQuestionIdGeneratorDevTest() {
        return new QuestionIdGenerator(TestQuestionRepository.currentMaxLength);
    }

    @Bean
    GraphQLScalarType myGQLScalarType() {
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("YYYY-MM-DD")
                .coercing(new Coercing<Date, String>(){
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof Date) {
                            Date date = (Date)dataFetcherResult;
                            LocalDate localDate = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
                            return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
                        } else {
                            throw new CoercingSerializeException("Invalid value '" + dataFetcherResult + "' for Date");
                        }
                    }

                    @Override
                    public Date parseValue(Object input) throws CoercingParseValueException {
                        if (input instanceof String) {
                            String date = (String)input;
                            try {
                                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                                return Date.from(Instant.from(localDate.atStartOfDay(ZoneOffset.UTC)));
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseValueException("Invalid value '" + input + "' for Date");
                            }
                        } else {
                            throw new CoercingParseValueException("Invalid value '" + input + "' for Date");
                        }
                    }

                    @Override
                    public Date parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (!(input instanceof StringValue)) {
                            throw new CoercingParseLiteralException("Invalid value '" + input + "' for Date");
                        }
                        String value = ((StringValue) input).getValue();

                        try {
                            return parseValue(value);
                        } catch (CoercingParseValueException e) {
                            throw new CoercingParseLiteralException(e.getMessage());
                        }
                    }
                })
                .build();
    }

    // uncomment it to load file data into database
//    @Bean
//    QuestionImport initQuestionImport(QuestionMapper questionMapper, QuestionSolutionMapper questionSolutionMapper) {
//        QuestionImport questionImport = new QuestionImport();
//        try {
//            QuestionImport2.readQuestions("D:\\questionData\\question.txt",
//                    q -> q.forEach(questionMapper::createQuestion),
//                    qs -> qs.forEach(i -> questionSolutionMapper.createQuestionSolution(i.getQuestionId(), i.getOption())));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return questionImport;
//    }


//    @Bean
//    SchemaParserDictionary schemaParserDictionary() {
//        return new SchemaParserDictionary()
//                .add(LoginPayload.class)
//                .add(RegisterPayload.class)
//                .add(ErrorContainer.class);
//    }

    //    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        logger.info("addResourceHandlers called.");
//        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
//    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/jsp/html/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(JstlView.class);
//        registry.viewResolver(resolver);
//    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Bean
//    ConfigurationCustomizer myBatisConfigurationCustomizer() {
//        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(Configuration configuration) {
//                org.apache.ibatis.logging.LogFactory.useLog4JLogging();
//
//            }
//        };
//    }
}
