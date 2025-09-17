package com.feibai.study.demos.multithread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 门闩 - CountDownLatch
 * <p>
 * 可以和锁混合使用，或替代锁的功能。
 * <p>
 * 在门闩未完全开放之前等待。当门闩完全开放后执行。
 * <p>
 * 避免锁的效率低下问题。
 */
public class CountDownLatch_test01 {
    CountDownLatch latch = new CountDownLatch(5); // 在门上加5把锁

    void m1() {
        try {
            latch.await();// 等待门闩开放
        } catch (InterruptedException ignored) {
        }
        System.out.println("m1() method");
    }

    void m2() {
        for (int i = 0; i < 10; i++) {
            if (latch.getCount() != 0) {
                System.out.println("latch count : " + latch.getCount());
                latch.countDown(); // 减门闩上的锁
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ignored) {
            }
            System.out.println("m2() method : " + i);
        }
    }

    public static void main(String[] args) {
        final CountDownLatch_test01 t = new CountDownLatch_test01();
        new Thread(() -> {
            t.m1();
        }).start();

        new Thread(() -> {
            t.m2();
        }).start();
    }
}
