package ouc.cs.course.java.sample.soundmaker;

import javax.swing.JFrame;

public class WaveFileDrawDemo {

	public static void main(String[] args) {
		String filename = System.getProperty("user.dir") + "/sound/1968.wav";

		JFrame frame = new JFrame();
		WaveFileReader reader = new WaveFileReader(filename);

		if (reader.isSuccess()) {
			// 获取第一声道
			int[] data = reader.getData()[1];
			// 创建一个绘制波形的面板
			DrawPanel drawPanel = new DrawPanel(data);
			frame.add(drawPanel);
			frame.setTitle(filename);
			frame.setSize(1000, 400);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} else {
			System.err.println(filename + "Wav file is abnormal.");
		}
	}
}
