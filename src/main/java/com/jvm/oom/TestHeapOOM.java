package com.jvm.oom;

import java.util.ArrayList;

/**
 * @Author ws
 * @Date 2021/6/8 20:02
 */

// -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError

/**
 * 报错为:Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class TestHeapOOM {
    static class Object{

    }

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        while (true){
            list.add(new Object());
        }
    }
}
