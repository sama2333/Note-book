# Pandas库

### 下载库
` pip install pandas`

### 导入库
`import pandas as pd`

### 生成对象
- 用值生成Series时，Pandas默认自动生成整数索引
- DataFrame的列有不同的数据类型

### 查看数据
- `head()`查看头部数据，默认为5条
- `tail()`查看尾部数据，默认为5条
- `index`查看索引
- `columns`查看列名
- `describe()`快速查看数据的统计摘要
- `T`数据转置
- `sort_index()`按轴排序
- `sort_values()`按值排序

### 修改数据
- `rename()`修改列名

### 选择
- `df['A']`相当于`df.A` 用于选择单列
- `df[0:3]`用[]切片行
- `loc()`按标签选择，也可以按位置选择
- `iloc()`只能按位置进行选择

### 布尔索引
- `df[df.A > 0]`单列的值选择数据
- `df[df > 0]`选择df内满足条件的值
- `isin()`筛选

### 赋值
`df.at[dates[0], 'A'] = 0` 按标签赋值
`df.iat[0, 1] = 0` 按位置赋值
`df.loc[:, 'D'] = np.array([5] * len(df))`按np数组赋值
`df[df > 0] = -df`用`where`条件赋值

### 缺失值
- Pandas主要用`np.nan`表示缺失数据
- 计算的过程默认不包含空值
- `reindex()`重建索引可以更改、添加、删除指定轴的索引，返回值为数据副本
- `dropna(how='any')`删除含有缺失值的行
- `isna()`提取`nan`值的布尔值
- `fillna()`填充缺失值

### 运算
- 不同维度对象运算时，要先对齐
- Pandas自动沿指定维度广播
- `apply()`函数处理数组中的全部数据
- `str`字符串处理功能

### 合并
- `concat()`连接Pandas对象
- `merge()`SQL风格的连接
- `append()`添加行

### 分组
- `groupby()`按照条件分组，可进行单列或多列分组

### 重塑
- `stack()`按列压缩
- `unstack()`按列拆解

### 数据输入/输出
- `to_csv()`写入csv文件
- `read_csv()`读取csv文件数据
- `to_excel()`写入Excel文件