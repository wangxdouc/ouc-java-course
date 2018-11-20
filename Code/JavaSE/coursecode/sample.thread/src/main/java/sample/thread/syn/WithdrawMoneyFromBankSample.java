package sample.thread.syn;

/**
 * 银行取款示例
 * 
 * @author xiaodong
 *
 */
public class WithdrawMoneyFromBankSample {

	public static void main(String[] args) {
		// 创建两个取款线程（模拟两个取款客户）
		Customer c1 = new Customer("Kevin");
		Customer c2 = new Customer("Lisa");

		c1.start();
		c2.start();
	}

}

/**
 * 银行类 
 */
class MBank {
	private static int sum = 2000; // 银行总款额

	/*
	 * 取款方法
	 * 
	 * 通过synchronized指定take()为同步方法，当被synchronized限定的代码段执行完会自动释放互斥锁
	 * 
	 * （使用或删除synchronized关键字演示代码执行效果）
	 */
	public static void take(int k) {
		int temp = sum;
		temp -= k;

		System.out.println("用户 " + Thread.currentThread().getName() + " 取款 " + k);

		try {
			Thread.sleep((int) (1000 * Math.random()));
		} catch (InterruptedException e) {
		}

		sum = temp;

		System.out.println("当前银行余额为：" + sum);
	}
}

/**
 * 模拟用户取款的线程类
 */
class Customer extends Thread {
	public Customer(String name) {
		super(name);
	}

	public void run() {
		for (int i = 1; i <= 4; i++) {
			MBank.take(100);
		}
	}
}