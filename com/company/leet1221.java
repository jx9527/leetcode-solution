package com.company;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.function.ToIntFunction;

public class leet1221 {


    // 题目理解错误：只需要保证每个字符串L和R的数量相等即可，分割需要将整个字符串用到
    public static int balancedStringSplit(String s){
        int index = 0;
        int sum = 0;
        while (index < s.length()-1){
            if (s.charAt(index)!=s.charAt(index+1)){
                sum += 1;
                index++;
            }
            index++;
        }
        return sum;
    }

    public static int balanced(String s){
        int sum = 0;
        int n = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == 'R'){
                n++;
            }
            if (s.charAt(i) == 'L'){
                n--;
            }
            if (n==0){
                sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args){
        String s = "RLRRRLLRLL";
        balancedStringSplit(s);
        System.out.println();
    }
}
