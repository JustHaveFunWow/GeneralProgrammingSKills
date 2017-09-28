package easy;

/**Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

        You may assume the integer do not contain any leading zero, except the number 0 itself.

        The digits are stored such that the most significant digit is at the head of the list.*/
public class plus_one_66 {
    public int[] plusOne(int[] digits){
        if(digits==null||digits.length==0) return digits;
        int one=1;
        int i=digits.length-1;
        while(i>=0){
            int carry=(digits[i]+one)/10;
            int digit=(digits[i]+one)%10;
            digits[i]=digit;
            if(carry==0) return digits;
            else i--;
        }
        //说明首位也进一了
        int[] newDigits=new int[digits.length+1];
        newDigits[0]=1;
        for(int j=1;j<newDigits.length;j++){
            newDigits[j]=0;
        }
        return newDigits;
    }
}
