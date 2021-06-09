package com.jvm.hashCode;

/**
 * identityHashCode返回根据对象物理内存地址产生的hash值,native方法
 */
public class TestIdentityHashCode {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o.hashCode());                // 2133927002
        System.out.println(System.identityHashCode(o));  // 2133927002

        System.out.println("-------------------------");

        T t = new T();
        System.out.println(t.hashCode());                                            // 1
        System.out.println(t.superHashCode());                                       // 1836019240
        System.out.println(System.identityHashCode(t));                              // 1836019240
        System.out.println(t.superHashCode()==System.identityHashCode(t));           // true

        System.out.println("-------------------------");

        String s1 = new String("Hello");
        String s2 = new String("Hello");
        System.out.println(s1.hashCode() == s2.hashCode()); // String类重写了hashCode方法,故为true
        System.out.println(System.identityHashCode(s1) == System.identityHashCode(s2));  // 物理地址不一样 false

    }

    private static final class T {
        @Override
        public int hashCode() {
            return 1;
        }

        public int superHashCode() {
            return super.hashCode();
        }
    }
}
