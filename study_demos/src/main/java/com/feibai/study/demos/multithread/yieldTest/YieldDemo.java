package com.feibai.study.demos.multithread.yieldTest;


/**
 * 线程 yield() 是直接进入就绪状态，不是进入阻塞状态。
 */
public class YieldDemo implements Runnable {

    public static void main(String[] args) {
        YieldDemo yieldDemo = new YieldDemo();
        Thread t = new Thread(yieldDemo);
        t.start();

        // 主綫程每隔20次会礼让一次
        for (int i = 0; i < 1000; i++) {
            if (i % 20 == 0) {
                Thread.yield();
            }
            System.out.println("main...." + i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("yield...." + i);
        }

    }
}
