package com.feibai.study.demos.multithread.terminated;

/**
 * 通过设置标志位终止线程
 *
 * @author feibai
 */
public class TerminatedTread implements Runnable {
    // 这里需要加volatile吗？ 我的理解是不需要加的，因为这里的flag 是Thread 类属性，主线程通过调用 terminateThread()
    // 方法设置这个标志，线程可以立刻获得最新的值。如果该变量是线程间共享的变量，则需要加volatile 来保证可见性
    // 为了保证可靠性，可以使用AtomicBoolean
    private boolean flag = true;

    public static void main(String[] args) {
        TerminatedTread thread = new TerminatedTread();
        new Thread(thread).start();
        try {
            Thread.sleep(5000);
            thread.terminateThread();
            Thread.activeCount();//存活线程数量
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(200);
                System.out.println("Thread is running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread is terminated...");
    }

    public void terminateThread() {
        flag = false;
    }

}
