package com.jvm.parentAppoint;

public class StringTest {

    public static void main(String[] args) {
        // 发现结果没有打印我们自定义的String类的静态代码块的内容，证明了双亲委派机制
        String str = new String();
    }
}
