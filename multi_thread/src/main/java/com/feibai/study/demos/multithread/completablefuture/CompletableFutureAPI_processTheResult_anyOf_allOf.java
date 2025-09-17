package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPI_processTheResult_anyOf_allOf {

    /**
     * anyOf / allOf
     * <p>
     * allOf 返回的 CompletableFuture 是多个任务都执行完成后才会执行，只要有一个任务执行异常，则返回的 CompletableFuture 执行 get方法时
     * 会抛出异常，如果都是正常执行，则 get 返回 null
     *
     * <p>
     * anyOf返回的CompletableFuture是多个任务只要其中一个执行完成就会执行，其get返回的是已经执行完成的任务的执行结果，如果该任务执行异
     * 常，则抛出异常。
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            return 1.2;
        });

        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }

            return 3.2;
        });

        CompletableFuture<Double> cf3 = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
            }
            // throw new RuntimeException("test");
            return 2.2;
        });

        // allof 等待所有任务执行完成才执行 cf4，如果有一个任务异常终止，则cf4.get时会抛出异常。
        // 都是正常执行，cf4.get返回null
        CompletableFuture<Void> cf4 = CompletableFuture.allOf(cf1, cf2, cf3).whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("error stack trace->");
                ex.printStackTrace();
            } else {
                System.out.println("run succ, result->" + result);
            }
        });

        // anyOf 是只有一个任务执行完成，无论是正常执行或者执行异常，都会执行cf5，cf5.get 的结果就是已执行完成的任务的执行结果
        CompletableFuture<Object> cf5 = CompletableFuture.anyOf(cf1, cf2, cf3).whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("error stack trace->");
                ex.printStackTrace();
            } else {
                System.out.println("run succ, result->" + result);
            }
        });

        System.out.println("cf4 run result->" + cf4.get());
        System.out.println("cf5 run result->" + cf5.get());
    }

}