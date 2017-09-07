package chapter2;

import base.StdOut;

/**
 * Created by zhuoxiuwu on 2017/9/4.
 */
public class Insertion {
    //类似插牌，对于大部分有序序列有优势
    //如果是反序的序列，则在每次比较中都会交换位置，
    public static void sort(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            //把a[i]插入到 0...i-1中

            for (int j = i; j >0&&less(a[j],a[j-1]) ; j--) {
                exch(a,j,j-1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w)<0;
    }
    private static void exch(Comparable[] a,int x, int j){
        Comparable temp = a[x];
        a[x] =a[j];
        a[j] = temp;
    }
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] +" ");
            StdOut.println();
        }
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            if (less(a[i],a[i-1]))
                return false;
        }
        return true;
    }
}
