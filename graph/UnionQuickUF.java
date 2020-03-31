package com.zl.geekdata.graph;

/**
 * 并查集
 * Create by zhanglong on 2020/3/24
 */
public class UnionQuickUF {
    private int[] roots;
    private int[] rank;
    private int count;

    public UnionQuickUF(int n) {
        this.roots = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
            rank[i] = 1;
        }
    }

    private int findRoot(int i) {
        int root = i;
        //找到root节点
        while (root != roots[root]) {
            root = roots[root];
        }
        //路径压缩
        while (i != roots[root]) {
            int temp = roots[i];
            //i直接指向root
            roots[i] = root;
            i = temp;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }



    public void union(int p, int q) {
        int proot = findRoot(p);
        int qroot = findRoot(q);
        if (proot == qroot)
            return;
        //平衡性优化
        if (rank[proot] > rank[qroot]) {
            roots[qroot] = proot;
            rank[proot] += rank[qroot];
        } else {
            roots[proot] = qroot;
            rank[qroot] += rank[proot];
        }
        count--;
    }

    public int count() {
        return count;
    }
}
