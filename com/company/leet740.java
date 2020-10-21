package com.company;

import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;
import org.omg.CORBA.MARSHAL;

import java.lang.reflect.Array;
import java.util.Arrays;

public class leet740 {


    //【题目特点】
    // 当前状态和数组的两边状态都有关系，其实只需要考虑一个边就行了。
    // 左边的就等于上一个元素删除右边的了


    //暴力法
    //以数组下标作为索引
    // dp[i][0] 代表第i个不拿获得的点数
    // dp[i][1] 代表第i个数拿能获得的最大点数
    public int baoli_delete(int[] nums){
        int n = nums.length;
        if (n==0) return 0;
        if (n==1) return nums[0];
        Arrays.sort(nums);
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        //状态的遍历通过数组下标索引
        for (int i=1;i<n;i++){
            //1、当前元素和前一个元素相同
            if (nums[i]-nums[i-1] == 0){
               dp[i][0]=dp[i-1][0];
               dp[i][1]=dp[i-1][1]+nums[i];
               continue;
           }
            //2、当前元素和前一个元素不同
            if (nums[i]-nums[i-1]==1){
                dp[i][1]=dp[i-1][0] + nums[i];
            }else {
                dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0])+nums[i];
            }
            dp[i][0]= Math.max(dp[i-1][1],dp[i-1][0]);
        }
        //返回的是dp数组
        return Math.max(dp[n-1][0],dp[n-1][1]);
    }

    // 以数组中的值作为索引
    public int delete(int nums[]){
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int max = nums[0];

        //构造新的数据结构
        for (int i=0;i<nums.length;i++){
            max = Math.max(max, nums[i]);
        }

        int counts[] = new int[max+1];
        int dp[][] = new int[max+1][2];
        dp[1][0] = 0;
        dp[1][1] = counts[1];

        for (int i=2;i<=max;++i){
            dp[i][1] = dp[i-1][0]+i*counts[i];
            dp[i][0] = Math.max(dp[i-1][1], dp[i-1][0]);
        }

        return Math.max(dp[max][0], dp[max][1]);
    }

    //动态规划法
    public int deleteAndEarn(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }else if (nums.length == 1){
            return nums[0];
        }
        int len = nums.length;
        int max = nums[0];
        for (int i=0;i<len;++i){
            max = Math.max(max, nums[i]);
        }
        // **** 【 亮点  利用新的数据结构 】构造一个新的数组  下标对应原值  值对应原值的个数

        //两种选择：(1) 选择不删除当前点 前一个数字的位置的最优结果
        //         (2) 选择删除当前点  i-2位置处的最优结果+当前下标*个数
        int []all  = new int[max+1];
        for (int item : nums){
            all[item]++;
        }
        int []dp = new int[max+1];
        dp[1] = all[1] * 1;
        dp[2] = Math.max(dp[1], all[2] * 2);

        //动态规划求解
        for (int i=2;i<max;++i){
            dp[i] = Math.max(dp[i-1], dp[i-2] + i* all[i]);
        }
        return dp[max];
    }


    public static void main(String args[]){

    }
}
