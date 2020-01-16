package com.zl.geekdata.bf_rk;

/**
 * Create by zhanglong on 2020/1/3
 */
public class BfRk {

    //Broute force
    public int bF(String target, String model) {
        int n = target.length(), m = model.length();
        if (n < m)
            return -1;
        char[] tArr = target.toCharArray();
        char[] mArr = model.toCharArray();
        for (int i = 0; i < n - m; i++) {
            int k = 0;
            for (int j = 0; j < m; j++) {
                if (tArr[i + j] == mArr[j]) {
                    k++;
                } else {
                    break;
                }
            }
            if (k == m) {
                return i;
            }
        }
        return -1;
    }

    //rk
    public int rK(String target, String model) {
        int n = target.length(), m = model.length(), s,i;
        int[] hash = new int[n-m+1];
        int[] table = new int[26];
        char[] tArr = target.toCharArray();
        char[] mArr = model.toCharArray();
        s=1;
        for(i=0;i<26;i++){
            table[i] = s;
            s *= 26;
        }
        for(i=0;i<n-m;i++){
            s=0;
            for(int j=0;j<m;j++){
                s+=(tArr[i+j]-'a')*table[n-1-i];
            }
            hash[i] = s;
        }
        s=0;
        for(int j=0;j<m;j++){
            s+=(tArr[i+j]-'a')*table[n-1-i];
        }

        for(i=0;i<n-m;i++){
            if(hash[i]==s){
                return i;
            }
        }
        return -1;
    }


}
