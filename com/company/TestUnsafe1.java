package com.company;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafe1 {
    static final Unsafe unsafe;
    static final long stateOffset;
    private volatile long state = 0;
    static {
        try{
            //使用反射获取unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            //设置为可存取
            field.setAccessible(true);
            //获取该变量的值
            unsafe = (Unsafe)field.get(null);
            //获取state在TestUnsafe中的偏移值
            stateOffset = unsafe.objectFieldOffset(TestUnsafe1.class.getDeclaredField("state"));
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }
    }
    public static void main(String[] args){
        TestUnsafe1 test = new TestUnsafe1();
        Boolean success = unsafe.compareAndSwapInt(test,stateOffset,0,1);
        System.out.println(success);
    }
}
