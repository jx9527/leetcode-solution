package com.company;

import java.util.Arrays;

public class leet416 {

    //转换为0-1背包问题 【物品有重量和价值，背包有一定容量，求物品能够装进背包所得的最大价值】
    //背包问题： dp[物品索引][背包容量] = max(dp[物品索引-1][背包容量], dp[物品索引-1][背包容量-当前物品重量]+当前物品价值)
    //确定baseCase:
    //确定【状态--原问题和子问题中变化的变量】：
    /**
     * {
     *
     *   不算当前nums[i],当前状态取决于上一个状态dp[i-1][j]
     *   算入当前nums[i],是否能恰好装满背包，取决于状态dp[i-1][j-nums[i-1]]
     * }
     */
    //确定【选择】：
    //确定【dp函数/数组含义】：dp[i][j]=x 表示对前i个数选择加和，是不是恰好等于j。x = true/false
    public boolean canPartition(int[] nums) {
        if (nums.length == 0) return false;
        int len = nums.length;
        //求sum/2
        int sum = 0;
        for (int item : nums) {
            sum += item;
        }
        sum = sum / 2;
        //dp[0][...] 代表0个物品 false  dp[...][0] 容量为0 true
        boolean dp[][] = new boolean[len + 1][sum + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], false);
        }
        //baseCase
        for (int i = 0; i <= len; i++)
            dp[i][0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j < sum; j++) {
                //如果背包容量<物品重量
                //则当前物品不装入背包 当前状态取决于上一个状态
                if (j < nums[i])
                    dp[i][j] = dp[i - 1][j];
                    //背包容量>物品重量
                    //1、装 dp[i-1][j-nums[i]]
                    //2、不装dp[i-1][j]
                else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][sum];
    }
    // 考虑空间优化
    // 当前状态只与前一个状态有关
    // 因此状态数组可以只设置 2 行
    // dp[i%2][j] = dp[(i+1)%2];
    // dp[i%2][j] = dp[(i+1)%2][j] | dp[(i+1)%2][j-nums[(i+1)%2]]


    //滚动数组版本
    //dp[i][j]都是通过上一行dp[i-1][...]转移过来的，之前的数据不会再使用了。
    //所以当前状态只与前一个状态有关
    //二维压缩为一维
    //dp[i][j]=x
    public boolean carPartiton(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int num : nums) sum += num;
        //如果sum是奇数,那么sum/2就是小数，那么是不可能成立的
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        boolean dp[] = new boolean[len + 1];
        Arrays.fill(dp, false);
        for (int i = 0; i < len; i++) {
            for (int j = sum; j >= 0; j--) { // 做剪枝  j>=nums[i]不成立的话 现在状态=原来状态   那么就不需要往下遍历 可以进行下一个循环
                //for(int j=sum;j>=num[i];j--)

                //做剪枝
                if (dp[sum]) return true;

                if (j - nums[i] >= 0)
                    dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[sum];
    }

    //暴力求解（超时间）
    public static boolean  find(int[] nums,int target, int index){
        if (target == 0) return true;
        for (int i=index;i<nums.length;i++){
            if (target-nums[i]<0) return false;
            if (find(nums, target-nums[i],i+1)) return true;
        }
        return false;
    }
    public static boolean carPartition_digui(int[] nums){
        int total = 0;
        for (int num:nums) total+=num;
        // 如果总和是奇数，那么不成立 返回false
        if (total%2!=0) return false;
        if (total==0) return true;
        return find(nums,total/2,0);
    }

    //暴力递归




    //深度优先搜索+剪枝

    /**DFS模板
     * //会有相对应的方向变量  例如： dx[4].dy[4];
     * void DFS(int x,int y)
     * {
     *      if(满足所需要的条件)   {  相应的操作；return；}
     *     else{
     *             for(int i= ; ;) //如果是方向的话，会枚举方向
     *             {
     *                   枚举加方向的新坐标；
     *                   if(界限 ：例如：不能出到地图外，有障碍，已经访问过) continue;
     *                    设置已经访问新坐标；
     *                     DFS(新坐标);
     *                    恢复到未被访问；
     *             }
     *
     *        }
     * }
     *
     * int main()
     * {
     *     //  需注意要将起点设置成已访问。
     * }
     */
//    public static boolean canPartition_dfs(int[] nums){
//        int sum = 0;
//        for (int item:nums) sum += item;
//        sum /= 2;
//        if (sum % 2 == 1) return false;
//        int visited[] = new int[nums.length];
//        //排序....
//        Arrays.sort(nums);
//        if (dfs(nums,visited,0,sum/2,0));
//        return false;
//    }
//
//    public static boolean dfs(int[] nums, int[] visited, int sum, int target, int i){
//        if (sum == target) return true;
//        if (sum > target || i>nums.length) return false;
//        //剪枝
//        if (i>=1&&nums[i]==nums[i-1]&&visited[i-1]==0){
//
//        }else {
//            sum = sum + nums[i];
//            visited[i] = 1;
//            if (dfs(nums,visited,sum,target,i+1)) return true;
//            sum = sum - nums[i];
//            visited[i] = 0;
//        }
//        if (dfs(nums, visited, sum, target, i + 1)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean canPartition_dfs(int[] nums){
//        int sum = 0;
//        for (int item:nums) sum += item;
//        if (sum%2==1) return false;
//        sum /= 2;
//        int visited[][] = new int[nums.length][sum+1];
//        return dfs(nums,sum,0,visited);
//    }
//    public static boolean dfs(int[] nums,int sum, int index, int[][] visited){
//        if (sum ==0) return true;
//        if (sum<0||index>nums.length) return false;
//
//        //不使用当前数字
//        boolean used_index = dfs(nums,sum,index+1,visited);
//        //使用当前数字
//        boolean unused_index = dfs(nums,sum-nums[index],index+1,visited);
//        if (unused_index || unused_index)
//    }

    //回溯+剪枝
    static boolean res = false;
    public static boolean canPartition_huishuo(int[] nums){
        int sum = 0;
        for (int item:nums) sum += item;
        if ((sum & 1) == 1) return false;
        int target = sum/2;
        boolean[] mark = new boolean[nums.length];
        Arrays.sort(nums);
        dfs_huishuo(nums,mark,0,target);
        return res;
    }
    public static void dfs_huishuo(int[] nums,boolean[] mark, int sum, int target){
        if (res) return;
        if (sum == target){
            res = true;
            return;
        }
        if (sum>target) return;
        for (int i=0;i<nums.length;++i){
            //剪枝
            if (i>0 && nums[i-1] == nums[i] && !mark[i-1]) continue;
            if (!mark[i]){
                mark[i] = true;
                dfs_huishuo(nums,mark,sum+nums[i],target);
                mark[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        int nums[] = {1, 5, 11, 5};
        System.out.println(canPartition_huishuo(nums));
    }

}
