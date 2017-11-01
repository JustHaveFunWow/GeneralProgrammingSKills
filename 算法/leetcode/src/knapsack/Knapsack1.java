package knapsack;

/**
 * 经典 0 -1 背包问题
 * 有N件物品和一个容量为V的背包。放入第i件物品耗费的空间是Ci，得到的价值是Wi。求解将哪些物品装入背包可使价值总和最大。
 */
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
        int[][] G = new int[n+1][c+1];
        for (int i = 1; i < n+1; i++) {
            w[i] = weight[i-1];
            v[i] = value[i-1];
        }
        int[] values = new int[c+1];
        for (int i = 1; i < n+1; i++) {
            for (int t = c; t >= w[i] ; t--) {
                if (values[t] < values[t-w[i]]+v[i]){
                    values[t] = values[t-w[i]]+v[i];
                    G[i][t] =1;
                }
            }
        }

        System.out.println("最大价值为: "+values[c]);
        System.out.println("装入背包的物品编号为: ");

        int i = n;
        int j = c;
        while (i>0){
            if (G[i][j] ==1){
                System.out.print(i+" ");
                j -=w[i];
            }
            i--;
        }
    }
}
