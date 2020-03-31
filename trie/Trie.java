package com.zl.geekdata.trie;

/**
 * Create by zhanglong on 2020/1/7
 */
public class Trie {
    class TrieNode{
        char data;
        TrieNode[] children = new TrieNode[26];
        boolean isEndChar = false;
        TrieNode(char data){
            this.data = data;
        }
    }

    TrieNode root = new TrieNode('/');

    public void insert(char[] text){
        TrieNode p = root;
        for(int i=0;i<text.length;i++){
            int index = text[i] - 'a';
            if(p.children[index]==null){
                //插入该字符
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndChar = true;
    }

    public boolean find(char[] text){
        TrieNode p = root;
        for(int i=0;i<text.length;i++){
            int index = text[i]-'a';
            if(p.children[index]==null){
                return false;
            }
            p = p.children[index];
        }
        return p.isEndChar;
    }

}
