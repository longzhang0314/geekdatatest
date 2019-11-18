package com.zl.geekdata.queue;

//数组实现队列：头尾双指针
public class ArrayQueue {

    private String[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;
    }

    //入队
    public boolean qnqueue(String item) {
        if (tail == n)
            return false;
        items[tail++] = item;
        return true;
    }

    //动态入队（只要元素个数小于n就可以入队）
    public boolean dynamicEnqueue(String item) {
        if (tail == n) {
            if (head == 0)
                return false;
            //数据搬迁
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            tail = tail - head;
            head = 0;
        }
        items[tail++] = item;
        return true;
    }


    //出队
    public String dequeue() {
        if (head == tail)
            return null;
        return items[head++];
    }

    //打印
    public void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
}
