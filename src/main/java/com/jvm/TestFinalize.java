package com.jvm;

/**
 * @Author ws
 * @Date 2021/6/9 15:14
 */

/**
 * 1.对象可以被在GC的时候自我拯救
 * 2.机会只有一次,finalize系统只调用一次
 */
public class TestFinalize {
    // 保命钩子
    public static TestFinalize saveHook=null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed !");
        TestFinalize.saveHook=this;
    }

    public static void main(String[] args) throws InterruptedException {
        saveHook = new TestFinalize();

        saveHook=null;
        System.gc();
        Thread.sleep(1000);
        if (saveHook!=null){
            System.out.println("o is alive !!");
        }else {
            System.out.println("o is dead .");
        }

        saveHook=null;
        System.gc();
        Thread.sleep(500);
        if (saveHook!=null){
            System.out.println("o is alive !!");
        }else {
            System.out.println("o is dead .");
        }
    }
}
