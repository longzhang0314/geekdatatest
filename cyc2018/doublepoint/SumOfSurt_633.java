package com.zl.geekdata.cyc2018.doublepoint;

/**
 * 两数平方和
 * Create by zhanglong on 2020/4/16
 */
public class SumOfSurt_633 {

    public boolean judgeSquareSum(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum < c) i++;
            else if (sum > c) j--;
            else return true;
        }
        return false;
    }

}
