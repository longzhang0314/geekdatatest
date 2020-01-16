package com.zl.geekdata.dp.dp_3;

/**
 * Create by zhanglong on 2020/1/15
 */
public class Dp_3 {


    //-----------------------------------------字符串最小编辑距离(莱文斯坦距离)-------------------------------
    /*
     *1 minDist[i][j]表示处理完a[i]和b[j]时（a[0...i]已全部转换到b[0...j]），需要的最小编辑次数
     *2 a[i]和b[j]不相等时，状态转移公式为：minDist[i][j]=min(minDist(i-1,j)+1, minDist(i,j-1)+1，minDist(i-1,j-1))
     *  1) 如果minDist[i][j]=minDist(i-1,j)+1，现分析一下这个状态转移的具体过程。minDist(i-1,j)表示a[0...i-1]已全部转换到b[0...j]，如何编辑字符才能从minDist(i-1,j)到达minDist[i][j]这个状态呢？要么将a[i]这个字符删除，要么在b[j]后边添加一个跟a[i]相同的字符（这里编辑的操作跟老师讲的回溯法的操作是不一样的）
     *  2）如果minDist[i][j]=minDist(i-1,j-1)，现分析一下这个状态转移的具体过程。如何编辑字符才能从minDist(i-1,j-1)到达minDist[i][j]这个状态呢？？将a[i]替换为b[j]或者将b[j]替换为a[i]即可
     *  3) 如果minDist[i][j]=minDist(i,j-1)+1，跟上边第一种情况类似
     *3 a[i]和b[j]相等时比较简单，不需要做字符的编辑
     */
    //添加，删除，修改到匹配三种方式
    char[] a = "asaaa".toCharArray();
    char[] b = "asbsa".toCharArray();
    int n = a.length;
    int m = b.length;
    int minDist = Integer.MAX_VALUE;

    //1.回溯
    public void fMinEdist(int i, int j, int edist) {
        //递归终止条件
        if (i == n || j == m) {
            if (i < n)
                edist += (n - i);
            else if (j < m)
                edist += (m - j);
            if (edist < minDist) {
                minDist = edist;
            }
        }
        //匹配上了
        if (a[i] == b[j]) {
            fMinEdist(i + 1, j + 1, edist);
        } else {//不匹配
            fMinEdist(i + 1, j, edist + 1);//删除a[i]的，b[j]前面添加一个
            fMinEdist(i, j + 1, edist + 1);//删除b[j]的，a[i]前面添加一个
            fMinEdist(i + 1, j + 1, edist + 1);//a[i]和b[j]替换为相同字符
        }
    }

    //2.dp
    /*
     * 后一种稳定态由前一种稳定态的而来，i-1,j和i,j-1状态到达i,j编辑次数必+1,i-1,j-1到达i,j分情况
     * a[i] = b[j] --> S[i][j] = min(S[i-1][j]+1,S[i][j-1]+1,S[i-1][j-1]) //
     * a[i] != b[j]--> S[i][j] = min(S[i-1][j]+1,S[i][j-1]+1,S[i-1][j-1]+1) //
     *
     */
    public int fMinEdistDp(char[] a, int n, char[] b, int m) {
        //value表示到达当前稳定态(i,j)时的最小dist
        int[][] minDist = new int[n][m];
        //第一行特殊处理
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) { //如果相等
                //第一行只可能有S[i][j-1]+1
                //minDist[0][0] = 0;
                minDist[0][j] = j;
            } else if (j != 0) { //不相等且j != 0
                minDist[0][j] = minDist[0][j - 1] + 1;
            } else { //不相等且j == 0
                minDist[0][j] = 1;
            }
        }
        //第一列特殊处理
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                //s[i][j] = s[i-1][j]+1
                //s[0][0] = 0
                minDist[i][0] = i;
            } else if (i != 0) { //不是第一行且 a[i] != b[j]
                minDist[i][0] = minDist[i - 1][0] + 1;
            } else {
                minDist[i][0] = 1;
            }
        }
        //dp
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                } else {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
                }
            }
        }

        return minDist[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int min = Integer.MAX_VALUE;
        if (x < min)
            min = x;
        if (y < min)
            min = y;
        if (z < min)
            min = z;
        return min;
    }


    //--------------------------------------------------最长公共子串长度--------------------------------------------
    //添加，删除两种方式

    //(i-1,j)和(i,j-1)通过删除，添加得到稳定态，长度不能+1，i-1,j-1到达i,j，如果a[i]==b[j]长度+1
    //a[i]==b[j] --> maxDist[i][j] = max(maxDist[i-1][j-1]+1,maxDist[i-1][j],maxDist[i][j-1])
    //a[i]!=b[j] --> maxDist[i][j] = max(maxDist[i-1][j-1],maxDist[i-1][j],maxDist[i][j-1])
    public int maxDistDp(char[] a, int n, char[] b, int m) {
        int[][] maxDist = new int[n][m];
        //第一行特殊处理
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) {
                maxDist[0][j] = 1;//不能重复增加公共子串长度
            } else if (j != 0) {//a[0] != b[j]且j不在第一行
                maxDist[0][j] = maxDist[0][j - 1];
            } else {
                maxDist[0][j] = 0;
            }
        }
        //第一列特殊处理
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                maxDist[i][0] = 1;
            } else if (i != 0) {
                maxDist[i][0] = maxDist[i - 1][0];
            } else {
                maxDist[i][0] = 0;
            }
        }
        //dp
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    maxDist[i][j] = max(maxDist[i - 1][j], maxDist[i][j - 1], maxDist[i - 1][j - 1] + 1);
                } else {
                    maxDist[i][j] = max(maxDist[i - 1][j], maxDist[i][j - 1], maxDist[i - 1][j - 1]);
                }
            }
        }

        return maxDist[n - 1][m - 1];

    }

    private int max(int x, int y, int z) {
        int max = Integer.MIN_VALUE;
        if (x > max)
            max = x;
        if (y > max)
            max = y;
        if (z > max)
            max = z;
        return max;
    }


    //---------------------------------------------------------思考--------------------------
    /*
    我们有一个数字序列包含 n 个不同的数字，
    如何求出这个序列中的最长递增子序列长度？
    比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
     */
    //s[i] = a[i]之前所有比它小的元素的最长子序列长度+1
    public int longestIncreaseSubArrayDP(int[] array) {
        if (array.length < 2) return array.length;
        int[] state = new int[array.length];
        state[0] = 1;
        for (int i = 1; i < state.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    if (state[j] > max) max = state[j];
                }
            }
            state[i] = max + 1;
        }
        int result = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] > result) result = state[i];
        }
        return result;
    }

    //s[i] = a[i]之前所有比它小的元素的最长子序列长度+1
    public int longestIncreaseSubArrDP2(int[] arr,int n){
        if(n<2)return n;
        int[] states = new int[n];
        states[0] =1;
        for(int i=1;i<states.length;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i]){
                    if(states[j]>max)max = states[j];
                }
            }
            states[i] = max+1;
        }
        int result = 0;
        for(int i=0;i<states.length;i++){
            if(states[i]>result)result = states[i];
        }
        return result;
    }

}
