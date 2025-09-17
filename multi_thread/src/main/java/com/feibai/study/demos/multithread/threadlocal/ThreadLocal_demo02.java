package com.feibai.study.demos.multithread.threadlocal;

/**
 * ThreadLocal:每个线程自身的存储本地、局部区域 get/set/initialValue
 *
 * @author feibai
 */
public class ThreadLocal_demo02 {

    private static ThreadLocal<Integer> threadlocal = ThreadLocal.withInitial(() -> 200);

    public static void main(String[] args) {
        // 获取值
        System.out.println(Thread.currentThread().getName() + "-->" + threadlocal.get());
        // 设置值
        threadlocal.set(99);
        System.out.println(Thread.currentThread().getName() + "-->" + threadlocal.get());

        new Thread(new MyRun()).start();
        new Thread(new MyRun()).start();
    }

    public static class MyRun implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-->" + threadlocal.get());
            threadlocal.set((int) (Math.random() * 99));
            System.out.println(Thread.currentThread().getName() + "-->" + threadlocal.get());
        }
    }

}
