package ouc.cs.course.java.sample.soundmaker;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class MP3Player extends Thread {
	private String filename;
	private Player player;

	public MP3Player(String filename) {
		this.filename = filename;
	}
	
	@Override
	public void run() {
		try {
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
			player = new Player(buffer);
			player.play();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		String filename = System.getProperty("user.dir") + "/sound/1234.mp3";
		MP3Player mp3 = new MP3Player(filename);
		mp3.start();
		
		
	}
}
