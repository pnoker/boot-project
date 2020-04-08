package com.pnoker.service.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pnoker
 */
@Slf4j
@Component
public class ThreadPool {
    private static int CORE_POOL_SIZE = 4;
    private static int MAX_POOL_SIZE = 32;
    private static int KEEP_ALIVE_TIME = 10;
    private static int QUEUE_CAPACITY = 4096;

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 线程池
     */
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(QUEUE_CAPACITY),
            r -> {
                Thread thread = new Thread(r, "emulator-thread-" + atomicInteger.getAndIncrement());
                log.debug("{} has been created", thread.getName());
                return thread;
            }, (r, e) -> log.error("thread pool rejected"));

    /**
     * 在线程池中执行线程
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        poolExecutor.execute(runnable);
    }
}
