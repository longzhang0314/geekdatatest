package com.zl.geekdata.tree;

/**
 * Create by zhanglong on 2020/3/16
 */
public class BSTree {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //删除二叉搜索树节点
    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val == key) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            deleteNode(root.right,minNode.val);
        } else if (root.val < key) {
            deleteNode(root.left, key);
        } else if (root.val > key) {
            deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        while (node != null) {
            node = node.left;
        }
        return node;
    }


    //统计完全二叉树节点个数
    public int countNodes(TreeNode root) {
        TreeNode l = root, r = root;
        int hl = 0, hr = 0;
        while (l != null) {
            l = l.left;
            hl++;
        }
        while (r != null) {
            r = r.right;
            hr++;
        }
        if(hl==hr && hl==0) {
            return 1;
        } else if(hl == hr) {
            return (int) (Math.pow(2,hl) - 1);
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
