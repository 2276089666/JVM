package com.jvm.oom;

/**
 * @Author ws
 * @Date 2021/6/8 20:54
 */

// -Xss2m 栈内存容量

/**
 * 由于hotspot不区分本地方法栈和虚拟机栈
 * 故报错为: Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 */
public class TestStackOutOfMemory {
    public void stackLeakByThread(){
        while (true){
            new Thread(()->{
                while (true){

                }
            }).start();
        }
    }

    public static void main(String[] args) {
        TestStackOutOfMemory o = new TestStackOutOfMemory();
        o.stackLeakByThread();
    }
}
