package com.company;


//


import java.util.Arrays;

public class leet474 {


    //(自顶向下)递归版本
    //递归函数表达式 dp(i,j,k) = max[(dp(i-1,j,k)),dp(i-1,j-当前字符串0的个数,k-当前字符串1的个数)+1]
    public int findMaxFormH(String[] strs, int m, int n){
        if(strs.length==0||(m==0&&n==0)) return 0;
        return tryFindMaxForm(strs,strs.length-1,m,n);
    }
    public int tryFindMaxForm(String[] strs,int i, int m, int n){
      int cnt[] =countZeroAndOne(strs[i]);
      if (i<0) return 0;
      if (m>=cnt[0]&&n>=cnt[1])
          return Math.max(tryFindMaxForm(strs,i-1,m,n),tryFindMaxForm(strs,i-1,m-cnt[0],n-cnt[1]));
      else
          return tryFindMaxForm(strs,i-1,m,n);
    }

    //备忘录递归法
    private int[][][] memo;
    private int findMaxForm_memo(String[] strs,int m,int n){

        if (strs.length==0||(m==0&&n==0)) return 0;
        this.memo = new int[strs.length][m+1][n+1];
        //初始memo三维数组全为-1。
        for (int i=0;i<memo.length;i++){
            for (int j=0;j<memo[i].length;j++){
                Arrays.fill(memo[i][j],-1);
            }
        }
        return tryFindMaxForm_memo(strs,strs.length-1,m,n);

    }

    public int tryFindMaxForm_memo(String[] strs,int i,int m,int n){
        if (i<0) return 0;
        if (memo[i][m][n]!=-1) return memo[i][m][n];
        int cnt[] = countZeroAndOne(strs[i]);
        if (m>cnt[0]&&n>cnt[1])
            memo[i][m][n] = Math.max(tryFindMaxForm_memo(strs,i-1,m,n),
                    1+tryFindMaxForm_memo(strs,i-1,m-cnt[0],n-cnt[1]));
        else
            memo[i][m][n]=tryFindMaxForm_memo(strs,i-1,m,n);
        return memo[i][m][n];

    }


    // (自底向上) 动态规划版本
    // 0和1两种值 每个字符串的价值为1
    // 多维动态规划

    // 整体思想： 当前问题的最优解就是由前子问题的最优解+当前的选择的状态

    // 确定baseCase: 第0个字符串是空串
    // 确定【状态--原问题和子问题中变化的变量】：
    /**
     * {
     *                当前i不拼  dp[i-1][j][k]
     *   dp[i][j][k]
     *                当前i拼  dp[i-1][j-当前字符串使用0的个数][k-当前字符串使用1的个数]+1
     * }
     */
    // 确定【选择】：导致【状态】产生变化的行为: 当前字符串拼还是不拼
    // 确定dp函数/数组的含义：dp[i][j][k] 前i个字符串中能够使用j个0和k个1的所能拼的字符串的最大数量

    public int[] countZeroAndOne(String str){
        int[] cnt= new int[2];
        for (char c : str.toCharArray()){
            cnt[c-'0']++;
        }
        return cnt;
    }

    public int findMaxForm(String[] strs, int m, int n){
        int len = strs.length;
        int [][][]dp = new int[len+1][m+1][n+1];
        for (int i=1;i<=len;i++){
            int []cnt = countZeroAndOne(strs[i-1]);
            for (int j=0;j<=m;j++){
                for (int k=0;k<=n;k++){
                    int zeros = cnt[0];
                    int ones = cnt[1];
                    //如果0或者1的数量不够则用上一行的值即可。
                    dp[i][j][k] = dp[i-1][j][k];
                    if (j>=zeros && k>=ones){
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-zeros][k-ones]+1);
                    }

                }
            }
        }
        return dp[len][m][n];
    }


    //滚动数组版本
    //因为当前状态只和前一个状态有关，只用到了上一行的值 所以可以用滚动数组解决
    /**
     *  ----------------
     *  【填表顺序】
     *  「动态规划」填表的过程，只有从后向前填表，当前状态要参考的值才会是正确的。
     *
     *  -----------------
     *  dp[i][j]表示使用i个0和j个1，最多拼出的字符串数目
     *  注意逆序，则是0-1背包问题
     *  顺序就变成了完全背包问题
     *  可以看以下解释
     *  https://www.cnblogs.com/islch/p/12568358.html
     *  因为计算每个位置dp值的时候用的是前一个循环中的dp值，
     *  从后向前的话在计算每个位置时所用到的前一个循环中的dp值还没有被覆盖，可以直接拿来用
     */

    // dp(i,j) = max(dp[i-zeros(k),j-ones(k)])
    public int findMaxForm_1(String[] strs, int m ,int n){
        int [][]dp = new int[m+1][n+1];
        for (String s: strs){
            int[] count = countZeroAndOne(s);
            for (int i=m;i>=count[0];i--){
                for (int j=n;j>count[1];j--){
                    dp[i][j] = Math.max(1+dp[i-count[0]][j-count[1]],dp[i][j]);
                }
            }
        }
        return dp[m][n];
    }
}
