package com.zl.geekdata.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by zhanglong on 2020/1/3
 */
public class Graph {
    //顶点个数
    private int v;
    //存储结构，邻接表(元素为链表的数组)
    private LinkedList<Integer>[] adj;

    //构造一个v个顶点的图
    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    //添加一个边
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    //bfs 使用queue记录实现
    public void bfs(int s, int t) {
        if (s == t)
            return;
        //已被访问的顶点
        boolean[] visted = new boolean[v];
        visted[s] = true;
        //已被访问，但相连顶点还未被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        //访问过的路径，存储的是当前顶点访问路径中的上一个顶点
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        //从队列中头顶点开始访问
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                //如果该顶点没被访问，做后续操作
                if (!visted[q]) {
                    //记录该顶点的路径
                    prev[q] = w;
                    //找到了
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visted[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    //打印s->t的路径
    private void print(int[] prev, int s, int t) {
        //递归打印数组对应值的路径
        if (t != s && prev[t] != -1) {
            //要打印s->t，需要先打印s->t的前驱路径
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    boolean found = false;

    //dfs 回溯思想，递归实现
    public void dfs(int s, int t) {
        found = false;
        boolean[] visted = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        recurDfs(s, t, visted, prev);
        print(prev, s, t);
    }


    private void recurDfs(int w, int t, boolean[] visted, int[] prev) {
        //先判断是否找到了
        if (found)
            return;
        //当前顶点已访问
        visted[w] = true;
        //如果找到了
        if (w == t) {
            found = true;
            return;
        }
        //没找到从顶点向下走
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visted[q]) {
                prev[q] = w;
                recurDfs(q, t, visted, prev);
            }
        }
    }

}
