package com.feibai.study.demos.multithread.threadlocal;

/**
 * ThreadLocal
 * <p>
 * 作用：多线程环境为每个线程保存变量
 * 就是一个Map
 * key ->Thread.getCurrentThread() value ->线程需要保存的变量。
 * ThreadLocal.set(value) -> map.put(Thread.getCurrentThread(), value);
 * ThreadLocal.get() -> map.get(Thread.getCurrentThread());
 * <p>
 * 内存问题: 在并发量高的时候，可能有内存溢出。
 * 使用ThreadLocal的时候，一定注意回收资源问题，每个线程结束之前，将当前线程保存的线程变量一定要删除 。
 * ThreadLocal.remove();
 * <p>
 * 补充知识点：
 * 在操作系统中，线程和进程有数量上限，确认线程和进程唯一性的唯一条件就是线程ID或进程ID。
 * 操作系统在回收线程或进程的时候，不是一定杀死进程或者线程，在繁忙的时候，只会做清空线程或进程的操作，重复使用线程或进程
 */

import java.util.concurrent.TimeUnit;

public class ThreadLocal_demo01 {

    private volatile static String name = "zhangsan";
    private static final ThreadLocal<String> threadlocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignored) {
            }
            threadlocal.set(name);
            System.out.println(threadlocal.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
            name = "lisi";
            threadlocal.set("wangwu");
            System.out.println(threadlocal.get());
        }).start();

        System.out.println("------------");
        System.out.println(name);
    }

}
