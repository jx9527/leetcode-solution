package com.company;

public class leet0801 {
    private int[] dp = new int[100000];

    //暴力超时
    public int waysToStep(int n){
        if (n==0) return 0;
        if (n==1) return 1;
        if (n==2) return 2;
        if (n==3) return 4;
        return waysToStep(n-1)+waysToStep(n-2)+waysToStep(n-3);
    }

        private long[] memo;
        //备忘录递归法----栈溢出
        public int waysToStep_0(int n){
            memo = new long[n+1];
            for (int i = 0; i < n + 1; i++)
                memo[i] = -1;
           return (int)tryWays(n);
            //查表
        }
        public long tryWays(int n){
            if (n==0){
                memo[n]=0;
                return 0;
            }
            if (n==1){
                memo[n]=1;
                return 1;
            }
            if (n==2){
                memo[n]=2;
                return 2;
            }
            if (n==3){
                memo[n]=4;
                return 4;
            }
            if (memo[n]!=-1) return memo[n];
            memo[n] = (tryWays(n-1)+tryWays(n-2)+tryWays(n-3))%1000000007;
            return memo[n];
        }
    //dp
    public int waysToStep_1(int n){
        long dp[] = new long[n+1];
        if (n==0) return 0;
        if (n==1) return 1;
        if (n==2) return 2;
        if (n==3) return 4;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i=4;i<=n;i++){
            dp[i] = (dp[i-1]+dp[i-2]+dp[i-3])%1000000007;
        }
        return (int)dp[n];
    }

    //状态压缩
    public int waysToStep_2(int n){
        if (n==1) return 1;
        if (n==2) return 2;
        if (n==3) return 4;
        long a = 1, b = 2, c =4;
        for (int i=4;i<=n;i++){
            long tmp = (a+b+c)%1000000007;
            a = b;
            b = c;
            c = tmp;
        }
        return (int)c;
    }
}
