package com.zl.geekdata.dijkstra;

import java.util.LinkedList;

/**
 * Dijkstra算法，有权图
 * Create by zhanglong on 2020/2/18
 */
public class Graph {

    private int v;
    private LinkedList<Edge>[] adj;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }


    private class Edge {
        public int sid;
        public int tid;
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    private class Vertex {
        public int id; //顶点编号id
        public int dist; //从起始点到这个顶点的距离

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }


    //根据vertex.dist构建小顶堆
    private class PriorityQueue {
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];
            this.count = v;
        }

        public Vertex poll() {
            // TODO: 留给读者实现...
            return null;
        }

        public void add(Vertex vertex) {
            // TODO: 留给读者实现...


        }

        // 更新结点的值，并且从下往上堆化，重新符合堆的定义。时间复杂度O(logn)。
        public void update(Vertex vertex) {
            // TODO: 留给读者实现...
        }

        public boolean isEmpty() {
            // TODO: 留给读者实现...
            return false;
        }
    }

    //从顶点s到t的最短距离
    public void dijkstra(int s,int t){

    }
}
