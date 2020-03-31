package com.zl.geekdata.lrucache;

import java.util.HashMap;

/**
 * Create by zhanglong on 2020/3/29
 */
public class LRUCache {
    private DoubleList cache;
    private HashMap<Integer, ListNode> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        //找到数据
        int val = map.get(key).val;
        //将数据放到最前面
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        ListNode newNode = new ListNode(key, val);
        //缓存中是否已存在该数据
        if (map.containsKey(key)) {
            //删掉数据并放到头部
            cache.remove(map.get(key));
        } else {
            //直接放到头部并放入map，如果size大小等于容量，先删除尾部元素
            if (cache.size() == capacity) {
                ListNode last = cache.removeLast();
                //map中也删除
                map.remove(last.key);
            }
            map.put(key, newNode);
        }
        cache.addFirst(newNode);
    }
}

class ListNode {
    public ListNode prev;
    public ListNode next;
    public int key;
    public int val;

    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class DoubleList {
    private ListNode head;
    private ListNode tail;
    private int size;

    public DoubleList() {
        this.head = new ListNode(0, 0);
        this.tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //链表头部添加节点
    public void addFirst(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        size++;
    }

    //删除链表中的node节点
    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    //删除链表最后一个节点，并返回该节点
    public ListNode removeLast() {
        if (tail.prev == head) return null;
        ListNode last = tail.prev;
        remove(last);
        return last;
    }

    //返回链表长度
    public int size() {
        return size;
    }
}

