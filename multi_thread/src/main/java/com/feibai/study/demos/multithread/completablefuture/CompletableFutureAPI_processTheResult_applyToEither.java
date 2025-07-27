package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.*;

public class CompletableFutureAPI_processTheResult_applyToEither {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {
            System.out.println("playerA come in ==========");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "playerA";
        });

        CompletableFuture<String> playerB = CompletableFuture.supplyAsync(() -> {
            System.out.println("playerB come in ==========");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "playerB";
        });

        CompletableFuture<String> result = playerA.applyToEither(playerB, res -> {
            return res + " is winner!!!";
        }).exceptionally(e -> {
            System.out.println("come in the exceptionally....");
            e.printStackTrace();
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "\t" + result.join());

    }
}