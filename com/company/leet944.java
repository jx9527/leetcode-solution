package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class leet944 {

    public int minDeletionSize(String[] A){
        //对A按列遍历
        if (A.length==1) {
            return 0;
        }
        int num = 0;
        for (int i=0;i<A[0].length();i++){
            for (int j=0;j<A.length-1;j++){
                if ((A[j+1].charAt(i)-A[j].charAt(i))<0){
                    num++;
                    break;
                }
            }
        }
        return num;
    }
}
