package com.iQueue.dao;

import java.util.List;
import com.iQueue.model.Movie;

public interface MovieDaoImp {
	public void insert(Movie movie);
	public Movie getMovie(String movieName);
}
