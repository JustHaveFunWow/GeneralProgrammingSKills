package easy;

/**
 * Created by zhuoxiuwu on 2017/11/4.
 */
/*The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

        Given two integers x and y, calculate the Hamming distance.

        Note:
        0 ≤ x, y < 2^31.

        Example:

        Input: x = 1, y = 4

        Output: 2

        Explanation:
        1   (0 0 0 1)
        4   (0 1 0 0)
        ↑   ↑

        The above arrows point to positions where the corresponding bits are different.*/
public class Hamming_Distance_461 {
    public int hammingDistance(int x, int y) {
        int xoy = x ^ y,count =0;
        while (xoy>0){
            count+=xoy&1;
            xoy = xoy >>1;
        }
        return count;
    }
}
