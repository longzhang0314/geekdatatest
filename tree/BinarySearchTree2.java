package com.zl.geekdata.tree;

/**
 * Create by zhanglong on 2019/12/14
 */
public class BinarySearchTree2 {

    static class Node {
        private int data;
        private Node left;
        private Node right;

        Node(int data) {
            this.data = data;
        }
    }

    private Node tree;

    public Node findMin() {
        if (tree == null)
            return null;
        Node p = tree;
        while (p != null) {
            p = p.left;
        }
        return p;
    }

    public Node findMax() {
        if (tree == null)
            return null;
        Node p = tree;
        while (p != null) {
            p = p.right;
        }
        return p;
    }

    //查找
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    //插入到叶子节点
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
                }
                p = p.left;
            } else if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                }
                p = p.right;
            }
        }
    }


    //删除
    public void delete(int data) {
        Node p = tree;
        Node pp = null;
        //找到待删除节点
        while (p != null && p.data != data) {
            pp = p;
            if (data < p.data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        //没找到
        if (p == null)
            return;
        //待删除节点有两个子节点
        if (p.left != null && p.right != null) {
            //找到待删除节点右子节点最小节点
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            //转化为删除minP
            p = minP;
            pp = minPP;
        }
        //待删除节点有一个或0个子节点
        Node child;
        if (p.left != null)
            child = p.left;
        else if (p.right != null)
            child = p.right;
        else
            child = null;
        //删除
        if (pp == null)
            tree = child;
        else if (pp.left == p)
            pp.left = child;
        else
            pp.right = child;

    }


}
