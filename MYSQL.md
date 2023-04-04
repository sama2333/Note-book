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

### DDL
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