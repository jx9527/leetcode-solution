package com.company;

import sun.misc.Unsafe;

public class TestUnsafe {
    //获取Unsafe的实例
    static final Unsafe unsafe = Unsafe.getUnsafe();
    //记录变量state在类TestUnsafe中的偏移值
    static final long stateOffset;
    private volatile long state = 0;
    static {
        try{
            //获取偏移值
            stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }
    }

    public static void main(String[] args){
        TestUnsafe test = new TestUnsafe();
        Boolean sucess = unsafe.compareAndSwapInt(test,stateOffset,0,1);
        System.out.println(sucess);
    }
}
