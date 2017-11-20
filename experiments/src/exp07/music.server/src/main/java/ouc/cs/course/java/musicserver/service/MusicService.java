package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;

import ouc.cs.course.java.musicserver.dao.MusicDao;
import ouc.cs.course.java.musicserver.dao.impl.MusicDaoImpl;
import ouc.cs.course.java.musicserver.model.Music;

public class MusicService {

	MusicDao musicDao = new MusicDaoImpl();

	public MusicService() {
	}

	public int create(Music mu) throws SQLException {
		return musicDao.insert(mu);
	}

	public void getAll() throws SQLException {
		System.out.println(musicDao.findAll());
	}
	
	public String getFilePathByMd5value(String md5value) throws SQLException {
		Music mu = musicDao.findByMd5value(md5value);
		return mu.getName();
	}

}
