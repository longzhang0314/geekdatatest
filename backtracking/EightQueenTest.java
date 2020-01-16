package com.zl.geekdata.backtracking;

/**
 * Create by zhanglong on 2020/1/10
 */
public class EightQueenTest {


    int[] result = new int[8];

    public void cal8Queens(int row){
        if(row==8){
            //找到一组就打印
            print8Queen();
            return;
        }
        //逐列放置
        for(int column=0;column<8;column++){
            if(isOk(row,column)){
                result[row] = column;
            }
        }
    }

    //当前行列是否可放置
    private boolean isOk(int row, int column) {
        int upLeftColumn = column-1,upRightColumn = column+1;
        for(int i=row-1;i>=0;i--){
            if(result[i]==column)return false;
            if(upLeftColumn>=0){
                if(result[i]==upLeftColumn)return false;
            }
            if(upRightColumn<8){
                if(result[i]==upRightColumn)return false;
            }
            upLeftColumn--;upRightColumn++;
        }
        return true;
    }

    private void print8Queen() {
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
