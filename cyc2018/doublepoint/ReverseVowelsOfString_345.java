package com.zl.geekdata.cyc2018.doublepoint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 反转字符串中的元音字母
 * Create by zhanglong on 2020/4/16
 */
public class ReverseVowelsOfString_345 {

    public static void main(String[] args) {
        ReverseVowelsOfString_345 t = new ReverseVowelsOfString_345();
        String s = "aeihhhAEI";
        System.out.println(t.reverseVowels(s));
    }

    private static final Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) return s;
        int left = 0, right = s.length() - 1;
        char[] cc = s.toCharArray();
        while (left < right) {
            if (!set.contains(s.charAt(left))) left++;
            else if (!set.contains(s.charAt(right))) right--;
            else {
                char temp = cc[left];
                cc[left++] = cc[right];
                cc[right--] = temp;
            }
        }
        return new String(cc);
    }


}