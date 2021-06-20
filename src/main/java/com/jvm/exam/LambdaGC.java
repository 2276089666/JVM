package com.jvm.exam;

/**
 * @Author ws
 * @Date 2021/6/20 14:51
 */
// -XX:MaxMetaspaceSize=9M -XX:+PrintGCDetails
public class LambdaGC {
    public static interface I {
        public void m();
    }

    public static void main(String[] args) {
        while (true){
            I i=()->{
                System.out.println("lambda Gc");
            };
        }
    }
}
