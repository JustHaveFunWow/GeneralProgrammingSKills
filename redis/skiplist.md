# 跳跃表
SkipList是在有序链表的基础上进行了扩展，解决了有序链表结构查找特定值困难的问题，查找特定值的时间复杂度为O(logn)，他是一种可以代替平衡树的数据结构。具体的介绍可以参考这篇[博文](http://imtinx.iteye.com/blog/1291165)
    
## 跳跃表的数据结构 
  Redis的跳跃表由节点 **server.h/zskiplistNode** 和表 **server.h/zskiplist** 实现.
  ```c
  /* ZSETs use a specialized version of Skiplists */
typedef struct zskiplistNode {
    sds ele;//成员对象
    double score;//计算出的分值，用于排序
    struct zskiplistNode *backward;//后置节点指针
    //节点所处层级
    struct zskiplistLevel {
        struct zskiplistNode *forward;//前向指针
        unsigned int span;//该层跨度
    } level[];
} zskiplistNode;

typedef struct zskiplist {
    //跳跃表表头节点和表尾节点
    struct zskiplistNode *header, *tail;
    //表中节点数量
    unsigned long length;
    //跳跃表的层数
    int level;
} zskiplist;
  ```
  其中 **level**是在创建跳跃表的时候根据幂次定律随机生成一个介于1-32之间的值作为level的大小。
  
## 部分操作函数
### 插入节点
  在向跳跃表插入节点时，这个节点肯定会改变跳跃表的长度，也有可能会改变跳跃表的高度。插入节点时，先根据计算的分值，从最高层的节点向前查找，如果当前节点小于插入节点的score则继续向前，否则跳入低一层继续向前查找，如此循环。
  
```
zskiplistNode *zslInsert(zskiplist *zsl, double score, robj *obj) {
    // updata[]记录该节点在每一层的的前一个节点
    zskiplistNode *update[ZSKIPLIST_MAXLEVEL], *x;
    // rank[]记录该节点在每一层的前一个节点的排名
    unsigned int rank[ZSKIPLIST_MAXLEVEL];
    
    int i, level;

    serverAssert(!isnan(score));
    x = zsl->header; 

    for (i = zsl->level-1; i >= 0; i--) {
        // 存储rank值是为了交叉快速地到达插入位置
        rank[i] = i == (zsl->level-1) ? 0 : rank[i+1];
        // 前向指针不为空，前置指针的分值小于score或当前向指针的分值等且对象大小大于当前对象
        while (x->level[i].forward &&
            (x->level[i].forward->score < score ||
                (x->level[i].forward->score == score &&
                compareStringObjects(x->level[i].forward->obj,obj) < 0))) {
            rank[i] += x->level[i].span;
            x = x->level[i].forward;
        }
        // 存储当前层上位于插入节点的前一个节点
        update[i] = x;
    }
    // 此处假设插入节点的成员对象不存在于当前跳跃表内，即不存在重复的节点
    // 随机生成一个level值
    level = zslRandomLevel();
    if (level > zsl->level) {
        // 如果level大于当前存储的最大level值
        // 设定rank数组中大于原level层以上的值为0
        // 同时设定update数组大于原level层以上的数据
        for (i = zsl->level; i < level; i++) {
            rank[i] = 0;
            update[i] = zsl->header;
            update[i]->level[i].span = zsl->length;
        }
        // 更新level值
        zsl->level = level;
    }
    // 创建插入节点
    x = zslCreateNode(level,score,obj);
    for (i = 0; i < level; i++) {
        // 针对跳跃表的每一层，改变其forward指针的指向
        x->level[i].forward = update[i]->level[i].forward;
        update[i]->level[i].forward = x;

        // 更新插入节点的span值
        x->level[i].span = update[i]->level[i].span - (rank[0] - rank[i]);
        // 更新插入点的前一个节点的span值
        update[i]->level[i].span = (rank[0] - rank[i]) + 1;
    }

    // 更新高层的span值
    for (i = level; i < zsl->level; i++) {
        update[i]->level[i].span++;
    }
    // 设定插入节点的backward指针
    x->backward = (update[0] == zsl->header) ? NULL : update[0];
    if (x->level[0].forward)
        x->level[0].forward->backward = x;
    else
        zsl->tail = x;
    // 跳跃表长度+1
    zsl->length++;
    return x;
}

```

#### 网上的一张关于跳跃表的清晰的结构图
![image](https://pic2.zhimg.com/v2-137c97f94e273a7f497df9c45fc4aa21_b.png)
## 跳跃表的特性
- 跳跃表由很多层组成，占的内存多，但是检索性能堪比二叉树，是O(logN)的复杂度。
- 每一层都是由一个有序的链表组成，只不过跨度不一样
- 在最低层（1）的链表包含了跟表的所有元素
- 如果一个元素在Level i 层出现，则它在Level i之下也会出现
- 每个节点都包含两个指针，一个指针指向该层链表的下一个元素，另一个指针指向下一层的该元素。


> 参考[Redis实现分析]([https://zhuanlan.zhihu.com/p/26499803)
[Redis设计与实现]()