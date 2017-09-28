package easy;

/**
 *The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

 Given two integers x and y, calculate the Hamming distance.

 Note:
 0 ≤ x, y < 231.

 Example:

 Input: x = 1, y = 4

 Output: 2

 Explanation:
 1   (0 0 0 1)
 4   (0 1 0 0)
 ↑   ↑

 The above arrows point to positions where the corresponding bits are different.
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
