package com.feibai.study.demos.multithread.advanced.t02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * feibai
 *
 * <p>
 * 自定义容器，提供新增元素（add）和获取元素数量（size）方法。
 * 启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，当容器元素数量为5时，线程2输出信息并终止。
 */
public class HomeWorkTest2<E> {

    private volatile List<E> list = new ArrayList();

    public synchronized void getSize() throws InterruptedException {
        while (list.size() < 5) {
            this.wait();
        }
        this.notify();
        System.out.println("getSize.....");
    }

    public synchronized void add(E element) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("add ........." + i);
            list.add(element);
            if (list.size() == 5) {
                this.notifyAll();
                this.wait();// 这里如果不调用wait()方法，线程就一直不会释放锁
            }
        }
    }

    public static void main(String[] args) {
        HomeWorkTest2<Integer> test = new HomeWorkTest2();
        new Thread(() -> {
            try {
                test.add(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                test.getSize();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
