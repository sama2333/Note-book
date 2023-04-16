# 数据库

### 数据库概念
- 存储和管理数据的仓库，数据有组织的进行存储
- 数据库英文名为`DataBase`，简称`DB`

### 数据库管理系统概念
- 管理数据库的大型软件
- 英文为`DataBase Management System`，简称为`DBMS`

### 常见的数据库管理系统
- Oracle：收费的大型数据库
- MySQL：开源免费的中小型数据库
- SQL Server：MicroSoft公司收费的中型数据库。C#、.NET语言常用
- PostgreSQL：开源免费中小型数据库
- DB2：IBM公司的大型收费数据库
- SQLite：嵌入式的微型数据库，作为Android的内置数据库
- MariaDB：开源免费中小型数据库

## MySQL
### MySQL安装与卸载
https://downloads.mysql.com/archives/community/
-上面的链接为mysql下载页面
-安装MySQL：
    - `mysqld --initialize-insecure`初始化data目录
    - `mysqld -install`注册MySQL服务
    - `net start mysql`启动MySQL服务
    - `mysqladmin -u root password 1234`修改默认管理员(root)的密码为1234
-卸载MySQL：
    - 打开cmd
    - `net stop mysql`，停止MySQL的运行
    - `mysql -remove mysql`删除MySQL
    - 删除MySQL目录及相关环境变量

### MySQL登录与退出
- 登录
    - 打开cmd
    - `mysql -u用户名 -p密码 -h连接的mysql服务器的ip(默认127.0.0.1) -p端口号(默认3306)`登录
- 退出
    - `exit`
    - `quit`

### MySQL数据模型
- 关系型数据库的简历在关系模型基础上的数据库，简单来说就是由多张能互相连接的二维表组成的数据库
- 关系型数据库的优点：
    - 都是使用表结构，格式一致，便于维护
    - 使用通用的SQL语言操作，使用方便，可用与复杂查询
        - 关系型数据库都可以通过SQL进行操作，所以使用方便
        - 复杂查询。
    - 数据存储在磁盘中，安全
- 数据模型
    - MySQL中可以创建多个数据库，每个数据库对应磁盘中的一个文件夹
    - 每个数据库中可以创建多个表，每张表对应磁盘中的frm文件
    - 每张表可以存储多条数据，数据会存储到磁盘的MYD文件中

## SQL
### SQL简介
- Structured Query Language，简称SQL，结构化查询语言
- 操作关系型数据库的编程语言
- 定义操作所以关系型数据库的统一标准，可以使用SQL操作所有关系型数据库管理系统

### 通用语法
- SQL语句可以单行或多行书写，以分号结尾
- MySQL数据库中的SQL语句不区分大小写，关键字建议大写
- 注释
    - 单行注释：`-- 注释内容` 或 `#注释内容`(MySQL特有)
    > 注意：使用`--`添加单行注释时，`--`后面必须加上空格，而`#`没有要求
    - 多行注释：`/* 注释 */`

### SQL分类
- DDL(Data Definition Language)：数据定义语言，用来定义数据库对象：数据库、表、列等
- DML(Data Manipulation Language)：数据操作语言，用来对数据库中表的数据进行增删改
- DQL(Data Query Language)：数据查询语言，用来查询数据库中表的记录
- DCL(Data Control Language)：数据控制语言，用来定义数据库的访问权限和安全级别，及创建用户

### DDL:数据库操作
- 查询所有的数据库
```sql
SHOW DATABASES;
```
在未创建创建数据库时，查询到的数据库是MySQL安装好后自带的数据库，一般不对其进行操作
- 创建数据库
```sql
CREATE DATABASE 数据库名称;
```
当创建数据库的时候使用的数据库名称已经存在，会出现报错，所以创建数据库之前应该进行判断
```sql
CREATE DATABASE IF NOT EXISTS 数据库名称;
```
- 删除数据库
```sql
DROP DATABASE 数据库名称;
```
判断数据库是否存在，存在则删除
```sql
DROP DATABASE IF EXISTS 数据库名称;
```
- 使用数据库
```sql
USE 数据库名称;
```

### DDL:数据表操作
- 查询表
```sql
SHOW TABLES;
```
- 查询表结构
```sql
DESC 表名称;
```
- 创建表
``` sql
CREATE TABLE 表名 (
    字段名1 数据类型1,
    字段名2 数据类型2,
    ...
    字段名n 数据类型n
);
```
> 注意：最后一行的末尾不能加逗号
- 数据类型:
``` sql
1. 数值类型：
    TINYINT ：占用 1 字节，范围为 -128 到 127（有符号）或 0 到 255（无符号）。
    SMALLINT ：占用 2 字节，范围为 -32768 到 32767（有符号）或 0 到 65535（无符号）。
    MEDIUMINT ：占用 3 字节，范围为 -8388608 到 8388607（有符号）或 0 到 16777215（无符号）。
    INT ：占用 4 字节，范围为 -2147483648 到 2147483647（有符号）或 0 到 4294967295（无符号）。
    BIGINT ：占用 8 字节，范围为 -9223372036854775808 到 9223372036854775807（有符号）或 0 到 18446744073709551615（无符号）。
    FLOAT ：占用 4 字节，单精度浮点数。
    DOUBLE ：占用 8 字节，双精度浮点数。
    DECIMAL ：定点数，可以指定精度和小数位数。

2. 日期和时间类型：
    DATE ：日期类型，格式为 "YYYY-MM-DD"。
    TIME ：时间类型，格式为 "HH:MM:SS"。
    DATETIME ：日期时间类型，格式为 "YYYY-MM-DD HH:MM:SS"。
    TIMESTAMP ：时间戳类型，存储为从 1970 年 1 月 1 日 00:00:00 UTC 到当前时间的秒数。
    YEAR ：年份类型（2 位或 4 位），可用于存储 1901 到 2155 年。

3. 字符串类型：
    CHAR ：固定长度字符串，最多支持 255 个字符。
    VARCHAR ：可变长度字符串，最多支持 65535 个字符。
    TINYTEXT ：可变长度字符串，最多支持 255 个字符。
    TEXT ：可变长度字符串，最多支持 65535 个字符。
    MEDIUMTEXT ：可变长度字符串，最多支持 16777215 个字符。
    LONGTEXT ：可变长度字符串，最多支持 4294967295 个字符。

4. 二进制数据类型：
    BINARY ：固定长度的二进制字符串，最多支持 255 个字节。
    VARBINARY ：可变长度的二进制字符串，最多支持 65535 个字节。
    TINYBLOB ：可变长度的二进制字符串，最多支持 255 个字节。
    BLOB ：可变长度的二进制字符串，最多支持 65535 个字节。
    MEDIUMBLOB ：可变长度的二进制字符串，最多支持 16777215 个字节。
    LONGBLOB ：可变长度的二进制字符串，最多支持 4294967295 个字节。

5. 布尔类型：
    BOOLEAN 或 BOOL ：可以存储 true 或 false。

6. 枚举类型：
    ENUM ：枚举类型，可以指定不超过 65535 个成员的列表，并且每个成员都有一个关联的数值。

7. 集合类型：
    SET ：集合类型，可以指定不超过 64 个成员的列表，可以包含一个或多个成员。
```

- 删除表
```sql
DROP TABLE 表名;
```
删除表前判断表是否存在
```sql
DROP TABLE IF EXISTS 表名;
```

- 修改表
    - 修改表名
    ```sql
    ALTER TABLE 表名 RENAME TO 新的表名;
    ```
    - 添加一列
    ```sql
    ALTER TABLE 表名 ADD 列名 数据类型;
    ```
    - 修改列的数据类型
    ```sql
    ALTER TABLE 表名 MODIFY 列名 新数据类型;
    ```
    - 修改列名和数据类型
    ```sql
    ALTER TABLE 表名 CHANGE 列名 新列名 新数据类型;
    ```
    - 删除列
    ```sql
    ALTER TABLE 表名 DROP 列名;
    ```

### DML
DML主要是对数据进行增(insert)删(delete)改(update)操作
- 添加数据
    - 指定列添加数据
    ```sql
    INSERT INTO 表名 VALUES(值1,值2,...);
    ```
    - 给全部列添加数据
    ```sql
    INSERT INTO 表名(列名1,列名2,...);
    ```
    - 批量添加数据
    ```sql
    INSERT INTO 表名(列名1,列名2,...) VALUES(值1,值2,...),(值1,值2,...),(值1,值2,...)...;
    INSERT INTO 表名 VALUES(值1,值2,...),(值1,值2,...),(值1,值2,...)...;
    ```

- 修改数据
    - 修改表数据
    ```sql
    UPDATE 表名 SET 列名1=值1,列名2=值2,...[WHERE 条件];
    ```
    > 注意：
    >
    > 1.修改语句中如果不加条件，则修改全部数据
    > 2.语句中的中括号部分表示非必要语句，即可以省略

- 删除数据
    - 删除数据
    ```sql
    DELETE FROM 表名 [WHERE 条件];
    ```

### DQL
- 完整的查询语句语法
```sql
// select语句
SELECT [DISTINCT] [TOP n [WITH TIES]] select_list  // 搜寻结果唯一化&查询指定列
[INTO new_table] //查询结果保存到新表
[FROM table_source] // 指定查询表
[WHERE search_conditition] // 指定查询条件
[GROUP BY group_by_expression] // 指定查询结果分组条件
[HAVING search_condition] // 指定组或者聚合函数查询条件
[ORDER BY order_expression [ASC|DESC]] // 指定结果集的排序方式
[COMPUTE expression] // 结果集末尾生成汇总
```
- 基础查询语句
    - 查询多个字段
    ```sql
    SELECT 字段列表 FROM 表名;
    SELECT * FROM 表名; -- 查询所有数据
    ```
    - 去除重复记录
    ```sql
    SELECT DISTINCT 字段列表 FROM 表名;
    ```
    - 起别名
    ```sql
    AS: AS 也可以省略
    ```
- 条件查询
```sql
SELECT 字段列表 FROM 表名 WHERE 条件列表;
```
- 模糊查询练习
> 模糊查询使用like关键字，可以使用通配符进行占位:
>
> （1）_ : 代表单个任意字符
>
> （2）% : 代表任意个数字符
- 排序查询
```sql
SELECT 字段列表 FROM 表名 ORDER BY 排序字段名1 [排序方式1],排序字段名2 [排序方式2] …;
```

上述语句中的排序方式有两种，分别是：

* ASC ： 升序排列 **（默认值）**
* DESC ： 降序排列

> 注意：如果有多个排序条件，当前边的条件值一样时，才会根据第二条件进行排序
- 聚合函数
将一列数据作为一个整体，进行纵向计算。

| 函数名 | 功能 |
| ----------- | -------------------------------- |
| count(列名) | 统计数量（一般选用不为null的列） |
| max(列名) | 最大值 |
| min(列名) | 最小值 |
| sum(列名) | 求和 |
| avg(列名) | 平均值 |
```sql
SELECT 聚合函数名(列名) FROM 表;
```
- 分组查询
```sql
SELECT 字段列表 FROM 表名 [WHERE 分组前条件限定] GROUP BY 分组字段名 [HAVING 分组后条件过滤];
```