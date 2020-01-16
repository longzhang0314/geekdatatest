package com.zl.geekdata.backtracking;

/**
 * Create by zhanglong on 2020/1/10
 */
public class Package0_1Test {
    int maxW = Integer.MIN_VALUE;

    public void f(int i,int cw,int[] items,int n,int w){
        if(i==n || cw == w){
            if(cw > maxW){
                maxW = cw;
            }
            return;
        }
        f(i+1,cw,items,n,w);
        if(cw+items[i]<=w){
            f(i+1,cw+items[i],items,n,w);
        }
    }
}
