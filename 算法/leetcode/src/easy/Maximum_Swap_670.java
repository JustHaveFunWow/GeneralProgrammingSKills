package easy;

/*
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

        Example 1:
        Input: 2736
        Output: 7236
        Explanation: Swap the number 2 and the number 7.
        Example 2:
        Input: 9973
        Output: 9973
        Explanation: No swap.
        Note:
        The given number is in the range [0, 108]*/
public class Maximum_Swap_670 {
    public static int maximumSwap(int num) {
        // 记录每位数字后面比它大的数字，从后向前遍历
        if (num <10)
            return num;
        char[] strArr = String.valueOf(num).toCharArray();
        int[] dp = new int[strArr.length];
        char max = strArr[strArr.length-1];
        dp[strArr.length-1] = strArr.length-1;
        for (int i = strArr.length-2; i >=0; i--) {
            if (strArr[i]>max){
                max = strArr[i];
                dp[i] =i;
            }else {
                dp[i] =dp[i+1];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i]!=i &&strArr[i]!=strArr[dp[i]]){
                char tmp = strArr[dp[i]];
                strArr[dp[i]] =strArr[i];
                strArr[i] = tmp;
                break;
            }
        }
        return Integer.valueOf(new String(strArr));

    }

    public static void main(String[] args) {
        System.out.println(maximumSwap(10));
    }
}
