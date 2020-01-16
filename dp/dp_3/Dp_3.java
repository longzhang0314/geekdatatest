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
     * 后一种稳定态由前一种稳定态的而来，i-1,j和i,j-1状态+1必得稳定态，i-1,j-1状态不动或+1都可能稳定，如果不稳定，再+1
     * a[i] = b[j] --> S[i][j] = min(S[i-1][j]+1,S[i][j-1]+1,S[i-1][j-1]) //此处省略S[i-1][j-1]+1
     * a[i] != b[j]--> S[i][j] = min(S[i-1][j]+1,S[i][j-1]+1,S[i-1][j-1]+1) //次数省略S[i-1][j-1] + 1 +1
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

    //添加删除必得稳定态
    //a[i]==b[j] --> maxDist[i][j] = max(maxDist[i-1][j-1]+1,maxDist[i-1][j],maxDist[i][j-1])
    //a[i]!=b[j] --> maxDist[i][j] = max(maxDist[i-1][j-1],maxDist[i-1][j],maxDist[i][j-1])


}
