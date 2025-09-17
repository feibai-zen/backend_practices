package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPI_getResultAndTriggerCalculate {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "abc";
        });


        TimeUnit.SECONDS.sleep(1);
        //System.out.println(future.get());  //不见不散
        //System.out.println(future.get(2L, TimeUnit.MINUTES)); // 过时不候
        //System.out.println(future.join()); // 不见不散，不抛出受检异常
        //System.out.println(future.getNow("bcd"));

        /** 如果异步线程执行完成，则使用计算完成的值，complete() 返回false
         *  如果没有计算完成，则使用提供的“other value”作为结果，complete()返回true
         */
        System.out.println(future.complete("other value") + "\t" + future.join());
    }
}