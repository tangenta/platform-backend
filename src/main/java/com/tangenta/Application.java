package com.tangenta;

import com.tangenta.data.mapper.QuestionIdFetchingMapper;
import com.tangenta.utils.QuestionIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
    QuestionIdGenerator initQuestionIdGeneratorDev(QuestionIdFetchingMapper questionIdFetchingMapper) {
        QuestionIdGenerator ret = new QuestionIdGenerator(questionIdFetchingMapper);
        logger.info("QuestionIdGenerator set up. Current max id: {}", ret.currentId());
        return ret;
    }


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
