package com.feibai.study.demos.multithread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Test05_ReentrantLock_fair {

    public static void main(String[] args) {
        ReentrantLock_test test = new ReentrantLock_test();

        Thread t1 = new Thread(() -> {
            test.method();
        });
        Thread t2 = new Thread(() -> {
            test.method();
        });

        t1.start();
        t2.start();
    }
}

class ReentrantLock_test {
    private ReentrantLock lock = new ReentrantLock(true);

    public void method() {

        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "------" + i);
            } finally {
                lock.unlock();
            }
        }
    }
}
