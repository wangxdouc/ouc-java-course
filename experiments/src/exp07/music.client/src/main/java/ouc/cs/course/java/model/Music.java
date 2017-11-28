package ouc.cs.course.java.model;

public class Music {

	private String name;
	private String singer;
	private String md5value;

	public Music() {
	}

	public Music(String name, String md5value) {
		this.name = name;
		this.md5value = md5value;
	}

	public Music(String name, String singer, String md5value) {
		this.name = name;
		this.singer = singer;
		this.md5value = md5value;
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