package com.feibai.study.demos.multithread.completablefuture;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 电商询价 3 种方式
 * 1. 同步的迭代去查询
 * 2. 使用 parallelStream 方式异步查询
 * 3. 使用 CompletableFuture 方式异步查询
 */
public class CompletableFutureMallDemo {

    static List<NetMall> mallList = Arrays.asList(new NetMall("jd"), new NetMall("dangdang"), new NetMall("taobao"));

    public static void main(String[] args) {
        getPriceWithNormal(mallList, "MySQL");
        System.out.println("===========================================");
        getPriceWithParallel(mallList, "MySQL");
        System.out.println("===========================================");
        getPriceWithCompletableFuture(mallList, "MySQL");
    }

    public static void getPriceWithNormal(List<NetMall> list, String productName) {
        long start = System.currentTimeMillis();

        System.out.println(list.stream()
                .map(netMall -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName)))
                .collect(Collectors.toList()));

        long end = System.currentTimeMillis();
        System.out.println("total cost with normal is: " + (end - start) + "ms");
    }

    public static void getPriceWithParallel(List<NetMall> list, String productName) {
        long start = System.currentTimeMillis();

        System.out.println(
                list.parallelStream()
                        .map(netMall ->
                                String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))
                        )
                        .collect(Collectors.toList())
        );

        long end = System.currentTimeMillis();
        System.out.println("total cost with parallel is: " + (end - start) + "ms");
    }

    public static void getPriceWithCompletableFuture(List<NetMall> list, String productName) {
        long start = System.currentTimeMillis();

        System.out.println(
                list.stream()
                        .map(netMall -> CompletableFuture.supplyAsync(() ->
                                String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))
                        ))
                        .collect(Collectors.toList())
                        .stream()
                        .map(future -> future.join())
                        .collect(Collectors.toList())
        );

        long end = System.currentTimeMillis();
        System.out.println("total cost with CompletableFuture is: " + (end - start) + "ms");
    }

}

class NetMall {

    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}