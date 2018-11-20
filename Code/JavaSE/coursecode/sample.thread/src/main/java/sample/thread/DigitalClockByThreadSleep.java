package sample.thread;

import java.awt.Font;
import javax.swing.*;

/**
 * 基于线程休眠的数字计数器示例
 * 
 * @author xiaodong
 *
 */
public class DigitalClockByThreadSleep {
	public static void main(String[] args) {
		JFrame jf = new JFrame("Clock");
		jf.setSize(500, 500);
		jf.setLocation(100, 100);

		JLabel clock = new JLabel("clock");
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setFont(new Font("Times New Roman", Font.BOLD, 200));

		jf.add(clock, "Center");

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		Thread t = new TimerThread(clock);
		t.start();

	}
}

/**
 * 自增计数器线程任务
 * 
 * @author xiaodong
 *
 */
class TimerThread extends Thread {
	private JLabel clock;
	private int i;

	public TimerThread(JLabel clock) {
		this.clock = clock;
		this.i = 1;
	}

	public void run() { // 线程体
		while (true) {
			clock.setText(String.valueOf(i++)); // 设置clock label的递增值
			try {
				Thread.sleep(1000); // 延迟1秒（1000毫秒）
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}