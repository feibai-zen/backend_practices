package com.feibai.study.demos.multithread.volatile_test;

public class Volatile_test03 {
    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

//        TimeUnit.SECONDS.sleep(6);
        num = 1;
    }
}
