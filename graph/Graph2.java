package com.zl.geekdata.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by zhanglong on 2020/2/24
 */
public class Graph2 {

    public static void main(String[] args) {
    }

    private int v;
    private LinkedList<Integer>[] adj;

    public Graph2(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEage(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs(int s, int t) {
        if (s == t)
            return;
        boolean[] visted = new boolean[v];
        Queue<Integer> queue = new LinkedList<>();
        int[] prev = new int[v];
        visted[s] = true;
        queue.offer(s);
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visted[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(s, t, prev);
                        return;
                    }
                    visted[q] = true;
                    queue.offer(q);
                }
            }
        }

    }

    private void print(int s, int t, int[] prev) {
        if (s != t && prev[t] != -1) {
            print(s, prev[t], prev);
        }
        System.out.print(t + " ");
    }

    boolean found  = false;

    public void dfs(int s, int t) {
        found = false;
        boolean[] visted = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        recurDfs(s, t, visted, prev);
        print(s, t, prev);
    }

    private void recurDfs(int w, int t, boolean[] visted, int[] prev) {
        if (found) return;
        visted[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visted[q]) {
                prev[q] = w;
                recurDfs(q, t, visted, prev);
            }
        }
    }


}
