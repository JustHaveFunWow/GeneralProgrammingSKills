package easy;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by zhuoxiuwu on 2017/9/25.
 */
public class Array_Partition_I_561 {
    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        //sort,first
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }


}
