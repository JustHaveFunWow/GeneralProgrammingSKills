package knapsack;

/**
 * 经典 0 -1 背包问题
 * 有N件物品和一个容量为V的背包。放入第i件物品耗费的空间是Ci，得到的价值是Wi。求解将哪些物品装入背包可使价值总和最大。
 */
/*用贪心算法求解0-1背包问题的步骤是，
        首先计算每种物品单位重量的价值vi/wi；
        然后，将物品的vi/wi的大小进行降序进行排列，依贪心选择策略，将尽可能多的单位重量价值最高的物品装入背包。
        若将这种物品全部装入背包后，背包内的物品总量未超过c，则选择单位重量价值次高的物品并尽可能多地装入背包。
        依此策略一直进行下去，直到背包装满为止*/
public class Knapsack1 {
    public static void Test(){
        int w[] ={3,2,2};
        int v[] = {5,10,20};
        knapsackOptimal(5,w,v);
    }


    public static void main(String[] args) {
        // n 件物品， 容量为v
        Test();
    }

    public static void knapsackOptimal(int c,int[] weight,int[] value){
        int n = weight.length;
        int[] w = new int[n+1];
        int[] v = new int[n+1];
        //G[i][j] 为二维组，表示容量为j时，第i个物品的选择
        int[][] G = new int[n+1][c+1];
        for (int i = 1; i < n+1; i++) {
            w[i] = weight[i-1];
            v[i] = value[i-1];
        }
        int[] values = new int[c+1];
        //整个G[i][t] 表的生成是自尚向下，自右向左的
        //第一个循环表示 只有[1,i]物品可选的时候 背包的选择
        //
        for (int i = 1; i < n+1; i++) {
            //容量从 最大递减
            for (int t = c; t >= w[i] ; t--) {//算出容量从c到w[i]时 对index i 物品的选择，则t容量至少为 weight[i] ,否则选择为0 表示没有可选
                if (values[t] < values[t-w[i]]+v[i]){
                    values[t] = values[t-w[i]]+v[i];
                    G[i][t] =1;
                }
            }
        }

        System.out.println("最大价值为: "+values[c]);
        System.out.println("装入背包的物品编号为: ");

        int i = n;//商品数量[0-n]
        int j = c;//[容量 0-C]
        //从表的生成规律可以看出，最佳的选择位于右下角. 寻找答案的规律是，先保证容量不变，
        while (i>0){
            if (G[i][j] ==1){
                System.out.print(i+" ");
                j -=w[i];
            }
            i--;
        }
    }
}
