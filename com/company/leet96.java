package com.company;

/**
 *  2020/08/23
 */

public class leet96 {

    public int numTrees(int n) {
        if (n==0 || n==1) return 1;
        int G[] = new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for(int i=2;i<=n;i++){
            for (int j=1;j<=i;j++){
                G[i] += G[j-1] * G[i-j];
            }
        }
        return G[n];
    }
    public int recur(int n, int[] memo){
        if (n==0||n==1) return memo[n]=1;
        if (memo[n] > 0) return memo[n];
        for (int i =0; i<=n-1; i++){
            memo[n] += numTrees_dg(i) + numTrees_dg(n-i-1);
        }
        return memo[n];
    }

    public int numTrees_dg(int n){
        int memo[] = new int[n+1];
        return recur(n,memo);
    }

    public static void main(){
        System.out.println("uuuu");
    }
}
