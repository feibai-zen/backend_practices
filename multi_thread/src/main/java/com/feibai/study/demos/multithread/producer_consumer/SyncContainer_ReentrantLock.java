package com.feibai.study.demos.multithread.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义同步容器，容器容量上限为10。可以在多线程中应用，并保证数据线程安全。
 * <p>
 * 生产者消费者 重入锁 & 条件
 * 条件 - Condition， 为 Lock 增加条件。当条件满足时，做什么事情，如加锁或解锁。如等待或唤醒
 */
public class SyncContainer_ReentrantLock<E> {

    private final LinkedList<E> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(E e) {
        lock.lock();
        try {
            while (list.size() == MAX) {
                System.out.println(Thread.currentThread().getName() + " 等待。。。");
                // 进入等待队列。释放锁标记。
                // 借助条件，进入的等待队列。
                producer.await();
            }
            System.out.println(Thread.currentThread().getName() + " put 。。。");
            list.add(e);
            this.count++;
            // 借助条件，唤醒所有的消费者。
            consumer.signalAll();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E get() {
        E e = null;

        lock.lock();
        try {
            while (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + " 等待。。。");
                // 借助条件，消费者进入等待队列
                consumer.await();
            }
            System.out.println(Thread.currentThread().getName() + " get 。。。");
            e = list.removeFirst();
            count--;
            // 借助条件，唤醒所有的生产者
            producer.signalAll();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }

        return e;
    }

    public static void main(String[] args) {
        final SyncContainer_ReentrantLock<String> c = new SyncContainer_ReentrantLock<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "consumer" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put("container value " + j);
                }
            }, "producer" + i).start();
        }
    }

}
