package com.zl.geekdata.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by zhanglong on 2020/1/3
 */
public class Graph2 {

    private int v;
    private LinkedList<Integer>[] adj;

    public Graph2(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
    }

    public void addEdge2(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs2(int s,int t){
        if(s==t)return;
        boolean[] visted = new boolean[v];
        visted[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for(int i=0;i<v;i++){
            prev[i] = -1;
        }
        while (queue.size() != 0){
            int w = queue.poll();
            for(int i=0;i<adj[w].size();i++){
                int q = adj[w].get(i);
                if(!visted[q]){
                    prev[q] = w;
                    if(q==t){
                        print2(prev,s,t);
                        return;
                    }
                    visted[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print2(int[] prev, int s, int t) {
        if(prev[t] != -1 && s != t){
            print2(prev,s,prev[t]);
        }
        System.out.print(t+" ");
    }

    boolean found2 = false;
    public void dfs2(int s,int t){
        if(s==t)return;
        found2 = false;
        boolean[] visted = new boolean[v];
        visted[s] = true;
        int[] prev = new int[v];
        for(int i=0;i<v;i++){
            prev[i] = -1;
        }
        recurDfs(s,t,visted,prev);
        print2(prev,s,t);
    }

    private void recurDfs(int w, int t, boolean[] visted, int[] prev) {
        if(found2)return;
        visted[w] = true;
        for(int i=0;i<adj[w].size();i++){
            int q = adj[w].get(i);
            if(!visted[q]){
                prev[q] = w;
                recurDfs(q,t,visted,prev);
            }
        }
    }


}
