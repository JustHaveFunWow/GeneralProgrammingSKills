package chapter4;

/**
 * Created by zhuoxiuwu on 2017/9/12.
 */
public class DepthFirstSearch {

    private boolean[] marked;//标记已经搜索过的v
    private int count;//搜索次数
    private Graph g;
    public DepthFirstSearch(Graph g){
        this.g = g;
        marked = new boolean[g.getVerticals()];
    }

    // v表示 点的序号
    public void dfs(int  v){
        marked[v] =true;
        count++;
        for (Integer integer : g.getAdjacency(v)) {
            if (!marked[integer]){//如果没有搜索过，则继续s搜索
                System.out.print("- "+integer);
                dfs(integer);
            }
        }
    }
    public void clear(){
        count =0;
        for (int i = 0; i < marked.length; i++) {
            marked[i] =false;
        }
    }

    public boolean isMarked(int vertical){
        return marked[vertical];
    }

    /**
     * 搜索所使用的步数
     * @return
     */
    public int gfsCount(){
        return count;
    }


}
