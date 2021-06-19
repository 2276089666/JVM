package com.jvm.exam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author ws
 * @Date 2021/6/19 10:21
 */
public class MyRejectedPolicy implements RejectedExecutionHandler {
    LinkedBlockingDeque<Runnable> list = new LinkedBlockingDeque<>();

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (list.size() != Integer.MAX_VALUE) {
            list.add(r);
        } else {
            FileOutputStream fo = null;
            ObjectOutputStream os = null;
            try {
                File file = new File("task");
                fo = new FileOutputStream(file);
                os = new ObjectOutputStream(fo);
                os.writeObject(list);
                // 清空队列，将任务重新放进来
                list.clear();
                list.add(r);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    os.close();
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
