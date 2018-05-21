package ouc.cs.course.java.musicserver.model;

public class Music {

	private int id;
	private String name;
	private String singer;
	private String md5value;

	public Music() {
	}

	public Music(String md5value, String name) {
		this.md5value = md5value;
		this.name = name;

	}

	public Music(String md5value, String name, String singer) {
		this.md5value = md5value;
		this.name = name;
		this.singer = singer;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getMd5value() {
		return md5value;
	}

	public void setMd5value(String md5value) {
		this.md5value = md5value;
	}
}