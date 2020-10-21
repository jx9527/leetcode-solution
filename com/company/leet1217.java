package com.company;

public class leet1217 {
    public int minCostToMoveChips(int[] position){
        int even = 0,odd = 0;
        for (int i=0;i<position.length;i++){
            if ((position[i]&1)==1) odd++;
            else even++;
        }
        return Math.min(even,odd);
    }
}
