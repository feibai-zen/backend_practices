package com.feibai.study.demos.multithread.threadpool;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

  public static void main(String[] args) {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,  //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
            10, //线程池最大能容忍的线程数
            200, //线程存活时间
            TimeUnit.MILLISECONDS, //参数keepAliveTime的时间单位
            new ArrayBlockingQueue<Runnable>(5) //任务缓存队列，用来存放等待执行的任务
    );

    for (int i = 0; i < 15; i++) {
      MyTask myTask = new MyTask(i);
      executor.execute(myTask);
      System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
              executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
    }
    executor.shutdown();
  }
}
