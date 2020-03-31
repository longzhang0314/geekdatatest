package com.zl.geekdata.quick_union_uf;

/**
 * 并查集
 * Create by zhanglong on 2020/3/16
 */
public class QuickUnionUF {
    private int[] roots;

    public QuickUnionUF(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    private int findRoot(int i) {
        int root = i;
        //找到root
        while (root != roots[root]) {
            root = roots[root];
        }
        //路径压缩
        while (i != roots[i]) {
            int temp = roots[i];
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
        roots[proot] = qroot;
    }
}
