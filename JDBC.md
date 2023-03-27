# JDBC基础

## JDBC概念
- 使用Java语言操作关系型数据库的一套API

## JDBC本质
- 官方定义的一套操作所有关系型数据库的规则(接口)
- 各个数据库厂商去实现这套接口,提供数据库驱动jar包
- 我们可以使用这套接口编程

## JDBC好处
- 可以随时替换底层数据库,访问数据库的Java代码基本不变


## JDBC基础代码
要先把jar包复制到lib文件里面
```java
//导包
import java.sql.Connection;
import java.sql.DriverManager;

//1、注册驱动
Class.forName("com.mysql.jdbc.Driver")；

//2、获取mysql链接
String url = "jdbc:mysql://localhost:3306/数据库名";
String username = "root";
String password = "1234"
Connection conn = DriverManager.getConnection(url,username,password);

//3、定义sql语句
String sql = "select * from 表名"

//4、获取执行sql的对象 Statement
Statement stmt = conn.createStatement();

//5、执行sql,返回的值是值该sql语句对数据造成影响的行数
int count = stmt.executeQuery(sql);

//6、释放资源，从内往外释放
stmt.close();
conn.close();
```


# DriverManager类（管理驱动类）
用于加载JDBC驱动并且创建与数据库的连接
## 常用方法
1. 注册驱动

```java
Class.forName("com.mysql.jdbc.Driver");
```
新版本一般省略该注册驱动的步骤
会自动加载jar包中的META-INF/services/java.sql.Driver文件中的驱动类

2. 获取数据库连接

```java
DriverManager.getConnection(String url,String user,String password);
```
参数解析：
url：连接路径
- 语法：`jdbc:mysql://ip地址:端号/数据库名称?参数键值对1&参数键值对2`
- tips：当连接的数据库为本机数据库且为默认端口3306，则可忽略中间ip地址:端口号部分，简写为`jdbc:mysql:///数据库名称`
- 配置useSSL = false 参数来禁用安全连接方式，解决警告提示


# Connection接口（数据库连接对象）
1. 获取执行sql语句的对象

```java
//普通执行对象
Statement createStatement();

//预编译SQL的执行SQL对象：防止SQL注入
PreparedStatement prepareStatement(sql);

//执行存储过程的对象
CallableStatement prepareCall(sql);

```

2. 管理事务

```java
//开启事务
setAutoCommit(boolean autoCommit)
//true为自动提交事务；false为手动提交事务，即开启事务

//提交事务
commit()

//事务回滚
rollback()
```

# Statement(执行SQL语句)
```java
//执行DDL、DML语句，返回DML语句影响的行数，DDL语句执行成功返回值也可能为0
int executeUpdate(sql);

//执行DQL语句,返回值：ResultSet结果集对象
ResultSet executeQuery(sql);

```

# ResultSet（结果集对象）
封装了DQL语句的结果
