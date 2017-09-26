package easy;

/**
 * Created by zhuoxiuwu on 2017/9/26.
 */
public class Number_Complement_476 {
    public static int findComplement(int num) {
        int temp = num;
        int flag = Integer.MAX_VALUE;
        while (temp>0){
            temp = temp>>1;
            flag = flag<<1;
        }
        return ~flag & ~num;
    }

    public static void main(String[] args) {
        System.out.println(findComplement(5));
    }
}
