package com.zl.geekdata.cyc2018.doublepoint;

/**
 * 可以删除一个字符，判断是否能构成回文字符串。
 * Create by zhanglong on 2020/4/16
 */
public class ValidPalindromeII_680 {

    public boolean validPalindrome(String s) {
        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) return valid(s, left, right - 1) || valid(s, left + 1, right);
        }
        return true;
    }

    private boolean valid(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }


}
