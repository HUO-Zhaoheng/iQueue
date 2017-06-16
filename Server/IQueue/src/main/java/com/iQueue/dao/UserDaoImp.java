package com.iQueue.dao;

import java.util.List;
import com.iQueue.model.User;

public interface UserDaoImp {
	public void insert(User user);
	public User getUser(String userName);
}
