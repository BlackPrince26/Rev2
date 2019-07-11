package com.revature.service;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.pojo.User;
import com.revature.pojo.UserInformation;

public class UserService {

	UserDao userDao = new UserDao();

	public List<User> findAllUsers(){
		return userDao.findAll();
	}

	//	public User login (String username, String password) {
	//		User u = userDao.getByUsername, String password);
	//		if(u == null) return null;
	//		if(u.getUsrPassword().equals()) {}
	//	}

	public User login(String username, String usrPassword) {
		User u = userDao.getByUsername(username);
		System.out.println("IN USER SERVICE " + u);
		System.out.println(usrPassword + " " + u.getUsrPassword());
		if(u == null) return null;
		if(u.getUsrPassword().equals(usrPassword)) return u;
		else {
			return null;
		}
	}

	public UserInformation getInfo(User u) {
		return userDao.getUserInfo(u);
	}

}
