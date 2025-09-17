package com.feibai.study.demos.multithread.container_tools;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器 - LinkedBlockingQueue
 * <p>
 * 阻塞容器。
 * <p>
 * put & take - 自动阻塞。
 * <p>
 * put自动阻塞， 队列容量满后，自动阻塞
 * <p>
 * take自动阻塞方法， 队列容量为0后，自动阻塞。
 */
public class Test_04_LinkedBlockingQueue {

    final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    final Random r = new Random();

    public static void main(String[] args) {
        final Test_04_LinkedBlockingQueue t = new Test_04_LinkedBlockingQueue();

        new Thread(() -> {
            while (true) {
                try {
                    t.queue.put("value" + t.r.nextInt(1000));
                    TimeUnit.SECONDS.sleep(1);// 每隔1秒向容器中添加元素
                } catch (InterruptedException e) {
                }
            }
        }, "producer").start();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
                    } catch (InterruptedException e) {
                    }
                }
            }, "consumer" + i).start();
        }
    }

}
