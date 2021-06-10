package com.jvm.clinit;

/**
 * @Author ws
 * @Date 2021/6/10 14:55
 */
// 发现只能有一个线程能初始化
public class TestSyncClinit {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            new A();
        });
        Thread t2 = new Thread(() -> {
            new A();
        });
        t1.start();
        t2.start();
    }

   static class A {
        static {
           if (true){
               System.out.println(Thread.currentThread().getName() + "初始化 class A");
               while (true) {

               }
           }
        }
    }
}
