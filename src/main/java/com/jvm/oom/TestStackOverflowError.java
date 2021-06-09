package com.jvm.oom;

/**
 * @Author ws
 * @Date 2021/6/8 20:29
 */

// -Xss128k   栈内存容量
/**
 * 报错为:stack length :	988
 *       java.lang.StackOverflowError
 */
public class TestStackOverflowError {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        TestStackOverflowError o = new TestStackOverflowError();
        try {
            o.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length :\t" + o.stackLength);
            e.printStackTrace();
        }
    }
}
