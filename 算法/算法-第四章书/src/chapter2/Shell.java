package chapter2;

import base.StdOut;

/**
 * Created by zhuoxiuwu on 2017/9/7.
 */

/**
 * 希尔排序可以看做 插入排序的优化版
 * 希尔排序先对数组的局部进行排序，最终再用插入排序将局部有序的数组排序
 * 希尔排序更高校的原因是 它权衡了子数组的规模和有序性
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        //设置初始的间距
        int h = 1;
        if (h <N/3)
            h = 3*h+1;
        while (h>1){
            for (int i = h; i < N; i++) {
                for (int j = i; j >=h&& less(a[j],a[j-h]) ; j-=h) {
                    exch(a,j,j-h);
                }
            }
            h = h/3;
        }

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int x, int j) {
        Comparable temp = a[x];
        a[x] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
            StdOut.println();
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }
}
