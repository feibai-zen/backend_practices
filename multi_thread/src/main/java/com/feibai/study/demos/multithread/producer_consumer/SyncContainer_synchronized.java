package com.feibai.study.demos.multithread.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者
 * <p>
 * 自定义同步容器，容器容量上限为10。可以在多线程中应用，并保证数据线程安全。
 * <p>
 * wait & notify
 * <p>
 * wait/notify 都是和 while 配合应用的。可以避免多线程并发判断逻辑失效问题。
 */
public class SyncContainer_synchronized<E> {

    private final LinkedList<E> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized void put(E e) {
        /**
         * 这里为什么使用while不用 if? 如果使用 if 会导致虚假唤醒状态。也就是说 if 只经过一次判断，到达 wait() 语句
         * 的时候会休眠并释放锁，当再次被唤醒的时候，将继续执行wait()之后的语句，而不会再去判断是否符合条件
         */
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        list.add(e);
        count++;
        this.notifyAll();
    }

    public synchronized E get() {
        E e = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        e = list.removeFirst();
        count--;
        this.notifyAll();
        return e;
    }

    public static void main(String[] args) {
        final SyncContainer_synchronized<String> c = new SyncContainer_synchronized<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + "-" + c.get());
                }
            }, "consumer" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + "-container value " + j);
                }
            }, "producer" + i).start();
        }

    }

}
