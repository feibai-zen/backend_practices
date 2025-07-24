package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.*;

public class FutureThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService pool = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();

        FutureTask<String> task1 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(30000);
            return "task1 success...";
        });

        FutureTask<String> task2 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(400);

            return "task2 success";
        });
        pool.submit(task1);
        pool.submit(task2);

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("task3 success");
        task1.get(10, TimeUnit.SECONDS);
        task2.get();

        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("total cost is: " + cost);

        pool.shutdown();
    }
}
