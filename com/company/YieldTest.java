package com.company;

public class YieldTest implements Runnable {
    YieldTest(){
        //创建并启动线程
        Thread t = new Thread(this);
        t.start();
    }
    public void run(){
        for (int i=0;i<5;i++){
            if ((i%5) == 0){
                //i==0的时候 让出CPU执行权 放弃时间片 进行下一轮调度
                System.out.println(Thread.currentThread()+"yield cpu...");
                // 当前线程让出CPU执行权
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread()+ " is over");
    }

    public static void main(String[] args){
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }

}
