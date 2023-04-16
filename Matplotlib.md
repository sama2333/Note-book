# Matplotlib库

### 下载与导入
- `pip install matplotlib`
- `import matplotlib.pyplot as plt` 导入plt库

### 基础语法
```python
plt.figure() # 创建画布
plt.title() # 添加标题，可以指定标题的名称、位置、颜色、字体大小
plt.xlabel() # 添加X轴名称
plt.ylabel() # 添加Y轴名称
plt.xlim() # 指定X轴的范围，只能确定一个数值区间，而无法使用字符串标识
plt.ylim() # 指定Y轴的范围
plt.xticks() # 指定X轴刻度的数目与取值
plt.yticks() # 指定Y轴刻度的数目与取值
plt.legend() # 指定当前图形的图例，可以指定图例的大小、位置、标签
plt.show() # 显示图形
```

### 绘制图案
- 绘制前准备
```python
plt.rcParams['font.sans-serif'] = 'SimHei'  # 设置中文显示
plt.rcParams['axes.unicode_minus'] = False # 解决坐标轴负号乱码
```
- `plt.scatter()`散点图
- `plt.plot()`折线图
- `plt.pie()`饼图
- `plt.boxplot()`箱线图