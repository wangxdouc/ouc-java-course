package sample.thread;

/**
 * 线程间共享代码和数据 通过共享同一个实现了Runnable接口的对象实现数据的共享
 * 
 * @author xiaodong
 *
 */
public class ShareDataWithinThreadsSample {

	public static void main(String[] args) {
		ThreadSale ts = new ThreadSale(1000); // 创建线程共享对象

		// 将对象传给三个线程，作为共享线程任务执行，同时指定线程名称
		Thread t1 = new Thread(ts, "第一售票窗口");
		Thread t2 = new Thread(ts, "第二售票窗口");
		Thread t3 = new Thread(ts, "第三售票窗口");

		// 启动三个线程
		t1.start();
		t2.start();
		t3.start();
	}

}

/**
 * 共享线程对象（不仅共享代码，而且共享数据tickets）
 * 
 * @author xiaodong
 *
 */
class ThreadSale implements Runnable {

	private int tickets = 10;

	public ThreadSale() {

	}

	public ThreadSale(int tickets) { // 初始化tickets数量
		this.tickets = tickets;
	}

	@Override
	public void run() { // 线程体
		while (true) {
			if (tickets > 0) {
				// 获取当前线程名称和tickets值
				System.out.println(Thread.currentThread().getName() + "售机票第 " + tickets-- + " 号");
			} else {
				System.exit(0); // tickets数量降至0时，退出程序
			}

		}
	}
}