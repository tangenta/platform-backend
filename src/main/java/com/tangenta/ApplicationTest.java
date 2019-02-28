package com.tangenta;

import com.tangenta.data.mapper.UserMapper;
import com.tangenta.data.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ApplicationTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao(MyBatisConnectionFactory.getSqlSessionFactory());
        List<User> users = userDao.getAllUsers();
    }
}

class UserDao {
    private SqlSessionFactory mSqlSessionFactory = null;

    UserDao(SqlSessionFactory sqlSessionFactory) {
        mSqlSessionFactory = sqlSessionFactory;
    }

    List<User> getAllUsers() {
        List<User> list = null;

        try (SqlSession session = mSqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            list = mapper.getAllUsers();
        }
        System.out.println("selectAll() --> " + list);
        list.forEach(System.out::println);
        return list;
    }
}

class MyBatisConnectionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {

            String resource = "config-test.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
    static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}