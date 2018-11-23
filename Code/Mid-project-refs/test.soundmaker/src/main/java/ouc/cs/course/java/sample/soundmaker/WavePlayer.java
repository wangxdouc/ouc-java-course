package ouc.cs.course.java.sample.soundmaker;

import java.io.FileInputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class WavePlayer {

	public static void main(String args[]) {
		try {
			FileInputStream fileaudio = new FileInputStream(System.getProperty("user.dir") + "/sound/1039.wav");
			AudioStream as = new AudioStream(fileaudio);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
