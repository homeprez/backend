package com.homeprez.de.dao;

import com.homeprez.de.exception.HomeprezException;
import com.homeprez.de.model.LoginUser;

public interface UserDao {

	long saveUser(LoginUser user) throws HomeprezException;

	LoginUser getUser(String uname, String pass);

}
