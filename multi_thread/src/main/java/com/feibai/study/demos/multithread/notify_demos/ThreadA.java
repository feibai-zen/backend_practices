package com.feibai.study.demos.multithread.notify_demos;

public class ThreadA extends Thread {
	public ThreadA(String name) {
		super(name);
	}

	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + " call notify()");
			notify();
		}
	}
}
