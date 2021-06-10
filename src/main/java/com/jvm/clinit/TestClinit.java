package com.jvm.clinit;

/**
 * @Author ws
 * @Date 2021/6/10 14:30
 */
// 测试类加载的初始化阶段的clinit
// 生成的<clinit>()与静态的赋值动作有关
public class TestClinit {
    private static int a=1;
    private int b=2;
    {
        b=3;
    }
    static {
        a=4;
        c=5;
    }
    private static int c =6;

    public static void main(String[] args) {
        int c=12;
        System.out.println(c);
    }
}
