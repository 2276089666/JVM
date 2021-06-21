package com.jvm.hotLoading;

import com.jvm.classLoader.MyClassLoader;

public class ClassReloading1 {
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.loadClass("com.jvm.classLoader.Test");

        // 重新加载我们的类,由于双亲委派的find in cache,加载过的不会再次加载,无法实现热替换热部署
        myClassLoader = new MyClassLoader();
        Class clazz1 = myClassLoader.loadClass("com.jvm.classLoader.Test");

        System.out.println(clazz == clazz1);
    }
}
