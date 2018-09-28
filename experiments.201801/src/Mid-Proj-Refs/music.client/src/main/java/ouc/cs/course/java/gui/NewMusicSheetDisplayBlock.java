package ouc.cs.course.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewMusicSheetDisplayBlock extends JPanel {

	private static final long serialVersionUID = 1L;
	private String picPath = "/Users/xiaodong/Music/guns and roses/fig-guns and roses.jpg";

	public NewMusicSheetDisplayBlock() {
		this.setPreferredSize(new Dimension(550, 250));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.CYAN);
		ImageIcon musicSheetPicture = new ImageIcon(picPath);
		int musicSheetPictureWidth = 250;
		int musicSheetPictureHeight = 250 * musicSheetPicture.getIconHeight() / musicSheetPicture.getIconWidth();
		musicSheetPicture.setImage(musicSheetPicture.getImage().getScaledInstance(musicSheetPictureWidth,
				musicSheetPictureHeight, Image.SCALE_DEFAULT));

		JLabel musicSheetPictureLabel = new JLabel(musicSheetPicture);
		musicSheetPictureLabel.setPreferredSize(new Dimension(musicSheetPictureWidth, musicSheetPictureHeight));

		JPanel musicSheetInfoPanel = new JPanel();
		musicSheetInfoPanel.setPreferredSize(new Dimension(300, 200));
		musicSheetInfoPanel.setLayout(new BoxLayout(musicSheetInfoPanel, BoxLayout.Y_AXIS));

		JPanel musicSheetButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton playAllMusicButton = new JButton("播放全部");
		JButton downloadAllMusicButton = new JButton("下载全部");
		musicSheetButtonPanel.add(playAllMusicButton);
		musicSheetButtonPanel.add(downloadAllMusicButton);

		JLabel musicSheetTitleLabel = new JLabel("杂七杂八的摇滚");
		JLabel musicSheetCreatorLabel = new JLabel("2011022 于 11月24日创建");

		musicSheetInfoPanel.add(Box.createVerticalStrut(20));
		musicSheetInfoPanel.add(musicSheetTitleLabel);
		musicSheetInfoPanel.add(Box.createVerticalStrut(10));
		musicSheetInfoPanel.add(musicSheetCreatorLabel);
		musicSheetInfoPanel.add(Box.createVerticalStrut(50));
		musicSheetInfoPanel.add(musicSheetButtonPanel);

		this.add(musicSheetPictureLabel, new Integer(Integer.MIN_VALUE));
		this.add(musicSheetInfoPanel);

	}
}
