# spark

## Spark基础
spark是用于大规模数据处理的统一分析引擎
可以计算结构化、半结构化、非结构化数据
可以使用Java、python等语言

-| Hadoop | Spark
---|---|---
类型|基础平台，包含计算存储调度| 纯计算按工具（分布式）
场景|海量数据批处理（磁盘迭代计算）|海量数据的批处理（内存迭代计算、交互式计算）、海量数据流计算
价格|对机器要求低、便宜|对内存有要求，相对贵
编程范式|Map+Reduce，API较为底层，算法适应性差|RDD组成DAG有向无环图，API较为顶层，方便使用
数据存储结构|MapReduce中间计算结果在HDFS磁盘上，延迟大|RDD中间运算结果在内存中，延迟小
运行方式|Task以进程方式维护，任务启动慢|Task以线程方式维护，任务启动快，可批量创建提高并行能力

### Spark模块
- 核心SparkCore
- SQL计算（SparkSQL）
- 流计算（SparkStreaming）
- 图计算（GraphX）
- 机器学习（MLlib）

### Spark的运行模式
- 本地模式（单击）Local用于开发和测试
- Standalone模式（集群）
- Hadoop YARN模式（集群）
- Kubernetes模式（容器集群）
- 云服务模式

## Standalone模式

### Spark的架构角色
- 资源层面
  - Master：集群资源管理
  - Worker：单机资源管理
- 任务运行层面：
  - Driver：单个任务的管理
  - Executor：单个任务的计算

### Spark程序运行层次结构
- 4040端口，运行的Application临时绑定的端口，用于查看当前任务的状态，当被占用时顺延至4041、4042等，当程序运行结束后，就会被注销
- 8080端口，默认Stand Alone下，Master角色的WEB端口，用于查看当前MASTER（集群）的状态
- 18080端口，默认是历史服务器的端口，当程序运行结束后，4040端口会被注销，若想查看程序的运行状态可通过历史服务器查看，历史服务器长期稳定运行

### 应对Master单点故障（SPOF）问题
- 基于文件系统的单点回复（Single-Node Recovery with Local FFile System） -- 只能用于开发环境
- 基于zookeeper的Standby Masters（Standby Masters with ZooKeeper） -- 可以用于生产环境

Zookeeper提供了Leader Election机制，可以使集群中存在多个Master，但只有一个是Active，其他都是Standby。当出现故障时，可以切换到新的Master中，切换的过程中只会影响新的Job的提交，运行中的Job不会受到影响。

## Spark On YARN
只需要找一台服务器充当Spark的客户端，即可提交任务到YARN集群中运行

### 本质
- Master角色由YARN的ResourceManager担任
- Worker角色由YARN的NodeManager单人
- Driver角色运行在YARN容器内或提交任务的客户端进程中
- 真正干活的Executor运行在YARN提供的容器内 

### 运行前提
- 安装了Yarn集群
- 拥有Spark客户端工具
- 被提交的代码程序

### 两种运行模式
- Cluster模式（集群模式），Driver运行在YARN容器内部，和ApplicationMaster在同一个容器内
- Client模式（客户端模式），Driver运行在客户端进程中，例如运行在spark-submit程序的进程中

-|Cluster模式|Client模式
---|---|---
Driver运行位置|YARN容器内|客户端进程内
通讯效率|高|低于Cluster模式
日志查看|日志输出在容器内容，查看不方便|日志输出在客户端的标准输出流中，方便查看
生产可用|推荐|不推荐
稳定性|稳定|基于客户端进程，受到客户端进程影响

## PySpark
PySpark指Python的运行类库，内置了完全的Spark API，可以通过该类库来编写Spark应用程序，并提交到Spark集群中也允许

### PySpark的架构体系
- Driver端由JVM执行
- Executor端由JVM做命令转发
- 底层由Python解释器进行工作

## SparkCore

### RDD（弹性分布式数据集）
是Spark最基本的数据抽象，代表一个不可变、可分区、里面元素可并行计算的集合
- Dataset：一个数据集合，用于存放数据
- Distributed：RDD中的数据是分布式存储，可用于分布式计算
- Resilient：RDD中的数据可以存储在内存中或者磁盘中

##### RDD的五大特性
- RDD是有分区的
- 计算方法会作用到每一个分片上
- RDD