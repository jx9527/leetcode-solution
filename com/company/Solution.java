package com.company;

import com.company.search.In;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Map;
class Solution {
    static int res = 0;
    public static int maxProfit(int[] prices){
        if(prices==null || prices.length == 0) return 0;
        helper(prices,0,prices.length-1);
        return res;
    }
    public static void helper(int[] prices,int left, int right){
        if (left == right) return;
        int mid = left + (right - left)/2;
        helper(prices,left,mid);
        helper(prices,mid+1,right);
        merge(prices,left,right,mid);
    }

    private static void merge(int[] prices,int left,int right, int mid){
        int min = 100;
        int max = Integer.MIN_VALUE;
        for (int i=left;i<mid;i++){
            min = Math.min(prices[i],min);
        }
        for (int i=mid+1;i<=right;i++){
            max = Math.max(prices[i],max);
        }
        res = Math.max(max-min,res);
    }


    public static void main(String[] args){
        int[] prices = {7,1,5,3,6,4};
        maxProfit(prices);
    }
}