package com.company.everyday;

import java.util.Map;

public class lcp19 {


    // 动态规划
    // 定义状态 dp[i][j] 表示第0片到第i片叶子进行调整操作，并且第i片叶子处于状态j时的最小操作数。
    // 【红 黄 红】 用三个状态分别表示其中的每一个部分，j=0表示【红...】 j=1表示【...黄...】 j=2表示【...红】
    // 选择：j=0时，将第i个叶子变成红色的，并且第i-1片叶子只能是红色 dp[i][0]=dp[i-1][0] + isY(i)
    //      j=1时，将第i个叶子变成黄色的，而第i-1片叶子既可以是黄色的，也可以是红色的（之前的叶子都是红色的）
    //        dp[i][1] = min{dp[i-1][0],dp[i-1][1]} + isR(i);
    //     j=2时，将第i个叶子变成红色的, (i-1黄色，i-2 红色/黄色)<==>i-1是j=1状态
    //                                  (i-1红色, i-2 红色/黄色)<==>i-1是j=2状态

    //BaseCase::i=0时,dp[0][0] = isY(0)

    public int minis(String leaves){
        int n = leaves.length();
        //分三个状态数量（红，红黄，红黄红）
        int[][] f = new int[n][3];
        //第一片叶必须是红色
        f[0][0] = leaves.charAt(0) == 'y'?1:0;
        //遵循叶子的数量必须大于等于状态的数量
        f[0][1] = f[0][2] = f[1][2] = Integer.MAX_VALUE;
        for (int i=1;i<n;i++){
            int isRed = leaves.charAt(i) == 'r' ?1:0;
            int isYellow = leaves.charAt(i)=='y'?1:0;
            //全部替换成红叶的最小次数
            f[i][0] = f[i-1][0] + isYellow;
            //替换成红黄排列最小次数
            f[i][1] = Math.min(f[i-1][0],f[i-1][1]) + isRed;
            if (i>=2){
                //替换成红黄红排列的最小次数
                f[i][2] = Math.min(f[i-1][1],f[i-1][2]) + isYellow;
            }
        }
        return f[n-1][2];
    }

    //滚动优化
    public int minums(String leaves){
        int start,middle,end;
        int n = leaves.length();
        start = leaves.charAt(0) == 'y'?1:0;
        middle = Integer.MAX_VALUE;
        end = Integer.MAX_VALUE;
        for (int i=1;i<n;i++){
            int isRed = leaves.charAt(i)=='r'?1:0;
            int isYellow = leaves.charAt(i)=='y'?1:0;
            int temp_a = Math.min(middle,start);
            int temp_b = Math.min(middle,end);
            start = start+isYellow;
            middle = temp_a+isRed;
            if (i>=2){
                end = temp_b+isYellow;
            }
        }
        return end;
    }

    public static void main(String[] args){
        String s = "ryyryyyrryyyyyryyyrrryyyryryyyyryyrrryryyyryrryrrrryyyrrrrryryyrrrrryyyryyryrryryyryyyyryyrryrryryy";
        int r = 0;
        int y = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='r'){
                r++;
            }
            if (s.charAt(i)=='y'){
                y++;
            }
        }
        System.out.println(r);
        System.out.println(y);
    }
}
