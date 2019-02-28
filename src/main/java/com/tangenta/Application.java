package com.tangenta;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import com.tangenta.data.mapper.UserMapper;
import com.tangenta.types.ErrorContainer;
import com.tangenta.types.LoginPayload;
import com.tangenta.types.LoginResult;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    SchemaParserDictionary schemaParserDictionary() {
        return new SchemaParserDictionary()
                .add(LoginPayload.class)
                .add(ErrorContainer.class);
    }
//
//    @Bean
//    UserMapper userMapper(SqlSessionFactory sqlSessionFactory) {
//        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
//        return sessionTemplate.getMapper(UserMapper.class);
//    }
}
