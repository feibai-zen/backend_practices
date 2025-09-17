package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPI_processTheResult_thenCombine {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<Integer> taskA = CompletableFuture.supplyAsync(()->{
            System.out.println("taskA come in ==========");
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return 10;
        });

        CompletableFuture<Integer> taskB = CompletableFuture.supplyAsync(()->{
            System.out.println("taskB come in ==========");
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return 20;
        });

        CompletableFuture<Integer> result = taskA.thenCombine(taskB, (res1,res2)->{
            return res1 + res2;
        });

        System.out.println(Thread.currentThread().getName() + "\t" + result.join());

    }
}