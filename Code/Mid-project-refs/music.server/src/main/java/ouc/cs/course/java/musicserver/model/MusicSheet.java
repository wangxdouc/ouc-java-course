package ouc.cs.course.java.musicserver.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class MusicSheet {

	private int id;
	private String uuid;
	private String name;
	private String creatorId;
	private String creator;
	private String dateCreated;
	private String picture;
	// <MD5, Music file name>
	private Map<String, String> musicItems;

	public MusicSheet() {
	}

	public MusicSheet(String name) {
		this();
		this.name = name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Map<String, String> getMusicItems() {
		return musicItems;
	}

	public void setMusicItems(Map<String, String> musicItems) {
		this.musicItems = musicItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}