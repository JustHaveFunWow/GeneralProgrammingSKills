package easy;

/**
 * Created by zhuoxiuwu on 2017/9/25.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.

 For example, given k = 3,
 Return [1,3,3,1].

 Note:
 Could you optimize your algorithm to use only O(k) extra space?
 */
public class Pascal_s_Triangle_II {
    public static List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        if (rowIndex<=0)
            return res;
        for (int i = 1; i <=rowIndex; i++) {
            for (int j = res.size()-2  ; j >=0; j--) {
                res.set(j+1,res.get(j)+res.get(j+1));
            }
            res.add(1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getRow(2));
    }
}
