package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPI_processTheResult_thenCompose {

    /**
     * 用来做任务串连
     *
     * thenCompose 会在某个任务执行完成后，将该任务的执行结果作为方法入参然后执行指定的方法，该方法会返回一个新的 CompletableFuture
     * 对象，如果该 CompletableFuture 对象的 result 不为 null，则返回一个基于该 result 的新的 CompletableFuture 实例；如果该CompletableFuture
     * 对象为null，则执行这个新任务
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());

            try {Thread.sleep(2000);} catch (InterruptedException e) {}

            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });

        CompletableFuture<String> cf2 = cf1.thenCompose((param) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());

            try {Thread.sleep(2000);} catch (InterruptedException e) {}

            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());

            return CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());

                try {Thread.sleep(2000);} catch (InterruptedException e) {}

                System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
                return "job3 test " + param;
            });
        });

        System.out.println("============main thread start============");

        //等待子任务执行完成
        System.out.println("cf1 run result->" + cf1.get());
        System.out.println("cf2 run result->" + cf2.get());


    }
}