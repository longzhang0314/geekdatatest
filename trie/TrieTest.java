package com.zl.geekdata.trie;

/**
 * Create by zhanglong on 2020/1/15
 */
public class TrieTest {

    class TrieNode{
        char data;
        TrieNode[] children;
        boolean isEnd;
        TrieNode(char data){
            this.data = data;
        }
    }

    TrieNode tree = new TrieNode('/');

    public boolean find(char[] text){
        TrieNode p = tree;
        for(int i=0;i<text.length;i++){
            int index = text[i] - 'a';
            if(p.children[index]==null)return false;
            p = p.children[index];
        }
        return p.isEnd;
    }

    public void insert(char[] text){
        TrieNode p = tree;
        for(int i=0;i<text.length;i++){
            int index = text[i] - 'a';
            if(p.children[index]==null){
                //插入该字符
                TrieNode node = new TrieNode(text[i]);
                p.children[index] = node;
            }
            p = p.children[index];
        }
        p.isEnd = true;
    }
}
