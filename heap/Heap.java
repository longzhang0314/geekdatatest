package com.zl.geekdata.heap;

/**
 * Create by zhanglong on 2019/12/16
 */
public class Heap {

    private int[] a;
    private int n;
    private int count;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    //插入
    public void insert(int data) {
        if (count >= n)return;
        ++count;
        a[count] = data;
        int i = count;
        //向上堆化
        while (i/2>0 && a[i]>a[i/2]){
            swap(a,i,i/2);
            i = i/2;
        }
    }

    private void swap(int[] a, int i, int i1) {
        int temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }

    //删除堆顶元素
    public void removeMax(){
        if(count<=0)return;
        //最后一个元素替换堆顶元素
        a[1] = a[count];
        --count;
        //堆化
        heapify(a,count,1);
    }

    /**
     * 顺序堆化
     * @param a  数组
     * @param n  当前元素个数
     * @param i  从哪个索引开始
     */
    private void heapify(int[] a, int n, int i) {
        while (true){
            int max = i;
            if(i*2<=n && a[i*2]>a[i])max = i*2;
            if(i*2+1<=n && a[i*2+1]>a[max])max = i*2+1;
            if(max==i)break;
            swap(a,i,max);
            i = max;
        }
    }

}
