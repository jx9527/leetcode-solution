package com.company;

import com.sun.media.sound.RIFFReader;

public class leet860 {



    public static boolean lemonadeChange(int[] bills){
        int num5=0,num10=0;
        for (int bill:bills){
            if (bill==5) {
                num5++;
            }
            if (bill==10){
                num5--;
                num10++;
                if (num5<0) return false;
            }
            if (bill==20){
                if (num10==0){
                    num5 -= 3;
                }else {
                    num5--;
                    num10--;
                }
                if (num5<0||num10<0) return false;
            }
        }
        return num5 >= 0 && num10 >= 0;
    }

    public static void main(String[] args){
        int[] arr = {5,5,10,20,5,5,5,5,5,5,5,5,5,10,5,5,20,5,20,5};
        lemonadeChange(arr);
    }

}
