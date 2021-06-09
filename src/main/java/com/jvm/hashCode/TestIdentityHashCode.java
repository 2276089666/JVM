package com.jvm.hashCode;

/**
 * identityHashCode返回对象的hashcode,子类重写了没用,native方法
 */
public class TestIdentityHashCode {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o.hashCode());
        System.out.println(System.identityHashCode(o));

        System.out.println("-------------------------");

        T t = new T();
        System.out.println(t.hashCode());
        System.out.println(t.superHashCode());
        System.out.println(System.identityHashCode(t));
        System.out.println(t.superHashCode()==System.identityHashCode(t)); //true

        System.out.println("-------------------------");

        String s1 = new String("Hello");
        String s2 = new String("Hello");
        System.out.println(s1.hashCode() == s2.hashCode()); // String类重写了hashCode方法,故为true
        System.out.println(System.identityHashCode(s1) == System.identityHashCode(s2));  // false

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
