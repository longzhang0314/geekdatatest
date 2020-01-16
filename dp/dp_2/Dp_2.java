package com.zl.geekdata.dp.dp_2;

/**
 * Create by zhanglong on 2020/1/14
 */
public class Dp_2 {

    //--------------------------------------棋盘最短路径---------------------------
    int minDist = Integer.MAX_VALUE;

    //1.回溯
    public void minDistBackTracing(int[][] matrix, int n, int dist, int i, int j) {
        if (i == n && j == n) {
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        if (i < n) {
            minDistBackTracing(matrix, n, dist + matrix[i][j], i + 1, j);
        }
        if (j < n) {
            minDistBackTracing(matrix, n, dist + matrix[i][j], i, j + 1);
        }
    }

    //2.DP递推
    public int minDistDp(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        //第一行状态特殊处理
        for (int j = 0; j < n; j++) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        //第一列特殊处理
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        //其他处理
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                states[i][j] = matrix[i][j] + Math.min(states[i - 1][j], states[i][j - 1]);
            }
        }
        return states[n - 1][n - 1];
    }

    //3.DP递归加memo
    //备忘录中记录已达的状态及状态值
    int[][] memo = new int[4][4];

    public int chessMemo(int[][] matrix, int n, int i, int j) { //i=n-1,j=n-1
        //递归出口
        if (i == 0 && j == 0)
            return matrix[0][0];
        if (memo[i][j] > 0)
            return memo[i][j];
        //当前状态左，上
        int minLeft = Integer.MAX_VALUE, minUp = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = chessMemo(matrix, n, i, j - 1);
        }
        if (i - 1 >= 0) {
            minUp = chessMemo(matrix, n, i - 1, j);
        }
        int minDist = matrix[i][j] + Math.min(minLeft, minUp);
        memo[i][j] = minDist;
        return minDist;
    }

    //----------------------------------------硬币找零-------------------------------------
    /*
     * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。
     * 如果我们要支付 w 元，求最少需要多少个硬币。
     * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
     */
    //f(w) = min(f(w-1),f(w-3),f(w-5)) + 1
    //1.递归回溯
    public int f(int w) {
        if (w == 1)
            return 1;
        if (w == 3)
            return 1;
        if (w == 5)
            return 1;
        if (w == 2)
            return 2;
        if (w == 4)
            return 2;
        int min1 = Math.min(f(w - 1), f(w - 3));
        return Math.min(min1, f(w - 5)) + 1;
    }
    // 2.状态转移表
    public int f1(int money){
        if(money==1 || money==3 || money==5)return 1;
        //横坐标个数，纵坐标金额
        boolean[][] states = new boolean[money][money+1];
        if(money>=1)states[0][1] = true;
        if(money>=3)states[0][3] = true;
        if(money>=5)states[0][5] = true;
        for(int i=1;i<money;i++){
            for(int j=1;j<money;j++){
                if(states[i-1][j]){
                    //一次三个状态
                    if(j+1<=money)states[i][j+1] = true;
                    if(j+3<=money)states[i][j+3] = true;
                    if(j+5<=money)states[i][j+5] = true;
                    if(states[i][money])return i+1;
                }
            }
        }
        return money;
    }

    //3.状态转移方程
    public int f2(int money){
        int[] states = new int[money+1];
        states[1] = 1;
        states[2] = 2;
        states[3] = 1;
        states[4] = 2;
        states[4] = 1;
        for(int i=6;i<=money;i++){
            states[i] = 1+ Math.min(states[i-1],Math.min(states[i-3],states[i-5]));
        }
        return states[money];
    }

}
