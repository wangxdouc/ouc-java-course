package ouc.cs.course.java.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LocalMusicSheetBlock extends JPanel {

	private static final long serialVersionUID = 1L;

	Object[][] localMusicData = { { "music sheet 04" }, { "music sheet 05" }, { "music sheet 06" },
			{ "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" },
			{ "music sheet 04" }, { "music sheet 04" }, { "music sheet 04" } };
	String[] localMusicColumnNames = { "歌 单" };

	public LocalMusicSheetBlock() {
		this.setPreferredSize(new Dimension(250, 400));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

		this.setBackground(Color.LIGHT_GRAY);
		JLabel localMusicSheetLabel = new JLabel("本地歌单");

		JTable localMusicSheetTable = new JTable(localMusicData, localMusicColumnNames);
		localMusicSheetTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane localMusicSheetTablePanel = new JScrollPane(localMusicSheetTable);
		this.add(Box.createVerticalStrut(5));
		this.add(localMusicSheetLabel);
		this.add(Box.createVerticalStrut(5));
		this.add(localMusicSheetTablePanel);
	}
}
