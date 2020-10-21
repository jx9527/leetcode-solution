package com.company;

import sun.plugin2.ipc.windows.WindowsIPCFactory;

public class leet746 {
    //确定baseCase:
    //确定【状态--会变化的变量】 for item:状态集合 每个楼梯对应的体力花费
    //确定【选择--导致状态变化的行为】
    /**
     * 两种选择 当前节点
     */
    //确定dp函数/数组含义：dp[i]代表前i家获取的最高金额
    public int minCostClimbingStairs(int[] cost){
        if (cost==null||cost.length<=0) return 0;
        int len = cost.length;
        int[] dp = new int[len+1];
        //dp[i] i+1个台阶为楼顶时，所需要的最小体力
        dp[0] = 0;
        dp[1] = 0;
        for (int i=2;i<=len;i++){
            dp[i] = Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[len];
    }
    //状态压缩
    //当前状态只与前两个状态有关，所以只要两个哨兵就行
    public int minCostClimbStairs_1(int[] cost){
        if (cost==null||cost.length<=0) return 0;
        int len = cost.length;
        int dp_0 = 0;
        int dp_1 = 0;
        int min=0;
        for (int i=2;i<=len;i++){
            min = Math.min(dp_0+cost[i-2],dp_1+cost[i-1]);
            dp_0 = dp_1;
            dp_1 = min;
        }
        return dp_1;
    }
}
