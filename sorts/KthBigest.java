package com.zl.geekdata.sorts;

//求无序数组第K大元素的值
public class KthBigest {

    public static void main(String[] args) {
        int[] arr = {22, 1, 4, 121, 4444, 0, -2, 3123};
        int k = 3;
        int kthBigest = getKthBigest(arr, k);
        System.out.println("第" + k + "大元素为：" + kthBigest);
    }

    public static int getKthBigest(int[] arr, int k) {
        if (arr == null || arr.length < k || k < 1)
            return -1;
        //获得分区点
        int patition = patition(arr, 0, arr.length - 1);

        //当前分区点不是第K大(k就是索引+1)
        while (patition + 1 != k) {
            if (patition + 1 < k) {//在分区点右侧
                patition = patition(arr, patition + 1, arr.length - 1);
            } else {
                patition = patition(arr, 0, patition - 1);
            }
        }
        return arr[patition];
    }

    //获得分区点
    private static int patition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        //末位分区点前所有元素
        for (int j = p; j < r; j++) {
            if (arr[j] >= pivot) {
                //i,j交换位置
                swap(arr, i, j);
                i++;
            }
        }
        //一次分区操作最后
        swap(arr, i, r);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
