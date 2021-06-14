package com.jvm.hashCode;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author ws
 * @Date 2021/6/14 10:30
 */
public class TestSyncHashCode {

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("轻量级锁:\n"+ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        thread1.start();
        thread1.join();

        System.out.println("无锁:\n"+ClassLayout.parseInstance(lock).toPrintable());




        Thread[] threads=new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                    synchronized (lock){
                        System.out.println("重量级锁"+ClassLayout.parseInstance(lock).toPrintable());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            },"thread-"+i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

    }
}
