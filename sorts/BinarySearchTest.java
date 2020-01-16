package com.zl.geekdata.sorts;

/**
 * Create by zhanglong on 2019/11/28
 */
public class BinarySearchTest {

    public static void main(String[] args) {

    }

    //1.查找第一个值等于给定值
    private static int serachStart(int[] a, int n, int value) {
        int low = 0, heigh = n - 1;
        while (low <= heigh) {
            int mid = low + ((heigh - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                heigh = mid - 1;
            } else {
                if (mid == 0 || a[mid - 1] != value)
                    return mid;
                heigh = heigh - 1;
            }
        }
        return -1;
    }

    //2.查找最后一个值等于给定值
    private static int searchEnd(int[] a, int n, int value) {
        int low = 0, heigh = n - 1;
        while (low <= heigh) {
            int mid = low + ((heigh - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                heigh = mid - 1;
            } else {
                if (mid == n - 1 || a[mid + 1] != value)
                    return mid;
                low = mid + 1;
            }
        }
        return -1;
    }

    //3.查找第一个大于等于给定值
    private static int serach1Big(int[] a, int n, int value) {
        int low = 0, heigh = n - 1;
        while (low <= heigh) {
            int mid = low + ((heigh - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] < value)
                    return mid;
                heigh = mid - 1;
            }
        }
        return -1;
    }

    //4.查找最后一个小于等于给定值
    private static int search1Small(int[] a, int n, int value) {
        int low = 0, heigh = n - 1;
        while (low <= heigh) {
            int mid = low + ((heigh - low) >> 1);
            if (a[mid] > value) {
                heigh = mid - 1;
            } else {
                if (mid == n - 1 || a[mid + 1] > value)
                    return mid;
                low = mid + 1;
            }
        }
        return -1;
    }


    //4,5,6,1,2,3,返回索引
    private int search(int[] nums, int target) {
        int low = 0, heigh = nums.length - 1;
        while (low <= heigh) {
            int mid = low + ((heigh - low) >> 1);
            if (nums[mid] == target)
                return mid;
            if (nums[low] <= nums[mid]) {
                if (nums[mid] > target && nums[low] <= target) {
                    heigh = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[heigh] >= target) {
                    low = mid + 1;
                } else {
                    heigh = mid - 1;
                }
            }
        }

        return -1;
    }
}
