package ouc.cs.course.java.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MusicSheetManagementBlock extends JPanel {

	private static final long serialVersionUID = 1L;

	public MusicSheetManagementBlock() {
		this.setPreferredSize(new Dimension(250, 50));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton createMusicSheetButton = new JButton("创建新歌单");
		JButton deleteMusicSheetButton = new JButton("删除歌单");

		this.add(createMusicSheetButton);
		this.add(deleteMusicSheetButton);
	}
}
