package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPI_processTheResult_thenAccepBoth {

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

        //cf1和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,无返回值
        CompletableFuture<Void> cf3 = cf1.thenAcceptBoth(cf2, (a, b) -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            System.out.println("job4 param a->" + a + ",b->" + b);

            try {Thread.sleep(1500);} catch (InterruptedException e) {}

            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
        });
        System.out.println("no return value for cf3: " + cf3.join());
    }

}