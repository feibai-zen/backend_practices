
package com.feibai.study.demos.multithread.volatile_test;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字
 * <p>
 * volatile的可见性
 * <p>
 * 通知OS操作系统底层，在CPU计算过程中，都要检查内存中数据的有效性。保证最新的内存数据被使用。
 * <p>
 * 用来通知os底层，数据修改可见性
 */
public class Volatile_test01 {

  /*volatile*/ boolean b = true;

  void m() {
    System.out.println("start");
    while (b) {// CPU忙碌，没有空闲将工作内存中的数据刷新到主存中
    }
    System.out.println("end");
  }

  public static void main(String[] args) {
    final Volatile_test01 t = new Volatile_test01();
    new Thread(new Runnable() {
      @Override
      public void run() {
        t.m();
      }
    }).start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t.b = false;
  }

}
