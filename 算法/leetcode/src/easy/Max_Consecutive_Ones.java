package easy;

/*Given a binary array, find the maximum number of consecutive 1s in this array.

        Example 1:
        Input: [1,1,0,1,1,1]
        Output: 3
        Explanation: The first two digits or the last three digits are consecutive 1s.
        The maximum number of consecutive 1s is 3.
        Note:

        The input array will only contain 0 and 1.
        The length of input array is a positive integer and will not exceed 10,000*/
public class Max_Consecutive_Ones {
    public static int findMaxConsecutiveOnes(int[] nums){
        int max = 0;
        int length = nums.length;
        int index =0;
        int limit = 0;
        while (index<length){
            if (nums[index]!=0){
                limit++;
                if (limit>max)
                    max = limit;
            }else
                limit =0;
            index++;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 0, 1, 1, 1,0,1,1,1,1,1,0,1};
        System.out.println(findMaxConsecutiveOnes(array));
    }
}
