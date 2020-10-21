package com.company;

import java.util.Map;

public class leet122 {
    //回溯 暴力得到所有可能的交易组合，并找出最大利润
    public int maxProfit(int[] prices) {
        return caculate(prices, 0);
    }

    public int caculate(int[] prices, int s) {
        if (s > prices.length) {
            return 0;
        }
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int max_profit = 0;
            for (int i = start + 1; i < prices.length; i++) {
//                if (prices[start] < prices[i]) {
                    int profit = caculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > max_profit) {
                        max_profit = profit;
//                    }
                }
            }
            if (max_profit > max) {
                max = max_profit;
            }
        }
        return max;
    }

    //贪心算法
    public int maxProfit_1(int[] prices) {
        int res = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }

    //动态规划
    //1. 定义状态,dp[i][j]  表示索引为i的那天所获得的最大利润
    //2. 定义选择,dp[i][j] j表示i那天是持有股票(j=1)，还是持有现金(j=0)
    //3. 确定起始, 第一天什么都不做，不买入股票,则获得利润为0 dp[0][0]=0
    //                  如果买入股票,则当前收益是负值,.dp[0][1]=-prices[i]
    //   确定终止，
    //          最后一天所得最大利润 dp[len-1][0]，因为一定有dp[len-1][0]>dp[len-1][1]
    public int maxProfit_3(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 0; i < len; i++) {
            //第i天持有现金 = 第i-1天不买入股票 or 第i-1天持有股票(已经买入股票了),第i天卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //第i天持有股票 = 第i-1天持有股票，不买入当天股票 or 第i-1天持有现金(已经把股票卖出去了),第i天买入股票,
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    //滚动变量
    public int maxProfit_4(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int cash = 0;
        int hold = -prices[0];
        int preCash = cash;
        int prehold = hold;
        for (int i = 1; i < len; i++) {
            cash = Math.max(preCash, prehold + prices[i]);
            hold = Math.max(prehold, preCash - prices[i]);
            preCash = cash;
            prehold = hold;
        }
        return cash;
    }

}


