package com.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ws
 * @Date 2021/6/8 22:32
 */
// -Xms20m -Xmx20m

/**
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class StringConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        long i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
