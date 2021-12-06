
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


public class TestCombo {


    @Test
    public void mybatisConn() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        System.out.println("----list category combo [一对多测试]-------");
        List<Category> categories = session.selectList("listCategoryCombo");
        categories.stream().forEach(c -> {
            System.out.println(c.toString());
            System.out.println(String.format("associated product size : %s", c.getProducts().size()));
            c.getProducts().stream().forEach(p -> System.out.println(p.toString()));
            System.out.println("-----------");
        });

        System.out.println("----list product combo [多对一测试]-------");
        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p + " 对应的分类是 \t " + p.getCategory());
        }

        listOrder(session);
        addOrderItem(session);
        listOrder(session);
        deleteOrderItem(session);
        listOrder(session);
    }
    
    private static void listOrder(SqlSession session) {
        System.out.println("------------list order [多对多测试]--------------------");
        List<Order> os = session.selectList("listOrder");
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois = o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(),
                        oi.getNumber());
            }
        }
    }

    private static void addOrderItem(SqlSession session) {
        System.out.println("------------insert order [新增多对多测试]--------------------");

        Order o1 = session.selectOne("getOrder", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        oi.setNumber(200);

        session.insert("addOrderItem", oi);
    }

    private static void deleteOrderItem(SqlSession session) {
        System.out.println("------------delete order [删除多对多测试]--------------------");

        Order o1 = session.selectOne("getOrder", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        session.delete("deleteOrderItem", oi);
    }

    public void main(String[] args) throws IOException {

    }
}