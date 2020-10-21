package com.company;

public class TestThreadLocal {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args){
        threadLocal.set("hello world");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:"+threadLocal.get());
            }
        });
        thread.start();
        System.out.println("main:"+threadLocal.get());

    }
}
