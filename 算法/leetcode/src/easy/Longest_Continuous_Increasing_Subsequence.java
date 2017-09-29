package easy;

/**
 * Created by zhuoxiuwu on 2017/9/29.
 */
public class Longest_Continuous_Increasing_Subsequence {
    public static int findLengthOfLCIS(int[] nums){

        if (nums.length == 0)
            return 0;
        int longest = 1;
        int tempLongest = 1;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i+1]>nums[i]){
                tempLongest ++;
                if (tempLongest > longest)
                    longest = tempLongest;
            }else {
                tempLongest = 1;
            }

        }

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(findLengthOfLCIS(new int[]{2,2,2,2}));
    }
}
