
package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.jupiter.api.Test;

import com.example.demo.Category;

public class TestCategory {

    private void listAll(SqlSession sqlSession, String info) {
        System.out.println("------------" + info + "-----------------");
        List<Category> categories = sqlSession.selectList("listCategory");
        for(Category category : categories){
            System.out.println(category.toString());
        }
        System.out.println("-----------" + info + "--------------------");

    }

    @Test
    public void mybatisConn() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        listAll(session, "original category");

        System.out.println("-----------新增category----------------");
        Category c = new Category();
        c.setName("巴拉巴拉");
        session.insert("addCategory", c);
        listAll(session, "After add category");

        System.out.println("-----------选择category---------------");
        Integer index = ((Category) session.selectList("listCategory").get(0)).getId(); // 返回的竟然是object，需要转化才行。
        Category getOne = session.selectOne("getCategory", index);
        System.out.println(getOne.toString());

        System.out.println("-----------修改category---------------");
        getOne.setName("修改巴拉巴拉小魔仙");
        session.update("updateCategory", getOne);
        listAll(session, "修改了");

        System.out.println("---单条件模糊查询---");
        session.selectList("listCategoryByName", "小魔仙").stream().forEach(item -> {
            System.out.println(item.toString());
        });

        System.out.println("---查询多条件----");
        Map<String, Object> params = new HashMap<>();  // 多条件查询竟然是构造一个map传递进去
        params.put("id", 6);
        params.put("name", "巴拉");
        session.selectList("listCategoryByIdAndName", params).stream().forEach(item -> {
            System.out.println(item.toString());
        });

        System.out.println("-----删除------");
        Integer totalCount = session.selectList("listCategory").size();
        IntStream.range(index, index + totalCount - 3).forEach(idx -> {
            Category toDelete = new Category();
            toDelete.setId(idx);
            session.delete("deleteCategory", toDelete);
            System.out.println(String.format("delete %s", idx));
        });
        listAll(session, "after delete all");

        session.commit();
        session.close();
    }

    public void main(String[] args) throws IOException {

    }
}