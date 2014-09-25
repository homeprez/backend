package com.homeprez.de.service;

import javax.ws.rs.core.Response;

public interface UserService extends Service {
	
	Response authenticateUser(String userName, String password);

	Response getUser(String param);

	Response saveUser(String userName, String password, int typeId);

}
