package com.zl.geekdata.dp.dp_2;

/**
 * Create by zhanglong on 2020/1/14
 */
public class Dp_2Test {

    //棋盘最短路径
    int minDist = Integer.MAX_VALUE;
    public void chessBt(int[][] matrix,int n,int dist,int i,int j){
       if(i==n && j==n){
           if(dist<minDist){
               minDist = dist;
           }
           return;
       }
    }

    //S[i][j] = a[i][j] + min(S[i-1][j],S[i][j-1])
    public int chessDp(int[][] matrix,int n){
        int[][] states = new int[n][n];
        int sum = 0;
        //第一行特殊处理
        for(int j=0;j<n;j++){
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        //第一列特殊处理
        sum = 0;
        for(int i=0;i<n;i++){
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        //dp
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                states[i][j] = matrix[i][j] + Math.min(states[i-1][j],states[i][j-1]);
            }
        }
        return states[n-1][n-1];
    }

    //3.DP_memo 记录访问过的states以及状态
    int[][] memo = new int[4][4];
    public int chessMemo(int[][] matrix,int n,int i,int j){ //i=n-1,j=n-1调用
        //递归终止条件
        if(i==0 && j==0)return matrix[0][0];
        //备忘录查重
        if(memo[i][j] > 0)return memo[i][j];
        //写出递推公式
        int minLeft = Integer.MAX_VALUE,minUp = Integer.MAX_VALUE;
        //不在第一行，可以递归赋值minUp
        if(i>=1){
            minUp = chessMemo(matrix,n,i-1,j);
        }
        //不在第一列
        if(j>=1){
            minLeft = chessMemo(matrix,n,i,j-1);
        }
        //状态转移方程
        int states = matrix[i][j] + Math.min(minLeft,minUp);
        //记录备忘录
        memo[i][j] = states;
        return states;
    }

    /*
    * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。
    * 如果我们要支付 w 元，求最少需要多少个硬币。
    * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
    *
    * */
    //1.回溯
    public int f(int money){
        if(money==1)return 1;
        if(money==2)return 2;
        if(money==3)return 1;
        if(money==4)return 2;
        if(money==5)return 1;
        int minF1 = Math.min(f(money-1),f((money-3)));
        return Math.min(minF1,f(money-5));
    }

    //2.dp表
    public int f1(int money){
        if(money==1 || money==3 || money==5)return 1;
        //矩阵横坐标数量，纵坐标金额
        boolean[][] states = new boolean[money][money+1];
        if(money>=1)states[0][1] = true;
        if(money>=3)states[0][3] = true;
        if(money>=5)states[0][5] = true;
        for(int i=1;i<money;i++){
            for(int j=1;j<money;j++){
                if(states[i-1][j]){
                    if(j+1<=money)states[i][j+1] = true;
                    if(j+3<=money)states[i][j+3] = true;
                    if(j+5<=money)states[i][j+5] = true;
                    if(states[i][j])return i+1;
                }
            }
        }
        return money;
    }

    //3.dp转移方程
    public int f2(int money){
        int[] states = new int[money+1];
        states[1] = 1;
        states[2] = 2;
        states[3] = 1;
        states[4] = 2;
        states[1] = 1;
        for(int i=6;i<=money;i++){
            states[i] = 1+ Math.min(states[i-1],Math.min(states[i-3],states[i-5]));
        }
        return states[money];
    }


}
