package com.zl.geekdata.cyc2018.doublepoint;

/**
 * 合并两个有序数组到nums1中
 * Create by zhanglong on 2020/4/16
 */
public class MergeTwoSortedArray_88 {

    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, cnt = m + n - 1;
        while (i >= 0 || j >= 0) {
            if (j < 0) break;
            if (i < 0 || nums2[j] > nums1[i]) nums1[cnt--] = nums2[j--];
            else nums1[cnt--] = nums1[i--];
        }
    }

}
