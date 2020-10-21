package com.company;

public class leet1716 {

    //打架劫舍题
    public int message(int[] nums){
        if (nums==null||nums.length==0)
            return 0;
        int len = nums.length;
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i=2;i<=len;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[len];
    }

    //空间优化
    public int message_1(int[] nums){
        int a = 0,b = 0;
        for (int i=0;i<nums.length;i++){
            int c = Math.max(b, a+nums[i]);
            a = b;
            b = c;
        }
        return b;
    }

}