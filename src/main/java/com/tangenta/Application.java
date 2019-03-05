package com.tangenta;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import com.tangenta.types.ErrorContainer;
import com.tangenta.types.LoginPayload;
import com.tangenta.types.RegisterPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication
public class Application implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    SchemaParserDictionary schemaParserDictionary() {
        return new SchemaParserDictionary()
                .add(LoginPayload.class)
                .add(RegisterPayload.class)
                .add(ErrorContainer.class);
    }

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
}
