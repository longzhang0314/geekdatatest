package com.zl.geekdata.backtracking;

/**
 * Create by zhanglong on 2020/1/10
 */
public class EightQueen {

    public static void main(String[] args) {
        cal8Queens(0);
    }

    static int[] result = new int[8];

    public static void cal8Queens(int row) {
        if (row == 8) {
            printQueens();
            return;//此处return后回到row=7
        }
        for (int column = 0; column < 8; column++) {//回到row=7，但是column=上次递归时row=7时的column+1
            if (isOk(row, column)) {
                result[row] = column;
                cal8Queens(row+1);
            }
        }
    }

    //当前行，列放置是否合理
    private static boolean isOk(int row, int column) {
        int leftUpColumn = column - 1, rightUpColumn = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            //当前列是否合理
            if (result[i] == column)
                return false;
            //左上是否合理
            if (leftUpColumn >= 0) {
                if (result[i] == leftUpColumn)
                    return false;
            }
            //右上是否合理
            if (rightUpColumn < 8) {
                if (result[i] == rightUpColumn)
                    return false;
            }
            leftUpColumn--;
            rightUpColumn++;
        }
        return true;
    }

    private static void printQueens() {
        for(int row=0;row<8;row++){
            for(int column=0;column<8;column++){
                if(result[row]==column){
                    System.out.print("Q ");
                }else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
