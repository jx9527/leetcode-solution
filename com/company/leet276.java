package com.company;
//双重dp问题
public class leet276 {

    // 状态选择: same[i] 当前i与i-1颜色相同，那么有多少涂色方案？ 取决i-1的涂色方案，
    //   又因为i-1与i-2的颜色不同，那么  same[i] = diff[i-1]
    //           diff[i] 当前i与i-1颜色不同，那么有多少涂色方案？
    //   当前i有k-1种颜色选择,i-1有same[i-1]和diff[i-1]种不同的颜色方案，那么总共有(k-1)(same+diff)

    //BaseCase diff[1] 代表1号柱子与0号柱子（不存在）颜色不同 有k种
    //         same[1] 代表1号柱子与0号柱子颜色相同 有0种
    public static int numWays_1(int n, int k) {
        if(n == 0 || k == 0) return 0;
        int[] same = new int[n+1], diff = new int[n+1];
        same[1] = 0;
        diff[1] = k;
        for(int i = 1; i <= n; i++) {
            same[i] = 1 * diff[i - 1];
            diff[i] = (k-1)*(diff[i-1] + same[i-1]);
        }
        return same[n] + diff[n];
    }

    


    public static void main(String[] args){
//        System.out.println(numWays(4,2));
        System.out.println(numWays_1(4,2));
    }

}
