package easy;

/**
 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

 The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

 Example 1:
 Input: "UD"
 Output: true
 Example 2:
 Input: "LL"
 Output: false

 */
public class JudgeRouteCircle_657 {

    public boolean judgeCircle(String moves) {
        int x = 0, y =0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L') x--;
            else if (moves.charAt(i)  == 'R') x++;
            else if (moves.charAt(i)  == 'U') y++;
            else if (moves.charAt(i)=='D') y--;
        }
        return x==0 && y==0;
    }
}
