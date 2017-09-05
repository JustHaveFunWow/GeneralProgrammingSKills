package chapter2;

import base.StdOut;

/**
 * Created by zhuoxiuwu on 2017/9/4.
 */
public class Insertion {
    //类似插牌，对于大部分有序序列有优势
    public static void sort(Comparable[] a){

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
