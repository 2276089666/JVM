package com.jvm.exam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ws
 * @Date 2021/6/18 20:39
 */

/**
 * -Xms200M -Xmx200M -XX:+PrintGC
 */
public class FGCProblem {

    //从数据库中读取信用数据，套用模型，并把结果进行记录和传输

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        for (; ; ) {
            modelFit();
            Thread.sleep(100);
        }
    }


    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());


    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info -> {
            // do something
            executor.scheduleWithFixedDelay(() -> {
                //do sth with info
                info.m();

            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }

        return taskList;
    }

    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthDate = new Date();

        public void m() {
        }
    }

}
