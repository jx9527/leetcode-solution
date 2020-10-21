package com.company.search;

public class BinarySearch {

    public static int rank(int key,int[] a){
         int lo=0;
         int hi=a.length - 1;
         while (lo <= hi){
             int mid = lo + (hi-lo) / 2;
             if (key < a[mid]) hi = mid - 1;
             if (key > a[mid]) lo = mid + 1;
             else return mid;
         }
         return -1;
    }

    int sqrt(int x){
        int i = 0;
        int j = x/2 + 1;
        while (i<=j){
            int mid = (i+j)/2;
            int sq = mid*mid;
            if(sq == x) return mid;
            else if (sq < x) i = mid+1;
            else j = mid - 1;
        }
        return j;
    }


    public static void main(String[] args){
        String s = "The square root of 2.0 is "+Math.sqrt(2.0);
        System.out.println(s);
    }
}
