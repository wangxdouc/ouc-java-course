package ouc.cs.course.java.musicserver.dao;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.model.MusicSheet;

public interface MusicSheetDao {

	public int insert(MusicSheet ms) throws SQLException;

	public void update(MusicSheet ms) throws SQLException;

	public void delete(int id) throws SQLException;

	public MusicSheet findById(int id) throws SQLException;

	public MusicSheet findByUuid(String uuid) throws SQLException;

	public List<MusicSheet> findAll() throws SQLException;

	void deleteByUuid(String uuid) throws SQLException;

	List<MusicSheet> findLatest(int num) throws SQLException;
}
