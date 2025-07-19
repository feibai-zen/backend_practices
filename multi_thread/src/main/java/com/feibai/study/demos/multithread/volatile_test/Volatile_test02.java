package com.feibai.study.demos.multithread.volatile_test;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile关键字
 * 
 * volatile的非原子性问题
 * 
 * volatile， 只能保证可见性，不能保证原子性。
 * 
 * 不是加锁问题，只是内存数据可见。
 */
public class Volatile_test02 {

	/*volatile*/ int count = 0;

	 synchronized void m() {
		for (int i = 0; i < 10000; i++) {
			count++;// 不是原子操作
		}
	}

	public static void main(String[] args) {
		final Volatile_test02 t = new Volatile_test02();
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					t.m();
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(t.count);
	}
}
