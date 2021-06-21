package com.jvm.lazyLoading;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Scanner;

/**
 * 严格讲应该叫lazy initialzing，因为java虚拟机规范并没有严格规定什么时候必须loading,但严格规定了什么时候initialzing
 */
public class LazyLoading {
    // 执行main之前,先初始化包含main方法的主类
    static {
        System.out.println("init main class");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入:");
        switch (input.nextInt()) {
            case 1:
                // new
                new Parent();
                break;
            case 2:
                // getstatic
                System.out.println(Parent.j);
                break;
            case 3:
                // 子类使用父类静态字段,只初始化父类
                System.out.println(Child.j);
                break;
            case 4:
                // getstatic(final除外)
                System.out.println(Parent.i);
                break;
            case 5:
                // putstatic
                Parent.j = 10;
                break;
            case 6:
                // invokestatic
                Parent.m();
                break;
            case 7:
                // 反射
                Class.forName("com.jvm.lazyLoading.LazyLoading$Parent");
                break;
            case 8:
                // 子类初始化时要先初始化父类
                new Child();
                break;
            case 9:
                // 实现类实现了带有default方法的接口,实现类初始化之前,接口先初始化
                new Parent();
                break;
            case 10:
                // 通过数组定义来引用类,不会触发类的初始化
                Parent[] parents = new Parent[10];
                break;
            case 11:
                // MethodHandle无法验证,使用过程中new Person()了,好烦┭┮﹏┭┮
                runMethodHandle();
                break;
            default:
                break;
        }
    }

    public static class Parent implements A {
        final static int i = 8;
        static int j = 9;

        static {
            System.out.println("init Parent");
        }

        public static void m() {

        }

        public String m2(String str) {
            return str + j;
        }

    }

    public static class Child extends Parent {
        static {
            System.out.println("init child");
        }
    }

    public interface A {
        static final TestA t = new TestA();

        default void a() {
        }
    }

    public static class TestA {
        static {
            System.out.println("init interface");
        }
    }

    public static void runMethodHandle() {
        //获取方法类型 参数为:1.返回值类型,2方法中参数类型
        MethodType mt = MethodType.methodType(String.class, String.class);
        try {
            // 获取方法句柄
            MethodHandle mh = MethodHandles.lookup().findVirtual(Parent.class, "m2", mt);
            // 调用方法
            String ret = (String) mh.invokeExact(new Parent(), "m2 str");
            System.out.println(ret);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
