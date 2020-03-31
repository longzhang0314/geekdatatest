package com.zl.geekdata.topology_sort;

import java.util.LinkedList;

/**
 * 拓扑排序
 * Create by zhanglong on 2020/2/17
 */
public class Graph {

    //顶点个数
    private int v;

    //邻接表
    private LinkedList<Integer>[] adj;

    //初始化图
    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { //s先于t,s->t
        adj[s].add(t);
    }


    //Kahn算法(贪心)
    public void topoSortByKahn() {
        //统计所有点的入度（依赖其他顶点，有其他顶点指向该顶点）
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        //添加临时队列，保存入度为0的顶点
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }
        //排序打印
        while (!queue.isEmpty()) {
            int i = queue.remove();
            //先打印顶点
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); i++) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0)
                    queue.add(k);
            }
        }
    }

    public void topoSortByDfs() {
        //创建逆邻接表 （逆序原因：递归时先打印可达的顶点，最后打印自身）
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            inverseAdj[i] = new LinkedList<>();
        }
        //通过邻接表生成逆邻接表
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);  //i->w
                inverseAdj[w].add(i);   //w->i
            }
        }
        //记录遍历过的顶点
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        for (int j = 0; j < inverseAdj[vertex].size(); j++) {
            int w = inverseAdj[vertex].get(j);
            if (visited[w])
                continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        //先打印出可达的所有顶点，再打印顶点本身
        System.out.print("->" + vertex);
    }
}
