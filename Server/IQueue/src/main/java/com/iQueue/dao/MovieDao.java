package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.iQueue.model.Movie;

public class MovieDao implements MovieDaoImp {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	/*
	 * 通过RowMapper赋值给Movie
	 * RowMapper可以将数据中的每一行封装成用户定义的类
	 * 在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
	 */
	private static final class MovieMapper implements RowMapper<Movie> {
		public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Movie Movie = new Movie();
			Movie.setMovieName(rs.getString("MovieName"));
			Movie.setTicket(rs.getString("Ticket"));
			return Movie;
		}
	}
	
	/*
	 * 插入信息
	 * @see MovieImp#insert(Movie)
	 */
	public void insert(Movie Movie) {
		String sql = "insert into movie (MovieName, Ticket) values(?, ?)";
		jdbcTemplate.update(sql, Movie.getMovieName(), Movie.getTicket());
	}
	
	
	public boolean isEmpty(String MovieName) {
		String SQL = "select * from movie where Moviename = ?";
		List items = jdbcTemplate.query(SQL, new Object[] { MovieName }, new MovieMapper());
		return items.isEmpty();
	}
	
	public Movie getMovie(String MovieName) {
		String SQL = "select * from movie where Moviename = ?";
		List<Movie> items = jdbcTemplate.query(SQL, new Object[] { MovieName }, new MovieMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}

	public List<Movie> listMovies() {
		String sql = "select * from movie";
		return jdbcTemplate.query(sql, new MovieMapper());
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
