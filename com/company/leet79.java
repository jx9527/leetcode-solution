package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//回溯算法: DFS+状态重置
public class leet79 {
    private boolean[][] marked;
    private int[][] direction = {{-1,0},{0,-1},{1,0},{0,1}};
    private int length;
    private int width;
    private String word;
    private char[][] board;
    public boolean exist(char[][] board,String word){
        length = board.length;
        if (length==0) return false;
        width = board[0].length;
        marked = new boolean[length][width];
        this.word = word;
        this.board = board;
        for (int i=0;i<length;i++){
            for (int j=0;j<width;j++){
                if (dfs(i,j,0)) return true;
            }
        }
        return false;
    }
    private boolean dfs(int i,int j,int start){
        if (start == word.length() - 1){
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j]==word.charAt(start)){
            marked[i][j] = true;
            for (int k=0;k<4;k++){
                int x_new = i+direction[k][0];
                int y_new = j+direction[k][1];
                if (inArea(x_new,y_new) && !marked[x_new][y_new]){
                    if (dfs(x_new,y_new,start+1)) return true;
                }
            }
            marked[i][j] = false;
        }
        return false;
    }
    private boolean inArea(int x,int y){
        return x>=0 && x<length && y>=0 && y<width;
    }


    //自己的错误写法
    boolean exist = false;
    public boolean exist_1(char[][] board, String word){
        if (word==null||word.length()==0) return false;
        List<Integer> record_x = new LinkedList<>();
        List<Integer> record_y = new LinkedList<>();
        int[] direX = {1,-1,0,0};
        int[] direY = {0,0,1,-1};
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                if (board[i][j] == word.indexOf(0)){
                    record_x.add(i);
                    record_y.add(j);
                }
            }
        }
        if (record_x.isEmpty()) return false;
        boolean[][] visited  = new boolean[board.length][board[0].length];
        for (int i=0;i<record_x.size();i++){
            String wordC = word;
            int x = record_x.get(i);
            int y = record_y.get(i);
            visited[x][y] = true;
            dfs_1(x,y,visited,board,wordC,direX,direY,0);
            visited[x][y] = false;
        }
        return exist;
    }

    public void dfs_1(int x, int y, boolean[][] visited,char[][] board,String word,int[] direX, int[] direY,int index){
        if (word.length()==1){
            exist = board[x][y] == word.charAt(0);
            return;
        }
        else {
            //枚举方向
            for (int i=0;i<direX.length;i++){
                //超过边界
                int x_new = direX[i]+x;
                int y_new = direY[i]+y;
                if (x_new>=board.length || x_new<0) continue;
                if (y_new>=board[0].length || y_new<0) continue;
                if(visited[x_new][y_new]) continue;
                if (board[x_new][y_new] == word.charAt(index+1)){
                    visited[x_new][y_new] = true;
                    dfs_1(x_new,y_new,visited,board,word.substring(index+1),direX,direY,index+1);
                    visited[x_new][y_new] = false;
                }
            }
        }
    }

}
