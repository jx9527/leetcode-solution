package com.company;


import javax.security.auth.Subject;

public class MyThread3
{
    private static volatile Object resourceA = new Object();
    public static void main(String[] args) throws InterruptedException
    {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadOne begin run!");
                for (;;){
                }
            }
        });
        final Thread mainThread = Thread.currentThread();
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                //休眠1s
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                mainThread.interrupt();
            }
        });
        threadOne.start();
        threadTwo.start();
        threadTwo.interrupt();
        try {
            threadOne.join();
        }catch (InterruptedException e){
            System.out.println("main thead "+ e);
        }
        System.out.println("hhhh");
    }
}