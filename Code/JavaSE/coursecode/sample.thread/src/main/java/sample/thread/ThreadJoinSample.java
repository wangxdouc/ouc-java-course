package sample.thread;

/**
 * 线程串行化示例（使用join()方法）
 * 
 * @author xiaodong
 *
 */
public class ThreadJoinSample {
	public static void main(String[] args) {
		JoinRunner r = new JoinRunner();
		Thread t = new Thread(r);
		t.start();

		try {
			t.join(); // 尝试将子线程逻辑串行进主线程
		} catch (InterruptedException e) { // 可能会产生中断异常
			e.printStackTrace();
		} 

		for (int i = 0; i < 50; i++) {
			System.out.println("主线程：" + i);
		}
	}
}

/**
 * 被串行化进主线程的子线程任务
 * 
 * @author xiaodong
 *
 */
class JoinRunner implements Runnable {
	public void run() { // 线程体
		for (int i = 0; i < 50; i++) {
			System.out.println("子线程：" + i);
		}
	}
}