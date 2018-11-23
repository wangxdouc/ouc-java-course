package ouc.cs.course.java.sample.soundmaker;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	private int[] data = null;

	public DrawPanel(int[] data) {
		this.data = data;
	}

	@Override
	protected void paintComponent(Graphics g) {
		int ww = getWidth();
		int hh = getHeight();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, ww, hh);

		int len = data.length;
		int step = len / ww;
		if (step == 0)
			step = 1;

		int prex = 0, prey = 0; // 上一个坐标
		int x = 0, y = 0;

		g.setColor(Color.RED);
		double k = hh / 2.0 / 32768.0;
		for (int i = 0; i < ww; ++i) {
			x = i;
			// 下面是个三点取出并绘制
			// 实际中应该按照采样率来设置间隔
			y = hh - (int) (data[i * 3] * k + hh / 2);

			System.out.print(y);
			System.out.print(" ");

			if (i != 0) {
				g.drawLine(x, y, prex, prey);
			}
			prex = x;
			prey = y;
		}
	}
}
