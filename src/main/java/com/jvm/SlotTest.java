package com.jvm;

/**
 * @Author ws
 * @Date 2021/6/11 10:55
 */
public class SlotTest {
    public void localVar1(){
        int a=0;
        System.out.println(a);
        int b=0;
    }

    public void localVar2(){
        {
            int a = 0;
            System.out.println(a);
        }
        // 此时b会复用a的槽位
        int b=0;
    }
}
