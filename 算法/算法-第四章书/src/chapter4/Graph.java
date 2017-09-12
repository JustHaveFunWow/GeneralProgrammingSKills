package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhuoxiuwu on 2017/9/12.
 */
public class Graph {
    private int verticals;//顶点个数
    private int edges;//边数
    private ArrayList<ArrayList<Integer>> adjacency;//顶点连接列表

    public Graph(int vertical){
        this.verticals = vertical;
        this.edges = 0;
        //也可以用数组表示
        adjacency = new ArrayList<>();
        for (int i = 0; i < vertical; i++) {
            adjacency.set(i, new ArrayList<>());
        }
    }
    public int getVerticals(){
        return verticals;
    }

    public int getEdges(){
        return edges;
    }
    public void addEdge(int verStart,int verEnd){
        adjacency.get(verStart).add(verEnd);
        adjacency.get(verEnd).add(verStart);
    }

    /**
     * 获取某个顶点的临接点
     * @param vertical
     * @return
     */
    public List<Integer> getAdjacency(int vertical){
        return adjacency.get(vertical);
    }
}
