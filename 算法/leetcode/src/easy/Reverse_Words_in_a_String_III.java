package easy;

/*Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

        Example 1:
        Input: "Let's take LeetCode contest"
        Output: "s'teL ekat edoCteeL tsetnoc"
        Note: In the string, each word is separated by single space and there will not be any extra space in the string.*/
public class Reverse_Words_in_a_String_III {
    public String reverseWords(String s) {
        String[] ans=s.split(" ");
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<ans.length;i++){
            for(int j=ans[i].length()-1;j>=0;j--){
                sb.append(ans[i].charAt(j));
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
