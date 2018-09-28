package ouc.cs.course.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SharedMusicSheetBlock extends JPanel {

	private static final long serialVersionUID = 1L;

	private Object[][] shareMusicData = { { "101", "music sheet 01" }, { "102", "music sheet 02" },
			{ "103", "music sheet 03" } };
	private String[] shareMusicColumnNames = { "分享者", "歌 单" };

	public SharedMusicSheetBlock() {
		this.setPreferredSize(new Dimension(250, 400));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		
		this.setBackground(Color.ORANGE);
		JLabel sharedMusicSheetLabel = new JLabel("共享歌单");
				 		
		JTable sharedMusicSheetTable = new JTable(shareMusicData, shareMusicColumnNames);
		sharedMusicSheetTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sharedMusicSheetTablePanel = new JScrollPane(sharedMusicSheetTable);
		this.add(Box.createVerticalStrut(5));		
		this.add(sharedMusicSheetLabel);
		this.add(Box.createVerticalStrut(5));		
		this.add(sharedMusicSheetTablePanel);
	}
}
