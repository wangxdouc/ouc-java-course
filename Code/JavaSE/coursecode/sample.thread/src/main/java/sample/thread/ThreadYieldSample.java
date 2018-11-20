package sample.thread;

import java.util.Date;

public class ThreadYieldSample {

	public static void main(String[] args) {
		Thread t1 = new MyThread(false);
		Thread t2 = new MyThread(true);
		Thread t3 = new MyThread(false);
		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread extends Thread {
	private boolean flag;

	public MyThread(boolean flag) {
		this.flag = flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void run() {
		long start = new Date().getTime();
		for (int i = 0; i < 200; i++) {
			if (flag) {
				Thread.yield();
			}
			System.out.println(this.getName() + ": " + i + "\t");
		}
		long end = new Date().getTime();
		System.out.println("\n" + this.getName() + "执行时间：" + (end - start) + "ms");
	}
}
