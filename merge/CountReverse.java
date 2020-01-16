package com.zl.geekdata.merge;

/**
 * Create by zhanglong on 2020/1/9
 */
public class CountReverse {

    private int num;

    //逆序度计数
    public int count(int[] a) {
        int n = a.length;
        mergeAndSort(a, 0, n - 1);
        return num;
    }

    //归
    private void mergeAndSort(int[] a, int p, int r) {
        if (p >= r)
            return;
        int q = (p + r) / 2;
        mergeAndSort(a, p, q);
        mergeAndSort(a, q + 1, r);
        merge(a, p, q, r);
    }

    //合并
    private void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] temp = new int[r - p + 1];
        //有序放入temp并计数
        while (i<=q && j<=r){
            if(a[i]<=a[j]){
                temp[k++] = a[i++];
            }else{
                //有q-i+1个元素比当前j大
                num += (q-i+1);
                temp[k++] = a[j++];
            }
        }
        //将剩余元素放入temp
        while (i<=p){
            temp[k++] = a[i++];
        }
        while (j<=r){
            temp[k++] = a[j++];
        }
        //temp放回a
        for(i=0;i<r-p+1;i++){
            a[p+i] = temp[i];
        }
    }
}
