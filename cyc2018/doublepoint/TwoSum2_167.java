package com.zl.geekdata.cyc2018.doublepoint;

/**
 * 两数之和2-有序数组
 * Create by zhanglong on 2020/4/16
 */
public class TwoSum2_167 {

    public static void main(String[] args) {
        TwoSum2_167 t = new TwoSum2_167();
        int[] nums = {2,7,11,18};
        int tar = 9;
        int[] res = t.twoSum(nums, tar);
    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[]{-1, -1};
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target) left++;
            else if (sum > target) right--;
            else return new int[]{left + 1, right + 1};
        }
        return new int[]{-1, -1};
    }

}
