package com.zl.geekdata.sorts;

public class Sorts {

    //bubble origin
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1)
            return;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    flag = true;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    //bubble update
    public static void bubbleSort2(int[] a, int n) {
        if (n <= 1)
            return;
        int lastExchange = 0;
        int sortBorder = n - 1;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if (!flag) {
                break;
            }
        }
    }

    //insert
    public static void insertSort(int[] a, int n) {
        if (n <= 1)
            return;
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;//break时j此时的后一位需要插入
                }
            }
            a[j + 1] = value;
        }
    }


    //select
    public static void selectSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < min) {
                    min = j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }


    //shell
    public static void shellSort(int[] a, int n) {
        if (n <= 1)
            return;
        //分组
        int step = n / 2;
        while (step >= 1) {

            for (int i = step; i < n; i++) {
                int value = a[i];
                //从i-step位置开始进行该组
                int j = i - step;
                for (; j >= 0; j -= step) {
                    if (value < a[j]) {
                        //向后移动step位
                        a[j + step] = a[j];
                    } else {
                        break;//break出来时j的后一位(step)需要插入value
                    }
                }
                a[j + step] = value;
            }
            //再次细分，直到分为1个组
            step /= 2;
        }

    }

}
