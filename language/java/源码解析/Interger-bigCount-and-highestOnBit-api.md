### highestOnBit
> 根据输入的数值，取这个数的二进制形式最左边的最高一位且高位后面全部补零
比如输入【0000,0000,0000,0001,0010,1000,0010,0001】则会返回
【0000,0000,0000,0001,0000,0000,0000,0000】

### 实现方式
实现方式是先将原值最高位以下的位全改为1.
比如【0000,0000,0000,0001,0010,1000,0010,0001】
变成 【0000,0000,0000,0001,1111,1111,1111,1111】
最后用这个值减去 无符号右移一位的值；将所有最高位以下的位抹除成0。
```
public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >>  1); //使得最高位往右2位都是1（如果最高位右边位数足够的话）
        i |= (i >>  2);//最高位往右4位都是1
        i |= (i >>  4);//最高位往右8位都是1
        i |= (i >>  8);//最高位往右16位都是1
        i |= (i >> 16);//最高位往右32位都是1
        return i - (i >>> 1);
    }
```

### bitCount
#### 实现方式
采用分组归并计算，先两两一组相加，之后四个四个一组相加，接着八个八个，最后就得到各位之和了。
第一行是计算每两位中的 1 的个数 , 并且用该对应的两位来存储这个个数 ,
如 : 01101100 -> 01011000 , 即先把前者每两位分段 01 10 11 00 , 分别有 1 1 2 0 个 1, 用两位二进制数表示为 01 01 10 00, 合起来为 01011000.
第二行是计算每四位中的 1 的个数 , 并且用该对应的四位来存储这个个数 .
如 : 01101100 经过第一行计算后得 01011000 , 然后把 01011000 每四位分段成 0101 1000 , 段内移位相加 : 前段 01+01 =10 , 后段 10+00=10, 分别用四位二进制数表示为 0010 0010, 合起来为 00100010 .
下面的各行以此类推 , 分别计算每 8 位 ,16 位 ,32 位中的 1 的个数 .

```java

public static int bitCount(int i) {
        // HD, Figure 5-2
        i = i - ((i >>> 1) & 0x55555555);//
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }
```
具体的代码分析可以参考:

参考 博文 （http://blog.csdn.net/cor_twi/article/details/53720640）
#### 

上面的实现方式有些复杂，这里再提出一个遍历获取的方式。
假设原数为num, num & (num - 1)可以快速地移除最右边的bit 1（如果不能理解，你可以在草稿本上尝试一下过程）， 一直循环到num为0, 总的循环数就是num中bit 1的个数。


```java
public static int bitCount (int i){
    int count = 0;
    while(i){
        ++count;
        i &= (i-1);
    }
    return count;
}

```