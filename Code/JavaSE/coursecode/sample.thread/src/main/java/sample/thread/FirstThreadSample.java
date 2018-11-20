package sample.thread;

/**
 * 简单线程创建示例
 * 
 * @author xiaodong
 *
 */
public class FirstThreadSample {

	public static void main(String args[]) {
		Runner1 r = new Runner1(); // 实例化线程执行对象
		Thread t = new Thread(r); // 加载线程执行对象到虚拟CPU（Thread）
		t.start(); // 启动线程
	}
}

/**
 * 线程执行对象，其中包括run()方法封装的线程体
 * 
 * @author xiaodong
 *
 */
class Runner1 implements Runnable { // 必须实现Runnable接口
	public void run() { // 线程体
		for (int i = 0; i < 30; i++) {
			System.out.println("No. " + i);
		}
	}
}