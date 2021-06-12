package com.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 MinorGC,MajorGC,FullGC
 * -Xms9m -Xmx9m -XX:+PrintGCDetails
 */
public class GcLog {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "a";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }

        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为：" + i);
        }
    }
}
