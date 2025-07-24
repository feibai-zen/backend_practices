package com.feibai.study.demos.multithread.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * Thread 参数只接受 Runnable 接口
 * Runnable 接口没有返回值
 * <p>
 * 所以包装了 FutureTask
 */
public class FutureTask_test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futuretask = new FutureTask<String>(new MyThread());
        Thread t1 = new Thread(futuretask, "t1");
        t1.start();

        System.out.printf(futuretask.get());
    }

}

class MyThread implements Callable {

    @Override
    public String call() throws Exception {
        System.out.println("-----come in call()");
        return "hello callable";
    }
}