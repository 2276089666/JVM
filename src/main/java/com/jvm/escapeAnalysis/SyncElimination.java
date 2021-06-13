package com.jvm.escapeAnalysis;

/**
 * @Author ws
 * @Date 2021/6/13 13:54
 */

/**
 *  -XX:+EliminateLocks
 */
public class SyncElimination {
    static class A {
        int b = 10;
    }
    static Object lock;
    static A a = new A();
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            Object lock = new Object();
            synchronized (lock) {
                for (int i = 0; i < 1000000; i++) {
                    a.b = i;
                }
            }
        });
        t1.start();
        t1.join();
        System.out.println("同步消除花费的时间:\t" + (System.currentTimeMillis() - start));


        long start2 = System.currentTimeMillis();
        Thread t2 = new Thread(() -> {
            lock = new Object();  //线程逃逸,同步无法消除
            synchronized (lock) {
                for (int i = 0; i < 1000000; i++) {
                    a.b = i;
                }
            }
        });
        t2.start();
        t2.join();
        System.out.println("非同步消除花费的时间:\t" + (System.currentTimeMillis() - start2));
    }
}
