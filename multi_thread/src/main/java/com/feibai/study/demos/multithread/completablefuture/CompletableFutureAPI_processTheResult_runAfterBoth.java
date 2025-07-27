package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPI_processTheResult_runAfterBoth {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());

            try {Thread.sleep(2000);} catch (InterruptedException e) {}

            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());

            try {Thread.sleep(1500);} catch (InterruptedException e) {}

            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });

        //cf1和cf2都执行完成后，执行cf3，无入参，无返回值
        CompletableFuture<Void> cf3 = cf2.runAfterBoth(cf1, () -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());

            try {Thread.sleep(1000);} catch (InterruptedException e) {}

            System.out.println("cf3 do something");
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
        });

        System.out.println("no return value for cf3: " + cf3.join());
    }

}