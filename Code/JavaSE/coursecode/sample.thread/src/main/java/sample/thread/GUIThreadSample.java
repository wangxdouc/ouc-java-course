package sample.thread;

import java.awt.*;
import java.awt.event.*;

/**
 * GUI线程展示示例
 * 
 * @author xiaodong
 *
 */
public class GUIThreadSample {
	public static void main(String[] args) throws Exception {
		Frame f = new Frame();
		f.setSize(400, 300);
		f.setLocation(200, 200);

		MyWindowListener mw = new MyWindowListener();

		Button b = new Button("Press Me");
		MyMonitor mm = new MyMonitor();
		b.addActionListener(mm);
		f.addWindowListener(mw);
		f.add(b, "Center");
		f.setVisible(true);
		MyThreadViewer.view();
	}
}

class MyMonitor extends WindowAdapter implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		MyThreadViewer.view();
	}
}

class MyWindowListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MyThreadViewer.view();
		System.exit(1);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}

/**
 * 线程信息显示工具类
 * 
 * @author xiaodong
 *
 */
class MyThreadViewer {
	public static void view() {
		Thread current = Thread.currentThread(); // 获得当前线程的引用
		System.out.println("当前线程名称：" + current.getName()); // 获得当前线程名称
		int total = Thread.activeCount();
		System.out.println("活动线程总数：" + total + "个");
		Thread[] threads = new Thread[total];
		Thread.enumerate(threads);
		for (Thread t : threads) {
			String role = t.isDaemon() ? "后台线程 " : "用户线程 ";
			System.out.println("   -" + role + t.getName());
		}
		System.out.println("--------------------------------");
	}
}