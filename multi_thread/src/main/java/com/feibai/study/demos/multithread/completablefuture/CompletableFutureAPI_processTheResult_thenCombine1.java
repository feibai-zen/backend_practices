package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPI_processTheResult_thenCombine1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<Integer> thenCombineResult = CompletableFuture.supplyAsync(() -> {
            System.out.println("=========come in step 1==========");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("=========come in step 2==========");
            return 20;
        }), (a, b) -> {
            System.out.println("=========come in step 3==========");
            return a + b;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("=========come in step 4==========");
            return 30;
        }), (a, b) -> {
            System.out.println("=========come in step 5==========");
            return a + b;
        });

        System.out.println(Thread.currentThread().getName() + "\t" + thenCombineResult.join());
    }
}