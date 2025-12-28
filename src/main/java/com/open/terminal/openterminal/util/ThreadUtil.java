package com.open.terminal.openterminal.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/12/28
 * @Copyright： https://github.com/DukeLewis
 */
public class ThreadUtil {
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(ThreadUtil.class);


    /**
     * 使用信号量控制虚拟线程并发量
     */
    private static final Semaphore uiSemaphore = new Semaphore(1);
    /**
     * 创建虚拟线程池
     */
    private static final ExecutorService virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();

    public static void submitTask(Runnable task) {
        virtualThreadPerTaskExecutor.submit(task);
    }


    public static void stopVirtualExecutorService() {
        if (!virtualThreadPerTaskExecutor.isShutdown()) {
            virtualThreadPerTaskExecutor.shutdown();
            try {
                virtualThreadPerTaskExecutor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.info("线程池终中断异常： {}", e);
            }
        }
    }
}
