package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.iQueue.model.User;

public class UserDao implements UserDaoImp {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	/*
	 * 通过RowMapper赋值给user
	 * RowMapper可以将数据中的每一行封装成用户定义的类
	 * 在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
	 */
	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getString("status"));
			return user;
		}
	}
	
	/*
	 * 插入信息
	 * @see UserImp#insert(User)
	 */
	public void insert(User user) {
		String sql = "insert into user ( username, password, status) values(?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getStatus());
	}
	
	
	public boolean isEmpty(String userName) {
		String SQL = "select * from user where username = ?";
		List items = jdbcTemplate.query(SQL, new Object[] { userName }, new UserMapper());
		return items.isEmpty();
	}
	
	public User getUser(String userName) {
		String SQL = "select * from user where username = ?";
		List<User> items = jdbcTemplate.query(SQL, new Object[] { userName }, new UserMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}
	
	public User getUserWithPassword(String userName, String password) {
		String SQL = "select * from user where username = ? and password = ?";
		List<User> items = jdbcTemplate.query(SQL, new Object[] { userName, password }, new UserMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}

	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJbdcTemplate() {
		return jdbcTemplate;
	}
}
