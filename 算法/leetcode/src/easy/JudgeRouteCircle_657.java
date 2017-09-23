package easy;

/**
 * Created by zhuoxiuwu on 2017/9/23.
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
