## 链表
  链表作为计算机程序最常用的数据结构之一，redis当然也提供了实现。redis 在 adlist文件中实现了双端链表。
## redis中链表及链表节点的实现
  掌握链表的核心思想，不同语言的链表实现基本上都一样。在redis中，首先用 一个结构体 listNode 定义了链表的每个节点
  ```c
  typedef struct listNode {
    struct listNode *prev;//当前节点的上一个节点
    struct listNode *next;//当前节点的下一个节点
    void *value;//当前节点的值
} listNode;
  ```
  而链表本身的实现结构如下
  ```
  typedef struct list {
    listNode *head;//链表头节点
    listNode *tail;//链表尾巴节点
    void *(*dup)(void *ptr);//节点值复制函数
    void (*free)(void *ptr);//节点值释放函数
    int (*match)(void *ptr, void *key);//节点值比对函数
    unsigned long len; //该节点的长度
} list;
  ```
  
  可以看到 list中持有了 头节点及尾节点的指针，这是一个双端无环的链表实现。
  
 ## 链表的迭代
 
redis 为sdlist定义了一个迭代器结构及具体迭代过程的实现，可以从链表的头部向后迭代，后者从尾部向前迭代
```c
typedef struct listIter {
    listNode *next;//当前迭代到的节点
    int direction;//迭代方向 
} listIter;
```
### 获取链表的迭代器
```
/* Returns a list iterator 'iter'. After the initialization every
 * call to listNext() will return the next element of the list.
 *
 * This function can't fail. */
listIter *listGetIterator(list *list, int direction)
{
    listIter *iter;

    if ((iter = zmalloc(sizeof(*iter))) == NULL) return NULL;
    if (direction == AL_START_HEAD)
        iter->next = list->head;
    else
        iter->next = list->tail;
    iter->direction = direction;
    return iter;
}
```
### 驱动迭代器

```
listNode *listNext(listIter *iter)
{
    listNode *current = iter->next;

    if (current != NULL) {
        if (iter->direction == AL_START_HEAD)
            iter->next = current->next;
        else
            iter->next = current->prev;
    }
    return current;
}
```

## 链表的其他操作
  链表的操作 还包括 创建、增加、删除、等，基本上 redis中链表的代码实现是非常简洁易懂的。\
## 节点类型多态
   不知道大家有没有注意到 **listNode** 中**value** 的类型是万能指针**void***，所以该链表是多态的，针对不同类型值的节点的操作，可以通过 **list** 结构的 dup、free、match 三个属性点为 节点值设置类型特定的操作函数。
```c
  typedef struct listNode {
    struct listNode *prev;
    struct listNode *next;
    void *value;
} listNode;
```
## 总结Redis 实现的链表特性

- 持有头尾节点指针，所以获取节点头、节点尾的复杂度尾 O(1)
- 无环链表，无论哪种迭代方向，对链表的迭代访问都可以以Null值判断结束
- 链表特性 获取前后节点的复杂度为 O(1)
- 同 sds 一样，通过自身操作 len属性，获取链表长度的复杂度为O(1)
- 链表类型的多态性