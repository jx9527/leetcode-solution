package com.company;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;

public class leet546 {
    public int removeBoxes(int []boxes){
        int [][][]dp = new  int[100][100][100];
        return caculatePoints(boxes,dp,0,boxes.length-1,0);
    }
    //备忘录递归法
    // 分为两个策略
    // 1. 先删除连续的数字 然后加上剩余区间的得分
    // 2. 先删除阻碍连续数字的数字区间，然后在删除得到后的连续数字区间
    public int caculatePoints(int[] boxes, int[][][]dp, int l, int r, int k){
        if (l > r) return 0;
        //查表
        if (dp[l][r][k] !=0 ) return dp[l][r][k];


        dp[l][r][k] = caculatePoints(boxes,dp, l,r-1,0)+(k+1)*(k+1);
        for (int i=1;i<r;i++){
            if (boxes[i] == boxes[r]){
                dp[l][r][k] = Math.max(dp[l][r][k], caculatePoints(boxes, dp,1, i, k+1) + caculatePoints(boxes, dp, i+1, r-1, 0));
            }
        }
        return dp[l][r][k];
    }
}
