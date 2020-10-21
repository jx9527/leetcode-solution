package com.company;

import java.util.PriorityQueue;

public class leet1050 {

    //暴力法
    public int largestSumAfterKNegations(int[] A,int K){
        while (K>0){
            int minIndex = 0;
            for (int i=1;i<A.length;i++){
                if (A[i]<A[minIndex]) {
                    minIndex = i;
                }
            }
            A[minIndex] = -A[minIndex];
            K--;
        }
        int sum = 0;
        for (int i=0;i<A.length;i++){
            sum += A[i];
        }
        return sum;
    }

    //小顶堆排序  log(N)+Klog(N)+N
    public int largestSum(int[] A,int K){
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int a:A){
            pq.add(a);
        }
        while (K>0){
            Integer poll = pq.poll();
            pq.add(-poll);
            K--;
        }
        while (!pq.isEmpty()){
            res += pq.poll();
        }
        return res;
    }



    //计数排序
    // 时间复杂度 o(N) + O(K) = O(N)
    public int largestSumAfter(int[] A,int K){

        int[] number = new int[201];
        for (int t:A){
            number[t+100]++;
        }
        int i = 0;
        while(K>0){
            while (number[i]==0) {
                i++;
            }
            number[i]--;
            number[200-i]++; //i的相反数+1
            if (i>100){
                i = 200-i; //若原最小数索引>100,则新的最小数索引应为200-i.(索引即number[]数组的下标)
            }
            K--;
        }
        int sum = 0;
        for (int j=i;j<number.length;j++){
            sum += (j-100)*number[j];
        }
        return sum;

    }

}
