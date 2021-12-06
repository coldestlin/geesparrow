## sqlformat 测试

2021-11-16


测试了一下，看起来可以把空格制表符和换行符之类去掉。

https://github.com/vertical-blank/sql-formatter


- 编译与执行
```
mvn clean package assembly:single 
java -jar target/sql-format-1.0-SNAPSHOT-jar-with-dependencies.jar
```

- java8是否不能编译运行

>Building this library requires JDK 11 because of ktfmt.

按照readme的说法，只能够java11才能编译，但是本地测试java8也能够编译运行。

```

(ocx) ➜  sql-format git:(main) ✗ jenv local
11
(ocx) ➜  sql-format git:(main) ✗ jenv versions
  system
  1.8
  1.8.0.282
* 11 (set by /Users/geehanlin/projects/geesparrow/sql-formatter/sql-format/.java-version)
  11.0
  11.0.10
  openjdk64-1.8.0.282
  openjdk64-11.0.10
(ocx) ➜  sql-format git:(main) ✗ jenv local 1.8
(ocx) ➜  sql-format git:(main) ✗ java -version
openjdk version "1.8.0_282"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_282-b08)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.282-b08, mixed mode)
```

- 其他支持的配置项

```java
SqlFormatter.format('SELECT * FROM tbl',
  FormatConfig.builder()
    .indent("    ") // Defaults to two spaces
    .uppercase(true) // Defaults to false (not safe to use when SQL dialect has case-sensitive identifiers)
    .linesBetweenQueries(2) // Defaults to 1
    .maxColumnLength(100) // Defaults to 50
    .params(Arrays.asList("a", "b", "c")) // Map or List. See Placeholders replacement.
    .build()
);
```


- 测试内容

```java

        List<String> arrs = Arrays.asList("select       a\t \n\r from table1;  \n", "select b from \n table2   \n");
        for (String x : arrs) {
            System.out.println("----");
            System.out.println(x);
            System.out.println("|||||");
            System.out.println(SqlFormatter.format(x));
            System.out.println("----\n\n");
        }
    }
```

```bash

(ocx) ➜  sql-format git:(main) ✗ java -jar target/sql-format-1.0-SNAPSHOT-jar-with-dependencies.jar
----
select       a   
 from table1;  

|||||
select
  a
from
  table1;
----


----
select b from 
 table2   

|||||
select
  b
from
  table2
----

```