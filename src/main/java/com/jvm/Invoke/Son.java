package com.jvm.Invoke;

/**
 * 解析调用中非虚方法、虚方法的测试
 * <p>
 * invokestatic指令和invokespecial指令调用的方法称为非虚方法
 *
 */
class Father {
    public Father() {
    }

    public static void showStatic() {
    }

    public final void showFinal() {
    }

    public void showCommon() {
    }

}

interface MethodInterface {
    void methodA();
}


public class Son extends Father {
    public Son() {
        super();
    }

    public Son(int age) {
        this();
    }

    public static void showStatic() {
    }

    private void showPrivate() {
    }

    public void info() {

    }

    @Override
    public void showCommon() {

    }

    public void show() {
        super.showStatic();

        showStatic();

        super.showFinal();

        showFinal();

        super.showCommon(); // 使用super,没有多态

        showCommon();

        showPrivate();

        info();  // 有可能被子类重写,所以还是invokevirtual

        MethodInterface in = null;
        in.methodA();
    }

}

