package sample.thread;

public class MultiThreadsSample {

	public static void main(String args[]) {
		Runner2 r = new Runner2();

		// 分别创建两个线程来执行同一个线程对象
		Thread t1 = new Thread(r); 
		Thread t2 = new Thread(r);
		// 分别启动两个线程
		t1.start();
		t2.start();
	}
}

class Runner2 implements Runnable {
	public void run() { // 线程体
		for (int i = 0; i < 100; i++) {
			String s = Thread.currentThread().getName(); // 获取当前运行中的线程对象
			System.out.println(s + ": " + i);
		}
	}
}
