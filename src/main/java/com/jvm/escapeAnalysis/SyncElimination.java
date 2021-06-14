package com.jvm.escapeAnalysis;

/**
 * @Author ws
 * @Date 2021/6/13 13:54
 */

/**
 * -XX:+EliminateLocks
 */
// 花费的时间都差不多,只是前面一个因为预热问题耗时长一点
public class SyncElimination {
    // 默认使用线程不安全的StringBuilder
    public static String concatString(String s1, String s2, String s3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s1);  // append无同步
        stringBuilder.append(s2);
        stringBuilder.append(s3);
        return stringBuilder.toString();
    }

    public static String concatString2(String s1, String s2, String s3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s1);  // append有同步
        stringBuffer.append(s2);
        stringBuffer.append(s3);
        return stringBuffer.toString();  // 会新创建一个String对象,避免方法逃逸,会有同步消除
    }


    public static void main(String[] args) {
        SyncElimination sy = new SyncElimination();

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sy.concatString(String.valueOf(i - 1), String.valueOf(i), String.valueOf(i + 1));
        }
        System.out.println("无同步消耗时间" + (System.currentTimeMillis() - start1));


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sy.concatString2(String.valueOf(i - 1), String.valueOf(i), String.valueOf(i + 1));
        }
        System.out.println("同步消除消耗时间" + (System.currentTimeMillis() - start2));

    }
}
