package com.zl.geekdata.trie;

/**
 * Create by zhanglong on 2020/1/15
 */
public class Trie2 {
    //定义节点
    private class TrieNode {
        private char data;
        private TrieNode[] children;
        private boolean isEnd = false;

        TrieNode(char data) {
            this.data = data;
        }
    }

    private TrieNode root = new TrieNode('/');

    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEnd = true;
    }

    public boolean search(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) return false;
            p = p.children[index];
        }
        return p.isEnd;
    }

    public boolean startsWith(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) return false;
        }
        return true;
    }

}
