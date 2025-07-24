package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.*;

public class CompletableFuture_selfdefine_threadpool {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("--------come in the subtask-------");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return ThreadLocalRandom.current().nextInt(20);
            }, pool).whenComplete((v, e) -> {
                if (null == e) {
                    System.out.println("get the subtask result, the result is: " + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("come in the exceptional case....");
                return null;
            });

        } catch (Exception e) {

        } finally {
            pool.shutdown();
        }


        System.out.println("the main thread to do other task....");
    }

}
