package com.jvm.returns;

/**
 * @Author ws
 * @Date 2021/6/11 17:10
 */
public class TestReturnValue {

    public int m1(){
        return 5;
    }

    public void m2(){
        int a=m1();
    }

    public void m3(){

    }

    public static void main(String[] args) {
        TestReturnValue o = new TestReturnValue();
        o.m2();
    }
}
