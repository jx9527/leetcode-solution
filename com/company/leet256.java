package com.company;

public class leet256 {
    //dp[i][j]表示第i个房子图颜色j最小花费总和，即从前一个房子状态dp[i-1][k](k!= j)中选择一个不同颜色
    // 且最小的再加上给第i栋房子涂颜色j的costs。

    //BaseCase dp[0][j] = cost[0][j]
    //dp[i][j] = min{dp[i-1][k] +costs[i][j]} (k != j)
    //答案为到最后一幢房子涂每种颜色花费中的最小值，即min(dp[n-1][k]),k=0,1,2
    public int minCost(int[][] costs){
        int n=costs.length;
        if (n==0) return 0;
        //dp[i][j]表示第i个房子涂颜色j的最小总和
        int[][] dp = new int[n][3];
        for (int i =0;i<3;i++){
            dp[0][i] = costs[0][i];
        }
        for (int i=1;i<n;i++){
            //第i时刻取j颜色 那么i-1就有两种可取  总共有3*3=9种组合
            for (int j=0;j<3;j++){
                dp[i][j]= Integer.MAX_VALUE;
                for (int k=0;k<3;k++){
                    if (k!=j){
                        dp[i][j] = Math.min(dp[i-1][k]+costs[i][j],dp[i][j]);
                    }
                }
            }
        }
        return Math.min(dp[n-1][0],Math.min(dp[n-1][1],dp[n-1][2]));
    }
}
