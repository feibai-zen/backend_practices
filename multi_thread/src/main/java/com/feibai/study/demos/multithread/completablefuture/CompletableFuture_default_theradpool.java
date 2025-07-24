package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 如果使用了默认的线程池，要注意，在主线程结束之后，默认创建的线程池就会被关闭，从而异步业务线程也会跟着结束。
 */
public class CompletableFuture_default_theradpool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("----come in the subtask----");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return result;
        }).whenComplete((v, e) -> {
            if (null == e) {
                System.out.println("the result of the subtask is: " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

        System.out.println("the main process to do other things");

        // 主线程不要立刻结束，否则 CompletableFuture 默认使用的线程池会立刻关闭：暂停5秒钟
        TimeUnit.SECONDS.sleep(5);

        // likeFuture(); // CompletableFuture 可以替换 Future 的功能
    }

    // CompletableFuture 可以代替Future, 提供了 Future 所有功能
    private static void likeFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("----come in the subtask----");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return result;
        });

        System.out.println("the main process to do other things");
        System.out.println("get the subtask result: " + future.get());

    }
}
