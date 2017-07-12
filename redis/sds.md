## SDS
  SDS是 redis 内置的字符串对象，redis没有使用C 自带的字符串结构，而是自己实现 字符串的表示结构。
### SDS 数据结构定义
在 **sds.h**中定义了 sds的结构
- len 表示字符串的长度 （不包含末尾的空终止字符 '\0'）
- alloc 申请的内存能够容纳的最大字符长度，不包含Header结构和最后的空终止字符
- flags header的类型
- []buf 存储的字符
```c
/* Note: sdshdr5 is never used, we just access the flags byte directly.
 * However is here to document the layout of type 5 SDS strings. */
struct __attribute__ ((__packed__)) sdshdr5 {
    unsigned char flags; /* 3 lsb of type, and 5 msb of string length */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr8 {
    uint8_t len; /* used */
    uint8_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr16 {
    uint16_t len; /* used */
    uint16_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr32 {
    uint32_t len; /* used */
    uint32_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr64 {
    uint64_t len; /* used */
    uint64_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
```
  针对不同的大小的字符使用了不同的结构体来存储 ，从而节省内存
  
### SDS 中的一些代码细节
- 使用 len 属性保存sds长度，使得获取 sds 对象长度的计算变为O(1)
- 使用 sdsMakeRoomFor 、sdsRemoveFreeSpace 函数在对 sds 做拼接或者剪切操作时 动态调整占用内存
- 
  
```C
  /* Append the specified binary-safe string pointed by 't' of 'len' bytes to the
 * end of the specified sds string 's'.
 *
 * After the call, the passed sds string is no longer valid and all the
 * references must be substituted with the new pointer returned by the call. */
sds sdscatlen(sds s, const void *t, size_t len) {
    //获取当前的字符串长度
    size_t curlen = sdslen(s);
    //make 更多的内存空间，内部有判断当前空间是否足够
    s = sdsMakeRoomFor(s,len);
    if (s == NULL) return NULL;
    //拷贝拼接的数据
    memcpy(s+curlen, t, len);
    //设置新的len属性
    sdssetlen(s, curlen+len);
    //为了与c语言的string 兼容，默认末尾会添加一个不计入len的 ‘\0’来标识字符串结束
    s[curlen+len] = '\0';
    return s;
}

/* Append the specified null termianted C string to the sds string 's'.
 *
 * After the call, the passed sds string is no longer valid and all the
 * references must be substituted with the new pointer returned by the call. */
sds sdscat(sds s, const char *t) {
    return sdscatlen(s, t, strlen(t));
}
```

  
  