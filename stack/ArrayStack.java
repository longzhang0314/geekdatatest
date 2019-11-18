package com.zl.geekdata.stack;

//数组实现栈
public class ArrayStack {

    private String[] items;
    private int n;
    private int count;

    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    //入栈
    public boolean push(String item) {
        if (count == n)
            return false;
        items[count++] = item;
        return true;
    }

    //出栈
    public String pop() {
        if (count == 0)
            return null;
        return items[--count];
    }
}
