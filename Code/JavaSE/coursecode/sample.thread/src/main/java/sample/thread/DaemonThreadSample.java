package sample.thread;

/**
 * 后台线程示例
 *   *******************************************************************
 *   将t2标记为后台线程后，t2并没有如预期的输出数字0-9999，而是提前终止。这是因
 *   为，待用户线程（这里包括主线程和线程t1）全部运行结束后，JVM检测到只剩
 *   下后台线程在运行的时候，就退出了当前程序的运行。
 *   *******************************************************************
 *   
 * @author xiaodong
 *
 */
public class DaemonThreadSample {

	public static void main(String[] args) {
		Thread t1 = new MyRunner(10); // t1线程体执行10次循环
		t1.setName("用户线程t1");
		t1.start();
		Thread t2 = new MyRunner(10000); // t2线程体执行10000次循环

		t2.setDaemon(true); // 将t2线程设置为后台线程（守护线程），带注释或取消注释观察程序执行情况
		t2.setName("后台线程t2");
		t2.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
		System.out.println("主线程结束");
	}
}

class MyRunner extends Thread {
	private int n;

	public MyRunner(int n) {
		this.n = n;
	}

	public void run() {
		for (int i = 0; i < n; i++) {
			System.out.println(this.getName() + ": " + i);
		}
		System.out.println(this.getName() + " 结束");
	}
}