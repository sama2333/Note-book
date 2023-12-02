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
- RDD之间存在依赖关系
- KV型RDD可以存在分区器
- RDD分区数据的读取会尽量靠近数据所在地

##### 程序执行入口SparkContext对象
Spark RDD编程的程序入口对象是Spark Context对象（不论何种编程语言）
只有构建出Spark Context对象后才能继续后续的API调用和计算
```python
# 构建SparkContext对象
conf = SparkConf().setAppName("HelloWord").setMaster("local[*]")
# 构建SparkContext执行环境入口对象
sc = SparkContext(conf = conf)
```

##### RDD的创建
- 通过并行化集合创建（本地对象 转 分布式RDD）
- 读取外部数据源（读取文件）
```python
rdd = sparkcontext.parallelize(参数1, 参数2)
# 参数1 集合对象
# 参数2 分区数
```
```python
rdd = sparkcontext.textFile(参数1, 参数2)
# 参数1，必填，本地路径，支持本地路径，支持HDFS
# 参数2，可选，表示最小分区数量，仅供参考
```
```python
# 读取批量小文件
rdd = sparkcontext.wholeTextFiles(参数1, 参数2)
# 参数1，必填，本地路径，支持本地路径，支持HDFS
# 参数2，可选，表示最小分区数量，仅供参考
```

##### RDD算子
算子：分布式集合对象的API称之为算子
分类：
- Transformation:转换算子（返回值仍是RDD的算子）
- Action:动作（行动）算子（返回值不是RDD的算子）

##### 常用Transformation算子
- map算子，将RDD中数据一条条处理
```python
rdd.map(func)
# func: f(T) -> U
```
- flatMap算子，执行map操作并解除RDD中的嵌套
```python
rdd.flatMap(func)
# func: f(T) -> U
```
- reduceByKey算子，针对KV型RDD，自动按照key分组，再对组内数据按照指定逻辑进行聚合操作
```python
rdd.reduceByKey(func)
# func: (V, V) -> V
# 接受两个类型一致的参数
```
- mapValue算子，对其内部的二元元组的Value执行map操作
```python
rdd.mapValue(func)
# func: (V) -> U
```
- groupBy算子，将rdd的数据进行分组
```python
rdd.groupBy(func)
# func: (T) ->K
```
- Filter算子，过滤想要的数据进行保留
```python
rdd.filter(func)
# func:(T)-> bool
# 返回值为True的保留，False的被丢弃
```
- distinct算子，对RDD数据进行去重
```python
rdd.distinct(参数1)
# 参数1，去重的分区数量，一般不传入该参数
```
- union算子，将2个rdd合并为1个并返回
```python
rdd.union(other_rdd)
# 只合并，不去重
```
- join算子，对两个rdd执行JOIN操作（可实现SQL中的内外连接）
```python
rdd.join(other_rdd)# 内连接
rdd.leftOuterJoin(other_rdd)# 左外连接
rdd.rightOuterJoin(other_rdd)# 右外连接
```
- intersection算子，求2个rdd的交集，返回新的rdd
```python
rdd.intersection(other_rdd)
```
- glom算子，为rdd的数据加上嵌套，按照分区进行
```python
rdd.glom()
```
- groupByKey算子，针对KV型数据，自动按照key分组
```python
rdd.groupByKey()
```
-sortBy算子，基于你指定的排序依据进行排序
```python
rdd.sortBy(func, ascending = False, numPartitions = 1)
# func:(T)-> U
# ascending True 升序 False 降序
# numPartitions用多少分区进行排序
```
- sortByKey，针对KV型数据，按照key排序
```python
rdd.sortByKey(ascending = True, numPartitions = None, keyfunc=<functiom RDD.<lambda>>)
# ascending 升序or降序
# numPartitions 按照几个分区进行排序，如果全局有序，设置1
# keyfunc 在排序前对key进行处理，语法是(K) -> U 一个参数传入，返回一个值
```

##### 常用Action算子
- contByKey算子，统计key出现的次数
```python
rdd.countByKey()
```
- collect算子，将RDD各个分区的数据统一收集到Driver中，形成一个List对象
```python
rdd.collect()
```
- reduce算子，对RDD数据集按照你传入的逻辑进行聚合
```python
rdd.reduce(func)
# func:(T, T) -> T
# 2参数传入，1返回值，返回值与参数要求类型一致
```
- fold算子，接受传入逻辑进行聚合，聚合是带有初始值的
```python
rdd.fold(a, func)
# a，初始值，会在每次分区内聚合中使用，同时分区间聚合时也会使用
```
- first算子，取出RDD的第一个元素
```python
rdd.first()
```
- top算子，对RDD数据集进行降序排序后，去前N个元素
```python
rdd.top(a)
# a，取降序后的前a个元素
```
- takeSample算子，随机抽样RDD的数据
```python
rdd.takeSample(参数1, 参数2, 参数3)
# 参数1，True表示允许取同一个数据，False表示不允许取同一个数据，与数据内容无关，仅针对数据位置是否相同
# 参数2，抽样数量
# 参数3，随机数种子
```
- takeOrdered算子，对RDD排序后取前N个元素
```python
rdd.takeOrdered(参数1, 参数2)
# 参数1，抽取数量
# 参数2，对排序的数据进行更改
```
- foreach算子，对RDD中每一个元素执行你提供的逻辑操作（与map算子相似，但没有返回值）
```python
rdd.foreach(func)
# func:(T) -> None
```
- saveAsTextFile算子，将RDD的数据写入文本文件中
```python
rdd.saveAsTextFile(参数1)
# 参数1，文本地址（支持写出到本地或者HDFS系统）
```
- mapPartitions算子，分区操作算子
```python
rdd.mapPartitions(func)
# 每个分区执行一次，相对于map算子减少了io次数
```
- foreachPartition算子
```python
rdd.foreachPartition(func)
# 与foreach算子相似，一次处理整个分区的数据
```
- partitionBy算子，对RDD进行自定义分区操作
```python
rdd.partitionBy(参数1, 参数2)
# 参数1 重新分区后的分区数量
# 参数2 自定义分区规则，函数传入
# func:(K) -> int
```
- repartition算子，对分区重新执行分区（仅数量）
```python
rdd.repartition(N)
# N，新的分区数
```
