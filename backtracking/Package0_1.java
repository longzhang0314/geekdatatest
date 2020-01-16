package com.zl.geekdata.backtracking;

/**
 * Create by zhanglong on 2020/1/10
 */
public class Package0_1 {
    public static void main(String[] args) {
        int i=0;
        int cw = 0;
        int[] items = {1,3,2,5,4,1};
        int n = 6;
        int w = 10;
        f(i,cw,items,n,w);
    }

    static int maxW = Integer.MIN_VALUE;
    public static void f(int i,int cw,int[] items,int n,int w){
        if(i==n || cw==w){//装满就不装了
            if(cw>maxW){
                maxW = cw;//更新当前背包重量
            }
            return;
        }
        f(i+1,cw,items,n,w);//先选择不装的
        if(cw+items[i]<=w){
            f(i+1,cw+items[i],items,n,w);//再选择装的
        }
    }
}
