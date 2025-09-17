package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.*;

public class CompletableFutureAPI_processTheResult_Handle {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1;
        }, pool).handle((f, e) -> {
            int i = 10 / 0;
            System.out.println("222");
            return f + 1;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 1;
        }).whenComplete((v, e) -> {
            System.out.println("when complete");
            if (null == e) {
                System.out.println("the final result is: " + v);
            }
            System.out.println("when complete. the f is: "+ v);
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("exist exception, the reason is: " + e.getMessage());
            return null;
        });

        System.out.println("the main thread to do other things.....");
//        TimeUnit.SECONDS.sleep(5);

        pool.shutdown(); // 线程执行完成之后，线程池才会被关闭
    }
}