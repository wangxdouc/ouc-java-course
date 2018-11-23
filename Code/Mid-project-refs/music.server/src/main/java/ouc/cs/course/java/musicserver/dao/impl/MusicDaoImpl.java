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
import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

public class MusicDaoImpl implements MusicDao {

	@Override
	public int insert(Music mu) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "insert into music(md5value, name, singer)values(?, ?, ?)";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, mu.getMd5value());
			ps.setString(2, mu.getName());
			ps.setString(3, mu.getSinger());

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
	public void update(Music mu) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update music set name=?, singer=? where md5value=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, mu.getName());
			ps.setString(2, mu.getSinger());
			ps.setString(3, mu.getMd5value());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("update data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}

	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from music where id=?";
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
	public void deleteByMd5value(String md5value) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from music where md5value=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, md5value);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("delete data by md5value failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
	}

	@Override
	public Music findById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Music mu = null;
		String sql = "select md5value, name, singer from music where id=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				mu = new Music();
				mu.setMd5value(rs.getString(1));
				mu.setName(rs.getString(2));
				mu.setSinger(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query by id failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return mu;
	}

	@Override
	public Music findByMd5value(String md5value) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Music mu = null;
		String sql = "select id, md5value, name, singer from music where md5value=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, md5value);
			rs = ps.executeQuery();
			if (rs.next()) {
				mu = new Music();
				mu.setId(rs.getInt(1));
				mu.setMd5value(rs.getString(2));
				mu.setName(rs.getString(3));
				mu.setSinger(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query by md5value failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return mu;
	}
	
	@Override
	public List<Music> findAll() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Music mu = null;
		List<Music> musicList = new ArrayList<Music>();
		String sql = "select md5value, name, singer from music";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				mu = new Music();
				mu.setMd5value(rs.getString(1));
				mu.setName(rs.getString(2));
				mu.setSinger(rs.getString(3));
				musicList.add(mu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return musicList;

	}
}
