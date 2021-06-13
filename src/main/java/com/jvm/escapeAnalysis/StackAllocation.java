package com.jvm.escapeAnalysis;

/**
 * 栈上分配测试
 * -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        StackAllocation stackAllocation = new StackAllocation();
        for (int i = 0; i < 10000000; i++) {
            stackAllocation.useUser2();
//            stackAllocation.useUser1(stackAllocation.alloc2());
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    class User {
        int a = 4;
    }

    private static User user;

    //未发生逃逸   栈:4ms  堆:80ms
    public void alloc1() {
        User user = new User();
    }


    /**
     * 对象作为方法返回,但是没有赋值给成员变量,所以为方法逃逸
     *
     * @return
     */
    //逃逸出方法,但是未发生线程逃逸  栈:5ms   堆:76ms
    public User alloc2() {
        return new User();
    }

    // 使用alloc2()发生方法逃逸,但是未发生线程逃逸   栈:6ms  堆:77ms
    public void useUser1(User user) {
        user.a=12;
    }

    /**
     * 为成员属性赋值，发生线程逃逸
     */
    // 线程逃逸    栈: 无法分配   堆:76ms
    public void set() {
        user = new User();
    }
    // 线程逃逸    栈: 无法分配    堆:77ms
    public void useUser2() {
        user = alloc2();
    }

}
