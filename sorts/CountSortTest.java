package com.zl.geekdata.sorts;

import javax.sound.midi.Soundbank;

/**
 * Create by zhanglong on 2019/11/27
 */
public class CountSortTest {

    public static void main(String[] args) {
        int[] arr = {3,2,3,0,1,5,2,0};
        countSort(arr,8);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }


    public static void countSort(int[] a, int n) {
        if (n <= 1) return;
        int max = 0;
        //计算出最大值是多少，方便开辟计数数组的大小
        for (int i = 0; i <  n; i++) {
            max = Math.max(max, a[i]);
        }
        int[] c = new int[max + 1];
        //计数数组赋值
        for (int i = 0; i < n; i++) {
            c[a[i]]++;
        }
        //计数数组特殊处理，每个位置存储小于等于该值的所有元素
        for (int i = 1; i <= max; i++) {
            c[i] = c[i - 1] + c[i];
        }
        //临时数组存储排好序的元素
        int[] r = new int[n];
        //遍历原数组，寻找计数数组中的排序，并赋值到r
        for (int i = n - 1; i >= 0; i--) {
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--;
        }
        //r数组赋值回原数组
        for (int i = 0; i < n; i++) {
            a[i] = r[i];
        }

    }
}
