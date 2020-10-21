package com.company.everyday;

import java.util.*;

public class leet771 {
    public int numJewelsInStones_0(String J, String S) {
            if (J.isEmpty()||S.isEmpty()) return 0;
            int sum = 0;
            for (int i=0;i<J.length();i++){
                for (int j=0;j<S.length();j++){
                    if (J.charAt(i) == S.charAt(j)){
                         sum++;
                    }
                }
            }
            return sum;
    }
    //哈希数组想法

    public int num_1(String J,String S){
        if (J.isEmpty()||S.isEmpty()) return 0;
        int length = 'z'-'A';
        int[] arr = new int[length+1];
        for (int i=0;i<S.length();i++){
            arr[S.charAt(i)-'A']++;
        }
        int sum = 0;
        for (int j=0;j<J.length();j++){
            sum += arr[J.charAt(j)-'A'];
        }
        return sum;
    }

    //哈希表想法
    public int num_2(String J,String S){
        int jewelsCount = 0;
        Set<Character> jewelsSet = new HashSet<Character>();
        int jewelsLength = J.length(), stonesLength = S.length();
        for (int i = 0; i < jewelsLength; i++) {
            char jewel = J.charAt(i);
            jewelsSet.add(jewel);
        }
        for (int i = 0; i < stonesLength; i++) {
            char stone = S.charAt(i);
            if (jewelsSet.contains(stone)) {  //该句的时间复杂度为1
                jewelsCount++;
            }
        }
        return jewelsCount;
    }
    //位存储的思想
    public int numJewelsInStones(String J, String S) {
        int[] bit=new int[4];
        int lenJ = J.length();
        int lenS = S.length();
        int sum = 0;
        for(int i = 0; i<lenJ;i++) bit[J.charAt(i)/32] |= (1<<(J.charAt(i)%32));
        for(int j = 0; j<lenS;j++) if((bit[S.charAt(j)/32] & (1<<(S.charAt(j)%32))) != 0) sum++;
        return sum;
    }
}


