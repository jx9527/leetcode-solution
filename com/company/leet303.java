package com.company;




import com.company.search.In;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class leet303 {
    //暴力法 空间O(1) 时间O(n)
    private int[] data;
    public leet303(int[] nums){
        data = nums; // 引用的复杂度O(1)
    }
    public int sumRange(int i,int j){
        int sum = 0;
        for (int k=i;k<=j;k++){
            sum += data[k];
        }
        return sum;
    }
    //缓存--空间换时间--预先计算所有范围的可能性并将结果存在哈希表中，可以将查询加速
    private Map<Pair<Integer,Integer>,Integer> map_1 = new HashMap<>();
    public leet303(int[] nums,String lable){

        //将所有的可能都保存下来
        for (int i=0;i<nums.length;i++){
            int sum = 0;
            for (int j=i;j<nums.length;j++){
                sum += nums[j];
                map_1.put(new Pair<>(i, j), sum);
            }
        }
    }
    public int sumRange_1(int i,int j){
        return map_1.get(new Pair<>(i,j));
    }

    //动态规划
    //dp[i] 代表0-i-1个数字的累积和
    private int dp[];
    public leet303(int[] nums, int label){
        int len = nums.length;
        this.dp = new int[len+1];
        for (int i=1;i<=len;i++){
            dp[i] = dp[i-1] + nums[i-1];
        }
    }
    int sumRange_2(int i,int j){
        return this.dp[j+1]-this.dp[i];
    }
}
