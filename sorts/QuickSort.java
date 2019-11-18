package com.zl.geekdata.sorts;

public class QuickSort {

    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r)
            return;
        int q = patition(a, p, r);
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    private static int patition(int[] a, int p, int r) {
        //分区点选择最后一位
        int pivot = a[r];
        //辅助指针i,i之前的数小于分区点
        int i = p;
        for (int j = p; j < r; j++) {
            if (a[j] <= pivot) {
                if (i == j) {
                    i++;
                } else {
                    //辅助指针与j的值互换
                    int temp = a[i];
                    a[i++] = a[j];
                    a[j] = temp;
                }
            }
        }
        //i与pivot互换值
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
        return i;
    }

}
