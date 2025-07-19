package com.feibai.study.demos.multithread.advanced.t02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * feibai
 *
 * @param <E>
 */
public class HomeWorkTest3<E> {

  private volatile List<E> list = new ArrayList();
  ReentrantLock lock = new ReentrantLock();

  public void getSize() throws InterruptedException {

    while (list.size() < 5) {
      this.wait();
    }
    this.notify();
    System.out.println("getSize.....");
  }

  public void add(E element) throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      Thread.sleep(1000);
      System.out.println("add ........." + i);
      list.add(element);
      if (list.size() == 5) {
        this.notify();
        this.wait();
      }
    }
  }

  public static void main(String[] args) {
    HomeWorkTest3<Integer> test = new HomeWorkTest3();
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
