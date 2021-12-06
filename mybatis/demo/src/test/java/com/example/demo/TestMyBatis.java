
package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.jupiter.api.Test;

import com.example.demo.Category;

public class TestMyBatis {

    @Test
    public void mybatisConn() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }

        System.out.println("--------------------------------");

        List<Student> ss = session.selectList("listStudent");
        for (Student s : ss) {
            System.out.println(s.toString());
        }

    }

    public void main(String[] args) throws IOException {
       
    }
}