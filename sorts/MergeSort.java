package com.zl.geekdata.sorts;

public class MergeSort {

    public static void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n - 1);
    }

    private static void mergeSortInternally(int[] a, int p, int r) {
        if (p >= r)
            return;

        int q = p + (r - p) / 2;
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q + 1, r);
        merge(a, p, q, r);
    }

    //1.no sentinel
    //临时数组先存储，后移回
    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] temp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) {
            temp[k++] = a[start++];
        }

        for (i = 0; i < r - p + 1; i++) {
            a[p + i] = temp[i];
        }
    }


    //2.sentinel
    //分开存储到临时数组，直接放入目标数组
    private static void merge2(int[] a, int p, int q, int r) {
        int[] leftArr = new int[q - p + 2];
        int[] rightArr = new int[r - q + 1];
        for (int i = 0; i < p - q + 1; i++) {
            leftArr[i] = a[p + i];
        }
        leftArr[q - p + 1] = Integer.MAX_VALUE;
        for (int i = 0; i < r - q; i++) {
            rightArr[i] = a[q + 1 + i];
        }
        rightArr[r - q] = Integer.MAX_VALUE;
        //合
        int i = 0;
        int j = 0;
        int k = p;
        while (k <= r) {
            if (leftArr[i] <= rightArr[j]) {
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
            }
        }
    }

}
