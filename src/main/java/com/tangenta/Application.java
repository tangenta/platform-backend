package com.tangenta;

import com.tangenta.data.mapper.InitDataMapper;
import com.tangenta.data.mapper.UserMapper;
import com.tangenta.repositories.impl.TestQuestionRepository;
import com.tangenta.repositories.impl.TestUserRepository;
import com.tangenta.utils.generator.IdGenerator;
import com.tangenta.utils.generator.QuestionIdGenerator;
import com.tangenta.utils.generator.StudentIdGenerator;
import graphql.language.StringValue;
import graphql.schema.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@EnableAspectJAutoProxy
@SpringBootApplication
public class Application implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${upload-temp-dir}")
    private String fileUploadTmpPath;

    @Value("${profile-image-dir}")
    private String profileImgPath;

    @Value("${image-resource-path}")
    private String imageResourcePath;

    @Value("${student-id-padding-digit-number}")
    private int studentIdPaddingNumber;

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

    @Bean("questionIdGenerator")
    @Profile("dev")
    IdGenerator initQuestionIdGeneratorDev(InitDataMapper initDataMapper) {
        Long maxQuestionId = initDataMapper.getMaxQuestionId();
        QuestionIdGenerator ret = new QuestionIdGenerator(maxQuestionId == null ? 0L : maxQuestionId);
        logger.info("QuestionIdGenerator set up. Current max id: {}", ret.currentId());
        return ret;
    }

    @Bean("questionIdGenerator")
    @Profile("dev-test")
    IdGenerator initQuestionIdGeneratorDevTest() {
        return new QuestionIdGenerator(TestQuestionRepository.currentMaxLength);
    }

    @Bean("studentIdGenerator")
    @Profile("dev")
    IdGenerator initStudentIdGeneratorDev(InitDataMapper initDataMapper) {
        Long maxStudentId = initDataMapper.getMaxStudentId(LocalDate.now().getYear(), studentIdPaddingNumber);
        if (maxStudentId == null) maxStudentId = 0L;
        return new StudentIdGenerator(maxStudentId);
    }

    @Bean("studentIdGenerator")
    @Profile("dev-test")
    IdGenerator initStudentIdGeneratorDevTest() {
        return new StudentIdGenerator(TestUserRepository.currentMaxId);
    }

    @Bean
    GraphQLScalarType myGQLScalarType() {
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("YYYY-MM-DD")
                .coercing(new Coercing<LocalDate, String>(){
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        try {
                            if (dataFetcherResult instanceof LocalDate) {
                                LocalDate localDate = (LocalDate)dataFetcherResult;
                                return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
                            }
                        } catch (DateTimeException dte) {
                            throw new CoercingSerializeException("format exception '"
                                    + dte.getMessage() + "' for input " + dataFetcherResult);
                        }
                        throw new CoercingSerializeException("Invalid value '" + dataFetcherResult + "' for Local Date");
                    }

                    @Override
                    public LocalDate parseValue(Object input) throws CoercingParseValueException {
                        try {
                            if (input instanceof String) {
                                String date = (String)input;
                                return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                            }
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException("Invalid value '" + input + "' for Local Date");
                        }
                        throw new CoercingParseValueException("Invalid value '" + input + "' for Local Date");
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (!(input instanceof StringValue)) {
                            throw new CoercingParseLiteralException("Invalid value '" + input + "' for Local Date");
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

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(fileUploadTmpPath);
        File tmpFile = new File(fileUploadTmpPath);
        if (!tmpFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            tmpFile.mkdirs();
        }
        logger.info("temp dir: '{}' is set.", fileUploadTmpPath);
        return factory.createMultipartConfig();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("resource location: {}", Paths.get(profileImgPath).toUri().toString());
        registry.addResourceHandler(imageResourcePath + "**")
                .addResourceLocations(Paths.get(profileImgPath).toUri().toString());
    }

    //TODO: restore file system user profile images

}
