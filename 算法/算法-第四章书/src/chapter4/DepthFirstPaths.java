package chapter4;

import java.util.Stack;

/**
 * Created by zhuoxiuwu on 2017/9/13.
 */
public class DepthFirstPaths {
    private  boolean[] marked;// 记录是否被dfs访问过
    private int[] edgesTo; //记录最后一个得到当前节点的顶点
    private int s;//搜索的起点

    public DepthFirstPaths(Graph g, int s){
        marked = new boolean[g.getVerticals()];
        edgesTo = new int[g.getVerticals()];
        this.s = s;
    }

    private void dfs (Graph g, int v){
        marked[v] =true;
        for (Integer w : g.getAdjacency(v)) {
            if (!marked[w]){
                edgesTo[w] = v;
                dfs(g,w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Stack<Integer> pathTo(int v){
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x !=s ; x= edgesTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
