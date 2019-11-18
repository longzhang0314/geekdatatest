package com.zl.geekdata.tree;

/*
 * 二叉查找树
 * */
public class BinarySearchTree {
    //要查找的树根节点
    private Node tree;

    //通过根节点和要查找的数据查到对应的data
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data)
                p = p.left;
            else if (data > p.data)
                p = p.right;
            else
                return p;
        }
        return null;
    }

    //插入数据
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }
        }
    }

    //节点删除
    public void delete(int data) {
        //待删除节点
        Node p = tree;
        //待删除节点父节点
        Node pp = null;
        //定位到待删除节点和父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data < p.data)
                p = p.left;
            else
                p = p.right;
        }
        if (p == null)
            return;
        //开始删除p节点
        //待删除节点有两个子节点
        if (p.left != null && p.right != null) {
            //待删除右子树最小节点
            Node minP = p.right;
            //最小节点父节点
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            //替换data
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }
        //待删除节点有一个子节点或为叶子节点
        //待删除节点的子节点
        Node child;
        if (p.left != null)
            child = p.left;
        else if (p.right != null)
            child = p.right;
        else
            child = null;

        if (pp == null)
            tree = child;//删除根节点
        else if (pp.left == p)
            pp.left = child;
        else
            pp.right = child;

    }


    public int height(Node tree) {
        if (tree == null)
            return 0;
        return Math.max(height(tree.left), height(tree.right)) + 1;
    }


    //定义Node节点
    class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
