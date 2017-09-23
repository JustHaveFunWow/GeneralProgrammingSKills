package easy;

/**
 * Created by zhuoxiuwu on 2017/9/23.
 */
public class Hamiing_Distance_461 {
    public int hammingDistance(int x, int y) {
        int count = 0;
        int exc = x ^ y;
        for (int i =0; i <32; ++i){
            count += (exc>>i) & 1;
        }
        return count;
    }
}
