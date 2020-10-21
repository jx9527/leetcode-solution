package com.company;

import java.util.PrimitiveIterator;


//求连续子序列的和的最大值
public class leet53_offer42 {
    /**
     * dp[i] = max(dp[i-1]+A[i],A[i])
     * dp代表前i个序列的最大和
     */
    public static int maxSubArray(int[] nums) {
        if (nums==null||nums.length==0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        //baseCase
        dp[0]=nums[0];
        int res = nums[0];
        for (int i=1;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    //状态压缩，当前状态dp[i]只和前一个状态dp[i-1]有关
    public static int maxSubArray2(int[] nums){
        if (nums==null||nums.length==0) return 0;
        int len = nums.length;
        int dp = nums[0];
        int res = nums[0];
        for (int i=1;i<nums.length;i++){
            dp = Math.max(dp+nums[i],nums[i]);
            res = Math.max(res,dp);
        }
        return res;
    }

    //暴力
    public int maxSubArray3(int[] nums){
        if (nums==null||nums.length==0) return 0;
        int len = nums.length;
        int sum = 0;
        int max = nums[0];
        for (int i=0;i<len;i++){
            sum = 0;
            for (int j=i;j<len;j++){
                sum += nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    //分治法
    /**
     * 分治法是将整个数组切分成几个小组，然后每个小组再切分成几个更小的小组，
     * 一直到不能继续切分也就是只剩一个数字为止。每个小组会计算出最优值，汇报给上一级的小组，
     * 一级一级汇报，上级拿到下级的汇报找到最大值，得到最终的结果。和归并排序的算法类似，先切分，再合并结果。
     *
     * 如何切分这些组合才能使每个小组之间不会有重复的组合:
     * 左边序列
     * 右边序列
     * 中间：包含左边序列的最右边元素以及右边序列的最左边元素
     *
     * 连续子序列的最大和主要由这三部分子区间里元素的最大和得到：
     * [left,mid]
     * [mid+1,right]
     * [mid,mid+1] 从左边序列的最右边元素向左一个一个累加，找出每次累加的最大值，就是左边序列的最大值
     *             同理，右边...
     *             然后两边合并就是中间的最大值
     */
    public static int maxSubArray4(int[] nums){
        return maxSubArrayDivdeWithBorder(nums,0,nums.length-1);
    }

    public static int maxSubArrayDivdeWithBorder(int[] nums,int start, int end){
        if (start == end) return nums[start];
        //计算中间值
        int center = (start+end)/2;
        int leftMax = maxSubArrayDivdeWithBorder(nums,start,center); //计算左侧子序列的最大值
        int rightMax = maxSubArrayDivdeWithBorder(nums,center+1,end); //计算右侧子序列的最大值
        //计算中间区间
        // 计算包含左侧子序列最后一个元素的子序列最大值
        int leftCrossMax = Integer.MIN_VALUE;
        int leftCrossSum = 0;
        for (int i=center;i>=start;i--){
            leftCrossSum += nums[i];
            leftCrossMax = Math.max(leftCrossMax,leftCrossSum);
        }
        // 计算包含右侧子序列最后一个元素的子序列最大值
        int rightCrossMax = nums[center+1];
        int rightCrossSum = 0;
        for (int i=center+1;i<end;i++){
            rightCrossSum += nums[i];
            rightCrossMax = Math.max(rightCrossSum, rightCrossMax);
        }
        //计算跨中心的子序列的最大值
        int CrossMax = leftCrossMax + rightCrossMax;
        //比较三者，返回最大值
        return Math.max(CrossMax,Math.max(leftMax,rightMax));
    }


    public static void main(String[] args){
        int nums[] = {-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray(nums);
    }
}
