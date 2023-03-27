# 基础概念

## 全文搜索
全文搜索是指计算机搜索程序通过扫描文章中的每一个词，对每一个词建立一个索引，指明该词在文章中出现的次数和位置，当用户查询时，搜索程序就根据事先建立的索引进行查找，并将查找的结果反馈给用户

## 倒排索引
倒排索引源于实际应用中需耍根据属性的值来查找记录。这种索引表中的每一项都包括一个属性值和具有该属性值的各记录的地址。由于不是由记录来确定属性值，而是由属性值来确定记录的位置。因而称为倒排索引(inverted index ) 带有倒排索引的文件我们称为倒排索引文件，简称倒排文件( inverted file )

## 压缩算法
对词典文件中的关键词进行了压缩，关键词压缩为＜前缀长度，后缀＞ ， 例如：
当前词为“阿拉伯语”，上一个词为“阿拉伯＂，那么“阿拉伯语“压缩为＜3 , 语＞其次大量用到的是对数字的压缩，数字只保存与上一个值的差值（ 这样可以减少数字的长度， 进而减少保存该数字需要的字节数）。例如当前文章号是16389 （不压缩要用3 个字节保存） 上一文章号是16382, 压缩后保存7 （只用一个字节）

## 索引词（term）
在Elasticsearch 中索引词( term ) 是一个能够被索引的精确值。foo 、Foo 、FOO 几个单词是不同的索引词。索引词( term ) 是可以通过term 查询进行准确的搜索。

## 文本（text）
文本是一段普通的非结构化文字。通常，文本会被分析成一个个的索引词，存储在Elasticsearch 的索引库中。为了让文本能够进行搜索，文本字段需要事先进行分析；当对文本中的关键词进行查询的时候，搜索引擎应该根据搜索条件搜索出原文本。

## 分析(analysis)
分析是将文本转换为索引词的过程，分析的结果依赖于分词器。

## 集群(cluster)
集群由一个或多个节点组成，对外提供服务，对外提供索引和搜索功能。在所有节点，一个集群有一个唯一的名称默认为“Elasticsearch "。当需要有多个集群的时候，要确保每个集群的名称不能重复。当某节点被设置为相同的集群名称时，就会自动加入该集群。一个节点只能加入一个集群。

## 节点(node)
一个节点是一个逻辑上独立的服务，它是集群的一部分，可以存储数据，并参与集群的索引和搜索功能。节点也有唯一的名字， 在启动的时候分配。默认情况下， 每个节点会加入名为Elasticsearch的集群中。

## 路由(routing)
当存储一个文档的时候，它会存储在唯一的主分片中，具体哪个分片是通过散列值进行选择已默认情况下，这个值是由文档的ID生成。如果文档有一个指定的父文档，则从父文档ID中生成，该值可以在存储文档的时候进行修改。

## 分片(shard)
索引是指向主分片和副本分片的逻辑空间。Elasticsearch 将索引分解成多个分片当你创建一个索引，你可以简单地定义你想要的分片数扯。每个分片本身是一个全功能的、独立的单元，可以托管在集群中的任何节点。每个分片本身是一个全功能的、独立的单元，可以托管在集群中的任何节点。

## 主分片(primary shard)
每个文档都存储在一个分片中，当你存储一个文档的时候，系统会首先存储在主分片中， 然后会复制到不同的副本中。默认情况下，一个索引有5个主分片。你可以事先制定分片的数量，当分片一旦建立，则分片的数量不能修改。

## 副本分片(replica shard)
每一个分片有零个或多个副本，副本主要是主分片的复制。
建立副本分片目的：1增加高可用性： 当主分片失败的时候，可以从副本分片中选择一个作为主分片；2提高性能： 当查询的时候可以到主分片或者副本分片中进行查询。默认情况下，一个主分片配有一个副本，但副本的数量可以在后面动态地配置增加。副本分片必须部署在不同的节点上，不能部署在和主分片相同的节点上。 分片的原因：1允许水平分割扩展数据；2允许分配和并行操作（可能在多个节点上）从而提高性能和吞吐量。

## 复制(replica)
Elasticsearch允许你创建一个或多个拷贝，你的索引分片就形成了所谓的副本或副本分片。复制提供了高可用性，当节点失败的时候不受影响需要注意的是，一个复制的分片又会存储在同一个节点中。搜索可以在所有副本上并行执行。每个索引可以拆分成多个分片。索引可以复制零个或者多个分片。

## 索引(index)
索引是具有相同结构的文档集合。在系统上索引的名字全部小写，通过这个名字可以用来执行索引、搜索、更新和删除操作等。

## 类型(type)
在索引中，可以定义一个或多个类烈，类型是索引的逻辑分区。在一般情况下， 一种类型被定义为具有一组公共字段的文档。

## 文档(document)
文档是存储在Elastic sea rch 中的一个JSON 格式的字符串。原始的JSON 文档被存储在一个叫作＿source的字段中己当搜索文档的时候默认返回的就是这个字段。

## 映射(mapping)
映射像关系数据库中的表结构，每一个索引都有一个映射，它定义了索引中的每一个字段类型，以及一个索引范围内的设置。一个映射可以事先被定义， 或者在第一次存储文档的时候自动识别。

## 字段(field)
文档中包含零个或者多个字段，字段可以是一个简单的值（例如字符串、整数、日期），也可以是一个数组或对象的嵌套结构。字段类似于关系数据库中表的列己每个字段都对应一个字段类型，例如整数、字符串、对象等二字段还可以指定如何分析该字段的值。

## 来源字段(source field)
默认情况下，你的原文档将被存储在＿source 这个字段中，当你查询的时候也是返回这个字段。这允许你可以从搜索结果中访问原始的对象， 这个对象返回一个精确的JSON字符串，这个对象不显示索引分析后的其他任何数据。

## 主键(ID)
ID是一个文件的唯一标识，如果在存库的时候没有提供ID,系统会自动生成一个ID,文档的index/type/id必须是唯一的。

## JSON (JavaScript Object Notation)
一种轻量级的数据交换格式,易于人们阅读和编写。JSON有两种结构：" 名称／值"对的集合(A collection of name/value pairs)。不同的语言中的理解不同，如对象(object)、纪录(record)、结构(struct)、字典(dictionary) 、哈希表(hash table) 、有键列表(keyed list) 或者关联数组(associative array)。
二是：值的有序列表( An ordered list of values ) 。在大部分语言中，是指数组( array ) 。

## Mapping属性
mapping是对索引库中文档的约束，常见的mapping属性包括：
type：字段数据类型，常见的简单类型有：
字符串：text（可分词的文本）、keyword（精确值，例如：品牌、国家、ip地址）
数值：long、integer、short、byte、double、float、
布尔：boolean
日期：date
对象：object
index：是否创建索引，默认为true
analyzer：使用哪种分词器
properties：该字段的子字段

# 索引管理（index）

## 创建索引
请求方式：PUT
请求路径：/索引库名，可以自定义
请求参数：mapping映射
代码：
```
PUT /索引库名称
{
  "mappings": {
    "properties": {
      "字段名":{
        "type": "text",
        "analyzer": "ik_smart"
      },
      "字段名2":{
        "type": "keyword",
        "index": "false"
      },
      "字段名3":{
        "properties": {
          "子字段": {
            "type": "keyword"
          }
        }
      },
      // ...略
    }
  }
}
```

## 查询索引
请求方式：GET
请求路径：/索引库名
请求参数：无
代码：
```
GET /索引库名
```

## 修改索引(添加字段)
倒排索引结构虽然不复杂，但是一旦数据结构改变（比如改变了分词器），就需要重新创建倒排索引，这简直是灾难。因此索引库一旦创建，无法修改mapping。虽然无法修改mapping中已有的字段，但是却允许添加新的字段到mapping中，因为不会对倒排索引产生影响。
代码：
```
PUT /索引库名/_mapping
{
  "properties": {
    "新字段名":{
      "type": "integer"
    }
  }
}
```

## 删除索引
请求方式：DELETE
请求路径：/索引库名
请求参数：无
代码：
```
DELETE /索引库名
```

# 文档管理（mapping）

## 创建文档
如果不写文档id，kibana会自动生成一个。
代码：
```
POST /索引库名/_doc/文档id
{
    "字段1": "值1",
    "字段2": "值2",
    "字段3": {
        "子属性1": "值3",
        "子属性2": "值4"
    },
    // ...
}
```

## 查询文档
代码：
```
GET /{索引库名称}/_doc/{id}
```

## 删除文档
代码：
```
DELETE /{索引库名}/_doc/id值
```

## 修改文档
### 全量修改
直接覆盖原来的文档，本质是根据指定的id删除文档，新增一个相同id的文档。
注意：如果根据id删除时，id不存在，第二步的新增也会执行，也就从修改变成了新增操作了。
代码：
```
PUT /{索引库名}/_doc/文档id
{
    "字段1": "值1",
    "字段2": "值2",
    // ... 略
}
```
### 增量修改
增量修改是只修改指定id匹配的文档中的部分字段。
代码：
```
POST /{索引库名}/_update/文档id
{
    "doc": {
         "字段名": "新的值",
    }
}
```

### 批量文档操作（添加、更新、删除）
每个操作（特指添加）中不可换行，代码必须在同一行
代码：
```
POST /{索引库名}/_bulk
#添加（指定id）
{"index":{空/"_id":自定id}}
  {"字段名":"值","字段名":"值"}

#更新
{"update":{"_id":文档id}}
  {"doc":{"指定字段":"新的值"}}

#删除
{"delete":{"_id":文档id}}
```

## queryDSL语法 查询所有match_all
GET /products/_search
{
  "query": {
    "match_all": {}
  }
}
#term 基于关键词查询
#keyword类型要全文搜索
#text类型中文默认按单字，英文按单词分词
GET /products/_search
{
  "query": {
    "term": {
      "title": {
        "value": "咸鱼豆腐"
      }
    }
  }
}

#范围查询 ranger
GET /products/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 0,
        "lte": 5
      }
    }
  }
}

#前缀查询 prefix
GET /products/_search
{
  "query": {
    "prefix": {
      "title": {
        "value": "咸鱼"
      }
    }
  }
}

#通配符查询 wildcard(?匹配一个字符，*匹配多个字符）)
GET /products/_search
{
  "query": {
    "wildcard": {
      "description": {
        "value": "鱼*"
      }
    }
  }
}

#多id查询 ids
GET /products/_search
{
  "query": {
    "ids": {
      "values": [1,2,4]
      
      }
    }
  }
  
#模糊查询 fuzzy（长度<=2不允许模糊；长度3-5允许一次模糊；长度>5允许最多两次模糊）
GET /products/_search
  {
    "query": {
      "fuzzy": {
        "title": "豆腐"
      }
    }
  }
  
#布尔查询 boll(must=&&;should=||;must_not=!)
GET /products/_search
{
  "query": {
    "bool": {
      "must": [
        {"ids": {
          "values": [1]
        }},{
          "term": {
            "title": {
              "value": "小浣熊"
            }
          }
        }
      ]
    }
  }
}

#多字段查询  multi_match（会将query字段分词再搜索）
GET /products/_search
{
  "query": {
    "multi_match": {
      "query": "浣猫",
      "fields": ["title","description"]
    }
  }
}

#默认字段分词查询 query_string
GET /products/_search
{
  "query": {
    "query_string": {
      "default_field": "description",
      "query": "熊"
    }
  }
}

#高亮查询 highli
GET /products/_search
{
  "query": {
    "term": {
      "description": {
        "value": "熊"
      }
    }
  }, 
  "highlight": {
    "pre_tags": ["<span style='color:red;'"], 
    "post_tags": ["</span>"], 
    "fields": {
      "*":{}
    }
  }
}

#返回指定条数 size
GET /products/_search
{
  "query": {
    "term": {
      "description": {
        "value": "小"
      }
    }
  },
  "size": 1
}

#分页查询 form(配合size使用，(当前页-1)*size)
GET /products/_search
{
  "query": {
    "term": {
      "description": {
        "value": "小"
      }
    }
  },
  "from": 1, 
  "size": 1
}

#指定字段排序 sort(默认降序desc,升序为asc)
GET /products/_search
{
  "query": {
    "term": {
      "description": {
        "value": "小"
      }
    }
  },
  "sort": [
    {
      "price": {
        "order": "desc"
      }
    }
  ]
}

#返回指定字段 _source
GET /products/_search
{
  "query": {
    "term": {
      "description": {
        "value": "小"
      }
    }
  }, 
  "_source": ["title","price"]
}