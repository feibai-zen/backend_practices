package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.*;

public class CompletableFutureAPI_processTheResult_Sequence {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenRun(() -> {
        }).join());

        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenAccept(System.out::println).join());

        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenApply(r -> {
            return r + "ResultB";
        }).join());

        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenApply(r -> {
            return r + "ResultB";
        }).join());

        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenApplyAsync(r -> {
            return r + "ResultB";
        }, pool).join());
    }
}