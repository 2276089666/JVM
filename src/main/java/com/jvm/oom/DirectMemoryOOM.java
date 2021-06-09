package com.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author ws
 * @Date 2021/6/9 14:28
 */

// -XX:MaxDirectMemorySize=10m

/**
 * Exception in thread "main" java.lang.OutOfMemoryError
 * 	at sun.misc.Unsafe.allocateMemory(Native Method)
 */
public class DirectMemoryOOM {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");

        // 私有的属性变为可以访问
        field.setAccessible(true);

        // 通过静态Unsafe字段获取它的对象
        Unsafe unsafe = (Unsafe)field.get(null);

        while (true){
            unsafe.allocateMemory(1024*1024);
        }

    }
}
