package com.zl.geekdata.heap;

/**
 * Create by zhanglong on 2019/12/19
 */
public class HeapSort {

    public void sort(int[] arr) {
        if (arr.length - 1 <= 1){
            return;
        }

        //1.建堆
        buildheap(arr);
        //2.排序
        heapSort(arr);
    }

    private void heapSort(int[] arr) {
        int k = arr.length-1;
        while (k>0){
            swap(arr,k,0);
            heapify(arr,--k,0);
        }
    }

    private void buildheap(int[] arr) {
        //从i/2往后堆化 最后一个叶子节点
        for(int i =(arr.length-1)/2;i>=0;i--){
            heapify(arr,arr.length-1,i);
        }
    }


    private void heapify(int[] a, int n, int i) {
        while (true){
            int max = i;
            if((i*2+1)<=n && a[i]<a[i*2+1])max = i*2+1;
            if((i*2+2)<=n && a[max]<a[i*2+2])max = i*2+2;
            if(max==i)break;
            swap(a,max,i);
            i = max;
        }
    }

    private void swap(int[] a, int max, int i) {
        int temp = a[max];
        a[max] = a[i];
        a[i] = temp;
    }


}
