package com.company;

public class leet1518 {
    public int numWaterBottles(int numBottles, int numExchange){
        int sum = 0;
        int divide = 0;
        int left = 0;
        sum += numBottles;
        while (numBottles/numExchange>0){
            divide = numBottles/numExchange;
            left = numBottles%numExchange;
            numBottles = divide + left;
            sum += divide;
        }
        return sum;
    }

    //首先肯定有b个空瓶,每次拿出来ex个瓶子换一瓶酒，再喝完这瓶，得到一个空瓶。
    public int numWaterBottles_1(int numBottles, int numExchange){
        int bottle = numBottles, ans = numExchange;
        while (bottle>=numExchange){
            bottle-=numExchange;
            ++ans;
            ++bottle;
        }
        return ans;
    }
}
