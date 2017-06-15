package com.iQueue.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator.OfInt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.iQueue.dao.MovieDao;
import com.iQueue.dao.UserDao;
import com.iQueue.dao.MovieDao;
import com.iQueue.model.Status;
import com.iQueue.model.User;
import com.iQueue.model.Movie;
@Controller
public class LoginController {
	private ApplicationContext context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");

	@RequestMapping(value="/initData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "initData");
		MovieDao movieDao = (MovieDao)context.getBean("MovieDao");
		Movie movie = (Movie)context.getBean("movie");
		if (opcode.equals("initData")) {
			modelMap.put("status", "success");
			List<Movie> movies = new ArrayList<Movie>();
			movies = movieDao.listMovies();
		} else {
			modelMap.put("status", "fail");
		};
		modelMap.put("movie", movie);
		return modelMap;
	}
	@ResponseBody
	@RequestMapping(value="/user", method = RequestMethod.POST)
	protected User login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String username, String password
		String opcode = request.getParameter("opcode");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao userDao = (UserDao)context.getBean("userDao");
		User user = (User)context.getBean("user");
		System.out.println(opcode);
		if (opcode.equals("register")) {
			if (userDao.isEmpty(username)) {
				user.setUserName(username);
				user.setPassword(password);
				user.setStatus(Status.success.toString());
				userDao.insert(user);
			} else {
				user.setStatus(Status.user_exited.toString());
			}
		} else if (opcode.equals("login")) {
			System.out.println("login");
			User regiestedUser = userDao.getUserWithPassword(username, password);

			if (regiestedUser != null) {
				regiestedUser.setStatus(Status.success.toString());
			} else {
				regiestedUser = (User)context.getBean("user");
				regiestedUser.setStatus(Status.name_or_password_error.toString());
			}
			return regiestedUser;
		}
		
		return user;
	}
}