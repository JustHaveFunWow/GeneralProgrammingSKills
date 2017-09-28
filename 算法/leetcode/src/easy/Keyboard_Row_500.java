package easy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below..
 Example 1:
 Input: ["Hello", "Alaska", "Dad", "Peace"]
 Output: ["Alaska", "Dad"]
 Note:
 You may use one character in the keyboard more than once.
 You may assume the input string will only contain letters of alphabet.
 */
public class Keyboard_Row_500 {
    public static String[] findWords(String[] words){
        String firstLine ="QWERTYUIOP";
        String secondLine = "ASDFGHJKL";
        String thirdLine = "ZXCVBNM";
        String[] lines={firstLine,secondLine,thirdLine};
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                hashMap.put(line.charAt(i),i);
            }
        }
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                hashMap.put(line.charAt(j),i);
            }
        }
        ArrayList<String> res = new ArrayList<>();
        for (String word : words) {
            if (word == null || word.length() == 0)
                continue;
            boolean isInlineWord =true;
            int line = hashMap.get(Character.toUpperCase(word.charAt(0)));
            for (int i = 1; i < word.length(); i++) {
                if (hashMap.get(Character.toUpperCase(word.charAt(i)))!=line){
                    isInlineWord =false;
                    break ;
                }
            }
            if (isInlineWord){
                res.add(word);

            }
        }

        return res.toArray(new String[res.size()]);


    }

    public static void main(String[] args) {
        String[] testwords =new String[]{"cccd"};
        System.out.println(findWords(testwords));
    }
}
