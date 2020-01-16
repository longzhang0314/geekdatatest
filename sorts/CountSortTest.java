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
        if(n<=1)return;
        //找到最大值，默认正数
        int max = a[0];
        for(int i=1;i<n;i++){
            if(a[i]>max){
                max = a[i];
            }
        }
        int[] c = new int[max+1];
        //先统计每个值各有几个
        for(int i=0;i<n;i++){
            c[a[i]]++;
        }
        //在统计小于等于的值有几个
        for(int i=1;i<=max;i++){
            c[i] = c[i-1]+c[i];
        }
        //有序临时数组
        int[] r = new int[n];
        //从a中拿数据去c中查找索引，在r中赋值
        for(int i=n-1;i>=0;i--){
            int index = --c[a[i]];
            r[index] = a[i];
        }
        //赋值回a
        for(int i=0;i<n;i++){
            a[i] = r[i];
        }

    }
}
