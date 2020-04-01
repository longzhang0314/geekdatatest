package com.zl.geekdata.dp.dp_1;

/**
 * Create by zhanglong on 2020/1/13
 */
public class Dp_1 {
    private int maxW = Integer.MIN_VALUE;
    private int w = 100;
    private int n = 5;
    private int[] items = {23, 1, 53, 44, 25};
    private boolean[][] men = new boolean[n][w + 1];
    //价值
    private int[] values = {12, 43, 2, 12, 54};
    private int maxV = Integer.MIN_VALUE;

    //------------------------------------------------------0-1背包----------------------------------------

    //回溯
    public void f(int i, int cw) {
        if (i == n || cw == w) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        f(i + 1, cw);
        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i] + w);
        }
    }

    //备忘录
    public void f1(int i, int cw) {
        if (i == n || cw == w) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        //查询备忘录中是否有
        if (men[i][cw])
            return;
        men[i][cw] = true;
        f(i + 1, cw);
        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i] + w);
        }
    }

    //dp1  int:最终最大重量
    public int knapsack() {
        boolean[][] status = new boolean[n][w + 1];
        status[0][0] = true;
        if (items[0] <= w) {
            status[0][items[0]] = true;
        }
        for (int i = 0; i < n; i++) {
            //第i个不放
            for (int j = 0; j <= w; j++) {
                if (status[i - 1][j]) {
                    status[i][j] = true;
                }
            }
            //第i个放
            for (int j = 0; j <= w - items[i]; j++) {
                if (status[i - 1][j]) {
                    status[i][j + items[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; i--) {
            if (status[n - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    //int 最终最大重量
    public int knapsack2() {
        boolean[] status = new boolean[w + 1];
        status[0] = true;
        if (items[0] <= w) {
            status[items[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) { //防止受到同一层干扰
                if (status[j]) {
                    status[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (status[i]) {
                return i;
            }
        }
        return 0;
    }

    //-----------------------------------------------改造问题，条件为背包价值最大--------------------------
    //回溯
    public void fv(int i, int cw, int cv) {
        if (i == n || cw == w) {
            if (cv > maxV) {
                maxV = cv;
            }
            return;
        }
        fv(i + 1, cw, cv);
        if (cw + items[i] <= w) {
            fv(i + 1, cw + items[i], cv + values[i]);
        }
    }

    //dp1
    public int knapsackV() {
        int[][] status = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                status[i][j] = -1;
            }
        }
        status[0][0] = 0;
        if (items[0] <= w) {
            status[0][items[0]] = values[0];
        }
        //状态转移
        for (int i = 1; i < n; i++) {
            //不放入
            for (int j = 0; j <= w; j++) {
                if (status[i - 1][j] >= 0) {
                    status[i][j] = status[i - 1][j];
                }
            }
            //放入
            for (int j = 0; j <= w - items[i]; j++) {
                if (status[i - 1][j] >= 0) {
                    int curVal = status[i - 1][j] + values[i];
                    //如果当前价值大于同一重量时的价值就替换
                    if (curVal > status[i][j + items[i]]) { //status[i][j+items[i]可能在j遍历时重复，只保存最大值
                        status[i][j + items[i]] = curVal;
                    }
                }
            }
        }
        //获取最大价值
        int maxValue = -1;
        for (int i = 0; i <= w; i++) {
            if (status[n - 1][i] > maxValue) {
                maxValue = status[n - 1][i];
            }
        }
        return maxValue;
    }

    //dp2
    public int knapsackV2() {
        //索引为重量，值为最大价值
        int[] status = new int[w + 1];
        for (int i = 0; i < w + 1; i++) {
            status[i] = -1;
        }
        status[0] = 0;
        if (items[0] <= w) {
            status[items[0]] = values[0];
        }
        for (int i = 0; i < n; i++) {
            //放入
            for (int j = w - items[i]; j >= 0; j--) { //防止影响同一层对上一层是否决策的判定
                if (status[j] >= 0) { //上一层已经决策过
                    int curV = status[j] + values[i];//计算值
                    if (curV > status[j + items[i]]) { //计算值>本层查找值
                        status[j + items[i]] = curV;
                    }
                }
            }
        }
        int maxValue = -1;
        for (int j = 0; j <= w; j++) {
            if (status[j] > maxValue) {
                maxValue = status[j];
            }
        }
        return maxValue;
    }

    //----------------------------------------------------薅羊毛问题-----------------------------------------

    /*
     *  n 商品数量
     *  w 满减条件
     *  items 商品价格数组
     */
    public void double11Advance() {
        //超过2倍就没有价值了
        boolean[][] status = new boolean[n][w * 2 + 1];
        status[0][0] = true;
        if (items[0] <= w * 2) {
            status[0][items[0]] = true;
        }
        //dp
        for (int i = 1; i < n; i++) {
            //不选该商品
            for (int j = 0; j <= w * 2; j++) {
                if (status[i - 1][j]) {
                    status[i][j] = true;
                }
            }
            //选择该商品
            for (int j = 0; j <= w * 2 - items[i]; j++) {
                if (status[i - 1][j]) {
                    status[i][j + items[i]] = true;
                }
            }
        }
        //最终总价格
        int j;
        for (j = 0; j <= w * 2; j++) {
            if (status[n - 1][j])
                break;
        }
        if (j == w * 2 + 1)
            return;//没有可行解
        //打印出选择的商品
        /*
         * j已有解：
         * status[i-1][j]可达，说明没有选择第i个商品
         * status[i-1][j-items[i]]可达，说明选择了第i个商品
         */
        //通过最后一行状态倒序考察当前物品是否可以被选择
        //j已经确定，所以states[i][j]必可达时选择i
        for (int i = n - 1; i >= 1; --i) {  //如果当前物品增加后未超过总价值 && 上一行states[i-1][j-items[i]]可达，曾states[i][j]可达
            if (j - items[i] >= 0 && status[i - 1][j - items[i]]) { //选择第i个商品
                System.out.print(items[i] + " ");
                j = j - items[i];
            }
        }
        //钱没用完，选择第0个物品
        if (j != 0) {
            System.out.print(items[0]);
        }
    }


    //------------------------------------------------"杨辉三角"-----------------------------------------

    int minYH = Integer.MAX_VALUE;

    /**
     * 1.回溯法
     *
     * @param yanghui 杨辉三角数组
     * @param n       总行数
     * @param i       行数
     * @param j       列数
     * @param dist    最小路径和
     * @return
     */
    public void yanghui1(int[][] yanghui, int n, int i, int j, int dist) {
        dist += minYH;
        if (i == n - 1) {
            if (dist < minYH) {
                minYH = dist;
            }
            return;
        }
        //走正下方一列
        yanghui1(yanghui, n, i + 1, j, dist);
        //走正下方往后一列
        yanghui1(yanghui, n, i + 1, j + 1, dist);
    }

    /**
     * 2.DP
     * S[i][j] = Math.min(S[i-1][j-1],S[i-1][j]) + a[i][j]
     *
     * @param yanghui 杨辉三角数组
     * @param n       总行数
     * @return
     */
    public int yanghui2(int[][] yanghui, int n) {
        int[][] states = new int[n][n];
        states[0][0] = yanghui[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < yanghui[i].length; j++) {
                if (j == 0) {  //第一列特殊处理
                    states[i][j] = states[i - 1][j] + yanghui[i][j];
                } else if (j == yanghui[i].length - 1) { //最后一列特殊处理
                    states[i][j] = states[i - 1][j - 1] + yanghui[i][j];
                } else {
                    int top1 = states[i - 1][j - 1];
                    int top2 = states[i - 1][j];
                    states[i][j] = Math.min(top1, top2) + yanghui[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int s = states[n - 1][i];
            if (s < minYH) {
                minYH = s;
            }
        }
        return minYH;
    }


}
