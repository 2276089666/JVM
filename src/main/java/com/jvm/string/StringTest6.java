package com.jvm.string;


/**
 * @Author ws
 * @Date 2021/6/16 11:02
 */
public class StringTest6 {


    public static void test1() {
        String a = "aaa";
        String b = new String("aaa").intern();
        String c = new String("aaa");
        System.out.println(a == b); // true
        System.out.println(a == c); // false
        System.out.println(b == c); // false
    }
    public static void test2() {
        String d = new String("1") + new String("2"); // 堆中创建对象，不会将“11”添加到字符串常量池中
        d.intern();                                                  // 将“11”添加到字符串常量池中，常量池中的引用为堆中的”12“
        String e = "12";                   // hash命中
        System.out.println(d == e);  // true
        String f = d.intern();
        System.out.println(f == e);  // true
    }
    public static void test3() {
        String d = new String("3") + new String("4"); // 堆中创建对象，不会将“11”添加到字符串常量池中
        String e = "34"; // hash没命中，字符串常量池中没有“11”，字符串常量池中创建对象“11“
        d.intern();
        System.out.println(d == e);  // false
        String f = d.intern();
        System.out.println(f == e);  // true
    }
    public static void test4() {
        String d = new String("56");  // 堆中创建对象，在字符串常量池中创建一个对象”11“，返回堆中对象引用
//        d.intern();   // 无效
        String e = "56";                   // hash命中，返回字符串常量池中的对象
        System.out.println(d == e);  // false
        String f = d.intern();
        System.out.println(f == e);  // true
    }

    public static void main(String[] args) {
        test1();
        System.out.println();
        test2();
        System.out.println();
        test3();
        System.out.println();
        test4();
    }
}
