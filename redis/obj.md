# Redis的对象系统
&emsp;&emsp;Redis在底层构造了几种基本的数据结构，包括 **动态字符创sds**、**双向链表sdlist**、**字典dict**、**跳跃表skiplist**、**整数集合intset**、**压缩列表ziplist**,作为一个基于内存的数据库系统，redis为内存优化做了很多工作，而在这些数据结构之上，redis定义了相应的对象系统，使用对象的而不是直接使用结构体能够针对不同的使用场景，为对象设置多种不同的数据结构的实现，从而优化对象在不同场景下的使用效率.

&emsp;&emsp;使用对象封装这些数据结构，将一些通用的逻辑封装在对象系统中，比如针对对象系统，redis 实现了基于引用计数技术的内存回收机制，在对象不再被使用时，自动释放内存。
## RedisObject简明分析
### 结构
```c
typedef struct redisObject {
    unsigned type:4; //类型
    unsigned encoding:4;//编码
    unsigned lru:LRU_BITS; // LRU_BITS为24
    int refcount;//引用计数
    void *ptr;//指向实际的底层数据结构的指针
} robj;
```
### 类型type
Redis对象有5种数据类型
```
#define OBJ_STRING 0
#define OBJ_LIST 1
#define OBJ_SET 2
#define OBJ_ZSET 3
#define OBJ_HASH 4
```
类型下面，还有具体的数据结构，比如 OBJ_LIST类型的对象可以由 LINKEDLIST 或者 ZIPLIST等实现，redis根据实际的不同场景做出优化。
### 编码类型 encoding
&emsp;&emsp;encoding参数表示该对象存储的实际实现，

对象类型 | 编码方式
---|---
OBJ_STRING | OBJ_ENCODING_RAW ,OBJ_ENCODING_INT ,OBJ_ENCODING_EMBSTR
OBJ_LIST | OBJ_ENCODING_LINKEDLIST ,OBJ_ENCODING_ZIPLIST ,OBJ_ENCODING_QUICKLIST
OBJ_SET | OBJ_ENCODING_INTSET ,OBJ_ENCODING_HT
OBJ_ZSET | OBJ_ENCODING_ZIPLIST ,OBJ_ENCODING_SKIPLIST
OBJ_HASH | OBJ_ENCODING_ZIPLIST ,OBJ_ENCODING_HT

&emsp;&emsp;以列表对象**OBJ_LIST**举例;  
Redis在开始，首先使用压缩列表做为 **OBJ_LIST**的底层实现，因为压缩列表更省内存，在数据量不大的情况下，增删操作性能影响并不大。随着列表对象包含元素的增多，使用压缩列表的优势逐渐小时，这时对象底层会使用功能更强、更适用保存大量元素的双端列表来实现。


#### 查看一个数据库键的值对象编码
```
redis > set sex "boy"
redis > OBJECT ENCODING boy
//输出 "raw"
```

#### 对象系统动态类型
