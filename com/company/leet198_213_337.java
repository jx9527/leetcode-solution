package com.company;


// 打家劫舍
// 动态规划【数组】
public class leet198_213_337 {
    /**
     * 暴力法
     * 用二维数组代表前i个房间偷/不偷得到的最高金额
     */
    public int rob_198_baoli(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int dp[][] = new int[len+1][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i=1;i<=len;i++){
            //当前i偷
            dp[i][1] = dp[i-1][0] + nums[i];
            //当前i不偷
            dp[i][0] = Math.max(dp[i-1][1],dp[i-1][0]);
        }
        return Math.max(dp[len][1],dp[len][0]);
    }

    //每个阶段确定一家偷还是不偷，所以决策就是偷和不偷两种
    //dp表
    public int rob_198(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int[] res = new int[len + 1];
        res[0] = 0;
        res[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            res[i] = Math.max(res[i - 1], res[i - 2] + nums[i-1]);
        }
        return res[len];
    }

    //动态规划(维护两个变量)
    public int rob_198_1(int[] nums) {
        //dp[i]表示前i家可以得到的最高金额,dp[i]=Max(dp[i-1],dp[i-2]+nums[i])
        // first = dp[i-1]
        // second = dp[i-2]
        if (nums == null || nums.length == 0) return 0;
        int first = 0, second = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(first + nums[i], second);
            first = second;
            second = res;
        }
        return res;
    }



    //滚动数组版
    //确定baseCase:
    //确定【状态--会变化的变量】 for item:状态集合 每个房间对应的分数
    //确定【选择--导致状态变化的行为】
    /**
     * 两种选择 当前节点 偷/不偷
     */
    //确定dp函数/数组含义：dp[i]代表前i家获取的最高金额
    public int rob_198_2(int[] nums){
        int dp[] = new int[3];
        for (int i=0;i<nums.length;i++){
            dp[(i+3)%3] = Math.max(dp[(i+1)%3]+nums[i],dp[i+2]%3);
        }
        return dp[(nums.length+2)%3];
    }
    // 如果第一家抢：最后一家一定不能抢，从0个到len-2做动态规划
    // 如果第一家不抢: 从1到len-1做动态规划
    // 然后找出最大值
    public int rob_213(int[] nums){
        if (nums==null || nums.length==0)
            return 0;
        int len = nums.length;
        if (len==1)
            return nums[0];
        int[] dp1 = new int[len];
        int[] dp2 = new int[len+1];
        //第一家抢
        dp1[0]=0;
        dp1[1]=nums[0];
        for (int i=2;i<len;i++){
            dp1[i] = Math.max(dp1[i-1],dp1[i-2]+nums[i-1]);
        }
        //第一家不抢
        dp2[0]=0;
        dp2[1]=0;
        for (int i=2;i<len;i++){
            dp2[i]=Math.max(dp2[i-1],dp2[i-2]+nums[i-1]);
        }
        return Math.max(dp1[len-1],dp2[len]);
    }
    /**
     *  维护两个变量
     */
    public int rob_213_1(int[] nums){
        if (nums==null||nums.length==0) return 0;
        int len = nums.length;
        if (len==1)
            return nums[0];
        // 第一家偷，最后一家不偷
        int first=0,second=0,res1=0;
        for (int i=0;i<len-1;i++){
            res1 = Math.max(first+nums[i],second);
            first=second;
            second = res1;
        }
        // 第一家不偷，最后一家有可能偷
        first=0;second=0;int res2=0;
        for (int i=0;i<len;i++){
            res2 = Math.max(first+nums[i],second);
            first=second;
            second=res2;
        }
        return Math.max(res1,res2);
    }

    //确定basecase: 没房子 返回0   有一个房子  返回该房子对应的分数
    //确定【状态 -- 会变化的变量】  for item:状态集合  遍历每个房间对应的分数
    //确定【选择 -- 导致状态变化的行为】
    /**
     * 两种选择：（1）根节点 不偷 那么可以考虑其左右孩子  左右孩子又是子问题
     *
     *          （2）根节点  偷  那么它的孩子节点不打劫  直接打劫孙子辈节点
     *
     *          选择中的两种情况将全部情况都概括
     *
     */
    //确定dp函数【递归】/数组【动态规划】的含义: dp[i]表示前i家获取的最高金额

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
    }

    public int rob_337(TreeNode root){

        if (root==null) return 0;
        int res1 = 0;

        //(1) 根在结果中
        res1 = root.value;
        if (root.left!=null)
            res1 = res1 + (rob_337(root.left.left) + rob_337(root.left.right));
        if (root.right!=null)
            res1 = res1 + (rob_337(root.right.left) + rob_337(root.right.right));

        //(2) 根不在结果中
        int res2 = rob_337(root.left) + rob_337(root.right);
        return Math.max(res1,res2);
    }

}
