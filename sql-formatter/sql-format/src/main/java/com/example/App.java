package com.example;

import java.util.List;
import java.util.Arrays;
import com.github.vertical_blank.sqlformatter.SqlFormatter;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        List<String> arrs = Arrays.asList("selECT       a\t \n\r from table1;  \n", "select b from \n table2   \n", "select mul from\t\r\n abc; select abc\n\r\t from tbbs");
        for (String x : arrs) {
            System.out.println("----");
            System.out.println(x);
            System.out.println("|||||");
            System.out.println(SqlFormatter.format(x));
            System.out.println("----\n\n");
        }
    }
}
