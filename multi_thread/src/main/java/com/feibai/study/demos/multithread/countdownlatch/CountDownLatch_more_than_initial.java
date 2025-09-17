package com.feibai.study.demos.multithread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_more_than_initial {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(10);

        for (int i = 0; i < 20; i++) {
            cdl.countDown();
            System.out.println("final count is: " + cdl.getCount());
        }
    }
}
