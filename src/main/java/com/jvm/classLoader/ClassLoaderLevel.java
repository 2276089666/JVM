package com.jvm.classLoader;

public class ClassLoaderLevel {
    public static void main(String[] args) {
        // BootStrapClassLoader是C++写的,java类库没有与之对应的类,返回为null
        System.out.println(String.class.getClassLoader());           // null
        System.out.println(sun.awt.HKSCS.class.getClassLoader());    //null
        // ExtClassLoader
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        // AppClassLoader
        System.out.println(ClassLoaderLevel.class.getClassLoader());

        // ExtClassLoader是被BootStrapClassLoader加载的
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        // AppClassLoader是被BootStrapClassLoader加载的
        System.out.println(ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());

        // 自定义ClassLoader的父加载器AppClassLoader
        System.out.println(new MSBClassLoader().getParent());
        // 系统加载器是AppClassLoader
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
