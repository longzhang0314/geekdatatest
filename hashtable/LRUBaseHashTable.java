package com.zl.geekdata.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存淘汰
 * 散列表存储双向链表节点，节点本身连成一条双向链表
 * 新加入的节点放在缓存头部，如果满了就删除最后一个节点
 * Create by zhanglong on 2019/12/13
 */
public class LRUBaseHashTable<K, V> {

    //双向链表
    static class Node<K, V> {
        private K key;
        private V value;

        private Node<K, V> pre;
        private Node<K, V> next;

        Node() {
        }

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int length;
    private Node<K, V> headNode;
    private Node<K, V> tailNode;
    //散列表存储key
    private Map<K, Node<K, V>> table;

    public LRUBaseHashTable(int capacity) {
        this.length = 0;
        this.capacity = capacity;
        headNode = new Node<>();//哨兵
        tailNode = new Node<>();//哨兵
        headNode.next = tailNode;
        tailNode.pre = headNode;
        table = new HashMap<>();
    }

    public LRUBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 添加
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        Node<K, V> node = table.get(key);
        //当前节点不存在值
        if (node == null) {
            Node<K, V> newNode = new Node<>(key, value);
            table.put(key, newNode);
            //添加到链表头部
            addNode(newNode);
            //超过长度，删除链表尾部元素
            if (++length > capacity) {
                //弹出链表尾部元素
                Node<K, V> tail = popTail();
                //删除散列表中该元素
                table.remove(tail.key);
                length--;
            }
        } else {
            //存在直接覆盖
            node.value = value;
            //并移动到链表头部
            moveToHead(node);
        }
    }

    /**
     * 移除节点数据
     *
     * @param key
     */
    public void remove(K key) {
        Node<K, V> node = table.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        length--;
        table.remove(key);
    }

    /**
     * 获取节点元素value
     *
     * @return
     */
    public V get(K key) {
        Node<K, V> node = table.get(key);
        if (node == null)
            return null;
        moveToHead(node);
        return node.value;
    }

    /**
     * 从头打印
     */
    public void printAll() {
        Node node = headNode.next;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    //弹出尾部元素
    private Node<K, V> popTail() {
        Node<K, V> node = tailNode.pre;
        removeNode(node);
        return node;
    }

    //节点加入头部
    private void moveToHead(Node<K, V> node) {
        //先移除
        removeNode(node);
        //加入链表头
        addNode(node);
    }

    //移除节点
    private void removeNode(Node<K, V> node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    //新节点加入链表头部
    private void addNode(Node<K, V> newNode) {
        newNode.next = headNode.next;
        newNode.pre = headNode;

        headNode.next.pre = newNode;
        headNode.next = newNode;
    }


}
