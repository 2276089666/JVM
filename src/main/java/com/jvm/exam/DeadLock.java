package com.jvm.exam;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ws
 * @Date 2021/6/18 22:29
 */
public class DeadLock {

    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

     static class Task1 implements Runnable {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("task 1 execute...");
                sleep(2);
                synchronized (lock2) {
                    System.out.println();
                }
            }
        }
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("task 1 execute...");
                sleep(2);
                synchronized (lock1) {
                    System.out.println();
                }
            }
        }
    }

    public static void sleep(long timeOut) {
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new MyThreadFactory(), new MyRejectedPolicy());
        pool.execute(new Task1());
        pool.execute(new Task2());
    }
}
