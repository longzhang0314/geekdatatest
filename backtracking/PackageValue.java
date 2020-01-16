package com.zl.geekdata.backtracking;

/**
 * Create by zhanglong on 2020/1/10
 */
public class PackageValue {

    int maxV = Integer.MIN_VALUE;

    public void f(int i,int cw,int cv,int[] items,int[] vals,int n,int w){
        if(i== n || cw == w){
            if(cv>maxV){
                maxV =cv;
            }
        }
        f(i+1,cw,cv,items,vals,n,w);
        if(cw+items[i]<=w){
            f(i+1,cw+items[i],cv+vals[i],items,vals,n,w);
        }
    }
}
