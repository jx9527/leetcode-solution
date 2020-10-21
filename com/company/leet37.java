package com.company;


import sun.plugin2.ipc.windows.WindowsIPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * 题目类型：n皇后问题 + 回溯/状态压缩
 *
 *
 */
public class leet37 {
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean vaild = false;
    private List<int[]> spaces = new ArrayList<>();
    public void solveSudoku(char[][] board){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (board[i][j]=='.'){
                    spaces.add(new int[]{i,j});
                }else {
                    int digit = board[i][j]-'0'-1;
                    line[i][digit] = column[j][digit] = block[i/3][j/3][digit] = true;
                }
            }
        }
        dfs(board,0);
    }
    public void dfs(char[][] board, int pos){
        if (pos==spaces.size()){
            vaild = true;  //这里的vaild 举个例子：spaces.size()==10，dfs(board,10) 刚好return  line[x][digit] = ... = false;
                           //退到dfs(board,9)时，如果刚好遍历到line[x][digit]==false，那么就会把之前的正确结果覆盖，出现和dfs(10)相同的数字
                          //产生重复情况，
            return;
        }
        int[] space = spaces.get(pos);
        int x = space[0],y= space[1];
        for (int digit=0; digit<9 && !vaild; ++digit){
            if (!line[x][digit] && !column[y][digit] && !block[x/3][y/3][digit]){
                line[x][digit] = column[y][digit] = block[x/3][y/3][digit] = true;
                board[x][y] = (char)(digit+'0'+1);
                dfs(board,pos+1);
                line[x][digit] = column[y][digit] = block[x/3][y/3][digit] = false;
            }
        }
    }

    //位运算优化
    /**
     * 借助位运算，仅使用一个整数表示每个数字是否出现过
     * 当b二进制表示为(011000100)2时，表示数字3,7,8已经出现
     * (1)【枚举状态】将b按位取反，那么所有位置都可以填入，通过寻找1来枚举状态，取反后数b的高位，需要将这个数和(111111111)2与，截取低位。
     * (2)【改变i位置的状态】按位与1<<0异或运算，1变为0，0变为1
     * (3)【保留最低位的1】 b&(-b)==b&(~b+1)
     * (4)【去除最低为的1】这样就可以枚举下一个1，用b和最低位的1进行异或运算或者b&(b-1)
    */

    private int[] line1 = new int[9];
    private int[] column1 = new int[9];
    private int[][] block1 = new int[3][3];
    private boolean valid1 = false;
    private List<int[]> spaces1 = new ArrayList<>();

    public void solveSudoku_1(char[][] board){
        for (int i=0;i<9;i++){
            for (int j=0;j<0;j++){
                if(board[i][j]=='.'){
                    spaces.add(new int[]{i,j});
                }else {
                    int digit = board[i][j]-'0'-1;
                    flip(i,j,digit);
                }
            }
        }
        dfs_1(board,0);
    }
    //初始化：0^1 = 1
    //状态翻转： 1^1 = 0 0^1=1
    public void flip(int i,int j,int digit){
        line1[i] ^= (1<<digit);
        column1[j] ^= (1<<digit);
        block1[i/3][j/3] ^= (1<<digit);
    }
    public void dfs_1(char[][] board,int pos){
        if (pos==spaces1.size()){
            valid1 = true;
            return;
        }
        int[] space = spaces1.get(pos);
        int i=space[0],j=space[1];
        //得到可以遍历的状态
        int mask = ~(line1[i]|column1[j]|block1[i/3][j/3]) & 0x1ff;
        for (;mask!=0&&!valid1;mask=mask & (mask-1)){
            int digitMask = mask&(-mask);//拿到最低位的1
            int digit = Integer.bitCount(digitMask-1);
            flip(i,j,digit);
            board[i][j] = (char)(digit+'0'+1);
            dfs(board,pos+1);
            flip(i,j,digit);
        }
    }

}
