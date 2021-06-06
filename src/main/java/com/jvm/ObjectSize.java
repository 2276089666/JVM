package com.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author ws
 * @Date 2021/6/6 17:05
 */
public class ObjectSize {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        int[] ints = {};
        System.out.println(ClassLayout.parseInstance(ints).toPrintable());
        AA aa = new AA();
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
    }

    public static class AA{
        int a;
        String b;
    }
}
