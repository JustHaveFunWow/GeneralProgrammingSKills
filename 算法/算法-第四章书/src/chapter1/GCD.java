package chapter1;

/**
 * Created by zhuoxiuwu on 2017/8/28.
 */
public class GCD {
    public static void main(String[] args) {
        System.out.println(gcd(100,25));
    }
    private static int gcd(int x, int y){
        if (y ==0)
            return y;
        if (x % y == 0)
            return y;
        return gcd(x,x % y);
    }


}
