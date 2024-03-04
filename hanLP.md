### hanLP模型
HanLP的工作流程是先加载模型，模型的标示符存储在hanlp.pretrained这个包中，按照NLP任务归类。
```python
# 输出模型包及名字
print(hanlp.pretrained)
# 加载pos类任务的某个模型包
pos = hanlp.load(hanlp.pretrained.pos.PKU_POS_ELECTRA_SMALL)
```
- hanlp.pretrained: 包名
- pos: 任务名
- PKU_POS_ELECTRA_SMALL: 具体某种标注标注的某个模型名


### hanLP自定义词典
```python
# 定义单个词性
pos.dict_tags = {'HanLP': 'state-of-the-art-tool'}
# 按照上下文定义词性
pos.dict_tags = {('的', '希望'): ('补语成分', '名词'), '希望': '动词'}
```
