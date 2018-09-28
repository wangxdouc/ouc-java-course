package ouc.cs.course.java.musicserver.model;

public class MusicSheetToMusic {

	private int id;
	private int musicSheetId;
	private int musicId;

	public MusicSheetToMusic() {
	}

	public MusicSheetToMusic(int musicSheetId, int musicId) {
		this.setMusicSheetId(musicSheetId);
		this.setMusicId(musicId);

	}

	public int getMusicSheetId() {
		return musicSheetId;
	}

	public void setMusicSheetId(int musicSheetId) {
		this.musicSheetId = musicSheetId;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}