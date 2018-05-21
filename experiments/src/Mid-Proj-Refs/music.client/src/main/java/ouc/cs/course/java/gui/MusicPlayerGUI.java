package ouc.cs.course.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MusicPlayerGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public MusicPlayerGUI(String title) {
		this.setTitle(title);
		this.setSize(900, 600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout(4, 4));
		container.setBackground(Color.WHITE);

		/* WEST ************************************/
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(250, 600));
		BoxLayout westPanelLayout = new BoxLayout(westPanel, BoxLayout.Y_AXIS);
		westPanel.setLayout(westPanelLayout);
		container.add("West", westPanel);

		westPanel.add(new SharedMusicSheetBlock());
		westPanel.add(new LocalMusicSheetBlock());
		westPanel.add(new MusicSheetManagementBlock());

		/* CENTER ************************************/
		JPanel centerPanel = new JPanel();
		BoxLayout centerPanelLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
		centerPanel.setLayout(centerPanelLayout);
		container.add("Center", centerPanel);

		centerPanel.add(new MusicSheetDisplayBlock());
		
		centerPanel.add(new MusicPlayerBlock());

	

	}

	public static void main(String[] args) {
		new MusicPlayerGUI("MUSIC PLAYER").setVisible(true);
	}
}
