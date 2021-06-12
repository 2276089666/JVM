package com.jvm.memorySize;

import java.io.IOException;

/**
 * -Xms600m -Xmx600m
 *
 * -XX:NewRatio=2  设置新生代与老年代的比例。默认值是2.  利用 jinfo -flag NewRatio pid 查看
 * -XX:SurvivorRatio=8 ：设置新生代中Eden区与Survivor区的比例。默认值是8
 * -XX:+UseAdaptiveSizePolicy ：自适应的内存分配策略,默认开启
 * -Xmn:设置新生代的空间的大小。 （一般不设置,使用-XX:NewRatio=2）
 *
 */
public class EdenSurvivorTest {
    public static void main(String[] args) throws IOException {
        System.out.println("~~~~~~~~~~~~~~~~~");
        System.in.read();
    }
}
