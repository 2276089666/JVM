package com.jvm.heap;

/**
 * 1. 设置堆空间大小的参数
 * -Xms 用来设置堆空间（年轻代+老年代）的初始内存大小
 * -X 是jvm的运行参数
 * ms 是memory start
 * -Xmx 用来设置堆空间（年轻代+老年代）的最大内存大小
 * <p>
 * 2. 默认堆空间的大小
 * 初始内存大小：物理电脑内存大小 / 64
 * 最大内存大小：物理电脑内存大小 / 4
 * 3. 手动设置：-Xms600m -Xmx600m
 * 开发中建议将初始堆内存和最大的堆内存设置成相同的值。
 * <p>
 * 4. 查看设置的参数：方式一： jps   /  jstat -gc 进程id
 * 方式二：-XX:+PrintGCDetails
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {

        //返回Java虚拟机中的堆内存总量(少一个Survivor区的容量10752/1024 G)
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");

        System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");

    }
}
