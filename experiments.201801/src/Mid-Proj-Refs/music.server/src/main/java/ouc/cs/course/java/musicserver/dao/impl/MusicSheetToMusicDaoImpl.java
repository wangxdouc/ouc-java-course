package ouc.cs.course.java.musicserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.MusicDao;
import ouc.cs.course.java.musicserver.dao.MusicSheetDao;
import ouc.cs.course.java.musicserver.dao.MusicSheetToMusicDao;
import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.model.MusicSheetToMusic;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

public class MusicSheetToMusicDaoImpl implements MusicSheetToMusicDao {

	@Override
	public int insert(MusicSheetToMusic mstm) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "insert into musicsheet_music(musicsheetId, musicId)values(?, ?)";

		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, mstm.getMusicSheetId());
			ps.setInt(2, mstm.getMusicId());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("add data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}

		return autoIncKey;
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from musicsheet_music where id=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("delete data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
	}

	@Override
	public List<Integer> findByMusicSheetId(int musicsheetId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> musicIdList = new ArrayList<Integer>();
		
		String sql = "select musicId from musicsheet_music where musicsheetId=?";
		
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, musicsheetId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				musicIdList.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query by musicsheetId failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return musicIdList;

	}
}
