package ouc.cs.course.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;

public class MusicPlayerBlock extends JPanel {

	private static final long serialVersionUID = 1L;

	private Object[][] musicData = { { "Yesterday.mp3", "Guns and Roses", "10 min", "", "" },
			{ "Night train.mp3", "Guns and Roses", "10 min", "", "" },
			{ "November rain.mp3", "Guns and Roses", "10 min", "", "" } };
	private String[] musicColumnNames = { "曲名", "歌手", "时长", "播放", "下载" };

	public MusicPlayerBlock() {
		this.setPreferredSize(new Dimension(550, 300));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

		JTable musicTable = new JTable(musicData, musicColumnNames);
		musicTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane musicTablePanel = new JScrollPane(musicTable);

		this.add(musicTablePanel);

		JSlider slider = new JSlider();
		this.add(slider, "普通滑动条");

		JPanel musicPlayerPanel = new JPanel();
		musicPlayerPanel.setLayout(new FlowLayout());
		musicPlayerPanel.setBackground(Color.GRAY);
		JButton previousMusicButton = new JButton("Previous");
		JButton playMusicButton = new JButton("Play");
		JButton nextMusicButtonButton = new JButton("Next");

		musicPlayerPanel.add(previousMusicButton);
		musicPlayerPanel.add(playMusicButton);
		musicPlayerPanel.add(nextMusicButtonButton);

		this.add(musicPlayerPanel);
	}
}
