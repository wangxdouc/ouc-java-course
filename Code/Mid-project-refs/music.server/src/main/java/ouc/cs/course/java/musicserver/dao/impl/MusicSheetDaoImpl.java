package ouc.cs.course.java.musicserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.MusicSheetDao;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

public class MusicSheetDaoImpl implements MusicSheetDao {

	@Override
	public int insert(MusicSheet ms) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "insert into musicsheet(uuid, name, creatorId, creator, dateCreated, picture)values(?, ?, ?, ?, ?, ?)";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, ms.getUuid());
			ps.setString(2, ms.getName());
			ps.setString(3, ms.getCreatorId());
			ps.setString(4, ms.getCreator());
			ps.setString(5, ms.getDateCreated());
			ps.setString(6, ms.getPicture());
			
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
	public void update(MusicSheet ms) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update musicsheet set name=?, creatorId=?, creator=?, dateCreated=?, picture=? where uuid=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ms.getName());
			ps.setString(2, ms.getCreatorId());
			ps.setString(3, ms.getCreator());
			ps.setString(4, ms.getDateCreated());
			ps.setString(5, ms.getPicture());
			ps.setString(6, ms.getUuid());
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
		String sql = "delete from musicsheet where id=?";
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
	public void deleteByUuid(String uuid) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from musicsheet where uuid=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("delete data by uuid failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
	}

	@Override
	public MusicSheet findById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MusicSheet ms = null;
		String sql = "select uuid, name, creatorId, creator, dateCreated, picture from musicsheet where id=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				ms = new MusicSheet();
				ms.setUuid(rs.getString(1));
				ms.setName(rs.getString(2));
				ms.setCreatorId(rs.getString(3));
				ms.setCreator(rs.getString(4));
				ms.setDateCreated(rs.getString(5));
				ms.setPicture(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query by id failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return ms;
	}

	@Override
	public MusicSheet findByUuid(String uuid) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MusicSheet ms = null;
		String sql = "select id, uuid, name, creatorId, creator, dateCreated, picture from musicsheet where uuid=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uuid);
			rs = ps.executeQuery();
			if (rs.next()) {
				ms = new MusicSheet();
				ms.setId(rs.getInt(1));
				ms.setUuid(rs.getString(2));
				ms.setName(rs.getString(3));
				ms.setCreatorId(rs.getString(4));
				ms.setCreator(rs.getString(5));
				ms.setDateCreated(rs.getString(6));
				ms.setPicture(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("MusicSheetDaoImpl: query by uuid failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return ms;
	}
	
	
	@Override
	public List<MusicSheet> findAll() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MusicSheet ms = null;
		List<MusicSheet> musicSheetList = new ArrayList<MusicSheet>();
		String sql = "select id, uuid, name, creatorId, creator, dateCreated, picture from musicsheet";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ms = new MusicSheet();
				ms.setId(rs.getInt(1));
				ms.setUuid(rs.getString(2));
				ms.setName(rs.getString(3));
				ms.setCreatorId(rs.getString(4));
				ms.setCreator(rs.getString(5));
				ms.setDateCreated(rs.getString(6));
				ms.setPicture(rs.getString(7));
				musicSheetList.add(ms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return musicSheetList;
	}
	
	/**
	 * 查询num条记录
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<MusicSheet> findLatest(int num) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MusicSheet ms = null;
		List<MusicSheet> musicSheetList = new ArrayList<MusicSheet>();
		String sql = "select id, uuid, name, creatorId, creator, dateCreated, picture from musicsheet order by id desc limit " + num;
		
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ms = new MusicSheet();
				ms.setId(rs.getInt(1));
				ms.setUuid(rs.getString(2));
				ms.setName(rs.getString(3));
				ms.setCreatorId(rs.getString(4));
				ms.setCreator(rs.getString(5));
				ms.setDateCreated(rs.getString(6));
				ms.setPicture(rs.getString(7));
				musicSheetList.add(ms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query latest data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return musicSheetList;
	}
}
