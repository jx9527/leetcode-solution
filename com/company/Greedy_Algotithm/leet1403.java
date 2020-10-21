package com.company.Greedy_Algotithm;

import com.company.search.In;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.*;

public class leet1403 {

        // 抽象为背包问题，即为给定容量(sum[nums()],)，求能够装下的最大重量
       // 不用降序排序 ---- 倒序遍历
       // 很显然是尽量取最大值得元素组成子序列。因此，先做降序排列，而后尽量取最短序列（贪心）
        public List<Integer> minSubsequence(int[] nums){
            if (nums.length==0) return null;
            int total = 0;
            int sum = 0 , index = nums.length-1;
            for (int i=0;i<nums.length;i++){
                total += nums[i];
            }
            List<Integer> res = new LinkedList<>();
            Arrays.sort(nums);
            while (index>=0){
                sum += nums[index];
                res.add(nums[index]);
                if (sum>total/2) break;
                index--;
            }
            return res;
        }

}
