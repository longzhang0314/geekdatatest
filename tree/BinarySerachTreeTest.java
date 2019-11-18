package com.zl.geekdata.tree;

public class BinarySerachTreeTest {
    class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node tree;

    //查找
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


    //插入
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


    //删除
    public void delete(int data) {
        Node p = tree;
        Node pp = null;
        while (p != null && data != p.data) {
            if (data < p.data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }

        if (p.left != null && p.right != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            //此处已完成
            p.data = minP.data;
            //重新将待删除节点定义为minP,与下述情况统一处理
            p = minP;
            pp = minPP;
        }

        //待删除节点为叶子节点或只有一个子节点
        //待删除节点的子节点
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }
        //至此，父节点pp,待删除p,子节点child
        //删除根节点
        if (pp == null) {
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }


    public void delete2(int data) {
        Node p = tree;
        Node pp = null;
        while (p != null && data != p.data) {
            pp = p;
            if (data < p.data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }

        if (p.left != null && p.right != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        if (pp == null) {
            tree = child;
        } else if (p.left != null) {
            p.left = child;
        } else {
            p.right = child;
        }
    }
}
