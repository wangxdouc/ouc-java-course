package ouc.cs.course.java.musicserver.dao;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.MusicSheet;

public interface MusicDao {

	public int insert(Music mu) throws SQLException;

	public void update(Music mu) throws SQLException;

	public void delete(int id) throws SQLException;

	public void deleteByMd5value(String md5value) throws SQLException;

	public Music findById(int id) throws SQLException;

	public List<Music> findAll() throws SQLException;

	public Music findByMd5value(String md5value) throws SQLException;

}
