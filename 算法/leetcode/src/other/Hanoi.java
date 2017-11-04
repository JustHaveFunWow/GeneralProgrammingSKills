package other;

/**
 * Created by zhuoxiuwu on 2017/11/4.
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(4,'A','B','C');
    }
    public static void hanoi(int n ,char A,char B,char C){
        if (n ==1){
            System.out.println(A +"移动到"+C);
        }else {
            hanoi(n-1,A,C,B);
            System.out.println(A+"移动"+C);
            hanoi(n-1,B,A,C);
        }

    }
}
