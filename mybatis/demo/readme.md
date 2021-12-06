# mybatis 入门demo测试

2021-11-20

网上搜索mybatis tutorial，找到一个例子跟着走了一遍，基础概念至少有感性认识了, 吐槽的xml文件也能看懂了。

顺便把junit test简单过了一遍，可以搞起unit test了， mvn test走起。

- 网上的例子教程

https://how2j.cn/k/mybatis/mybatis-tutorial/1087.html

- 官方文档对照查看

https://mybatis.org/mybatis-3/zh/getting-started.html

- 关键词快速查找

https://mybatis.org/mybatis-3/sqlmap-xml.html

- 需要提前准备的sql数据

```sql

create database mybatis;
CREATE USER 'dev'@'%' IDENTIFIED BY 'dev';
GRANT ALL PRIVILEGES ON * . * TO 'dev'@'%';
flush privileges;

use mybatis;

CREATE TABLE category_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO category_ VALUES (null,'category1');
INSERT INTO category_ VALUES (null,'category2');


create table product_(
id int NOT NULL AUTO_INCREMENT,
name varchar(30)  DEFAULT NULL,
price float  DEFAULT 0,
cid int ,
PRIMARY KEY (id)
)AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

delete from category_;
INSERT INTO category_ VALUES (1,'category1');
INSERT INTO category_ VALUES (2,'category2');
delete from product_;
INSERT INTO product_ VALUES (1,'product a', 88.88, 1);
INSERT INTO product_ VALUES (2,'product b', 88.88, 1);
INSERT INTO product_ VALUES (3,'product c', 88.88, 1);
INSERT INTO product_ VALUES (4,'product x', 88.88, 2);
INSERT INTO product_ VALUES (5,'product y', 88.88, 2);
INSERT INTO product_ VALUES (6,'product z', 88.88, 2);



create table order_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  code varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 
create table order_item_(
  id int(11) NOT NULL AUTO_INCREMENT, 
  oid int ,
  pid int ,
  number int ,
  PRIMARY KEY(id)
)AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO order_ VALUES (1,'code000A');
INSERT INTO order_ VALUES (2,'code000B');
 
INSERT INTO order_item_ VALUES (null, 1, 1, 100);
INSERT INTO order_item_ VALUES (null, 1, 2, 100);
INSERT INTO order_item_ VALUES (null, 1, 3, 100);
INSERT INTO order_item_ VALUES (null, 2, 2, 100);
INSERT INTO order_item_ VALUES (null, 2, 3, 100);
INSERT INTO order_item_ VALUES (null, 2, 4, 100);



CREATE TABLE student(
  id int(11) NOT NULL AUTO_INCREMENT,
  studentID int(11) NOT NULL UNIQUE,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO student VALUES(1,1,'abc');
INSERT INTO student VALUES(2,2,'def');
INSERT INTO student VALUES(3,3,'hij');
```

- 提示?

通过Mybatis执行多条sql语句需要增加一个参数：allowMultiQueries
 ```
<property name="url" value="jdbc:mysql://localhost:3306/how2java?characterEncoding=UTF-8&amp;allowMultiQueries=true"/>
```
