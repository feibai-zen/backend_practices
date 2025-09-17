package com.feibai.study.demos.multithread.interrupt_thread;

import java.util.concurrent.TimeUnit;

public class Interrupted_sleep_test {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);

                while (true) {
                    System.out.println("running t1 thread...");
                }
            } catch (InterruptedException e) {
                System.out.println("catched interrupted exception");
            }

        });
        t1.start();

        TimeUnit.SECONDS.sleep(10);

        t1.interrupt();
    }

}
