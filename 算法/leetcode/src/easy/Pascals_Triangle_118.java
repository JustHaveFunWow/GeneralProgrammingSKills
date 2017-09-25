package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuoxiuwu on 2017/9/25.
 */
public class Pascals_Triangle_118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows<=0)
            return res;
        ArrayList<Integer> pre = new ArrayList<>();
        pre.add(1);
        res.add(pre);
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> cur = new ArrayList<>();
            cur.add(1);
            List<Integer> prev = res.get(i-1);
            for (int j = 0; j < pre.size()-1; j++) {
                cur.add(pre.get(j)+pre.get(j+1));
            }
            cur.add(1);
            res.add(cur);
            pre = cur;
        }
        return res;
    }
}
