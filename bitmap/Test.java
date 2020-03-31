package com.zl.geekdata.bitmap;

/**
 * Create by zhanglong on 2020/3/31
 */
public class Test {

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(10000);
        bitMap.set(20);
        bitMap.set(18);
        System.out.println(bitMap.get(20));
        System.out.println(bitMap.get(18));
        System.out.println(bitMap.get(17));
    }
}


