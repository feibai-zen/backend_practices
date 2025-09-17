package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.*;

public class CompletableFutureAPI_processTheResult_thenAccept {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            return 1;
        }, pool).thenApply(r -> {
            return r + 1;
        }).thenApply(r -> {
            return r + 2;
        }).thenAccept(r -> {
            System.out.println("===============ThenAccept============" + r);
        });

        System.out.println("the main thread to do other things.....");

        pool.shutdown(); // 线程执行完成之后，线程池才会被关闭
    }
}