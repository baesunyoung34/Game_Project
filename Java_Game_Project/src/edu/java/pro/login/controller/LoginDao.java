package edu.java.pro.login.controller;

import java.util.List;

import edu.java.pro.login.model.LoginUser;

public interface LoginDao {
	int create(LoginUser loginuser); // 유저가 사용가능
	
	
}
