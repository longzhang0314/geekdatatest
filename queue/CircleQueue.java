package com.zl.geekdata.queue;

//环形队列
public class CircleQueue {
    private String[] items;
    private int n;
    private int head = 0;
    private int tail = 0;

    public CircleQueue(int capacity) {
        this.items = new String[capacity];
        this.n = n;
    }

    //入
    public boolean enqueue(String item) {
        if ((tail + 1) % n == head)
            return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    //出
    public String dequeue() {
        if (head == tail)
            return null;
        String value = items[head];
        head = (head + 1) % n;
        return value;
    }

    public void printAll() {
        if (n == 0)
            return;
        for (int i = head; i % n != tail; i = (i + 1) % n) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

}
