package com.feibai.study.demos.multithread.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 自定义容器，提供新增元素（add）和获取元素数量（size）方法。
 * 启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，当容器元素数量为5时，线程2输出信息并终止。
 */
public class CountDownLatch_test02<E> {

    private volatile List<E> list = new ArrayList();
    CountDownLatch cdl = new CountDownLatch(5);

    public int getSize() throws InterruptedException {
        cdl.await();
        System.out.println("getSize.....");
        return list.size();
    }

    public void add(E element) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("add ........." + (i + 1));
            list.add(element);
            cdl.countDown();
        }
    }

    public static void main(String[] args) {
        CountDownLatch_test02<Integer> test = new CountDownLatch_test02();

        new Thread(() -> {
            try {
                test.add(10);
            } catch (InterruptedException ignored) {
            }
        }).start();

        new Thread(() -> {
            try {
                test.getSize();
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

}
