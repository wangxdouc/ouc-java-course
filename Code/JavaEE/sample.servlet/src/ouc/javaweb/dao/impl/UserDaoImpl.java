package ouc.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ouc.javaweb.dao.UserDao;
import ouc.javaweb.model.User;
import ouc.javaweb.util.db.DatabaseUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public int insert(User user) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;

		String sql = "insert into user (username, md5) values(?, ?)";

		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, user.getName());
			ps.setString(2, user.getPasswordMd5());

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
	public void update(User user) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public User findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		List<User> users = new ArrayList<User>();
		String sql = "select username from user";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setName(rs.getString(1));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return users;

	}

}
