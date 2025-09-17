package com.feibai.study.demos.multithread.threadlocal;

/**
 * @author feibai
 */
public class ThreadLocal_demo03 {
    private static ThreadLocal<Integer> threadlocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyRun()).start();
        }
    }

    public static class MyRun implements Runnable {
        public void run() {
            Integer left = threadlocal.get();
            System.out.println(Thread.currentThread().getName() + "得到了-->" + left);
            threadlocal.set(left - 1);
            System.out.println(Thread.currentThread().getName() + "还剩下-->" + threadlocal.get());
        }
    }

}