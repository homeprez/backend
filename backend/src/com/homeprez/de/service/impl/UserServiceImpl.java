package com.homeprez.de.service.impl;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.homeprez.de.dao.UserDao;
import com.homeprez.de.dao.jdo.UserJdoDao;
import com.homeprez.de.exception.HomeprezException;
import com.homeprez.de.model.LoginUser;
import com.homeprez.de.service.UserService;
import com.homeprez.de.util.UserType;

@Path("/")
public class UserServiceImpl extends AbstractService implements UserService {

	private UserDao userDao = UserJdoDao.getInstance();
	
	@POST
	@Path("login")
	@Override
	public Response authenticateUser(@FormParam("userName") String userName, @FormParam("password") String password) {
		LoginUser user = userDao.getUser(userName, password);
		if (user == null) {
			return Response.status(200).entity("Invalid username or password.").build();
		} else {
			UserType userType = user.getType();
			return Response.status(200).entity("Username: " + user.getUserName() + ", Type: " + (userType != null ? userType.getText() : user.getTypeId())).build();
		}
	}

	@GET
	@Path("user/{token}")
	@Override
	public Response getUser(@PathParam("token") String param) {
		return Response.status(200).entity("user object").build();
	}

	@GET
	@Path("user/")
	@Override
	public Response saveUser(@QueryParam("userName") String userName, @QueryParam("password") String password, @QueryParam("typeId") int typeId ) {
		LoginUser user = new LoginUser();
		user.setUserName(userName);
		user.setPassword(password);
		user.setTypeId(typeId);
		try {
			userDao.saveUser(user);
			return Response.status(200).entity("Saved").build();
		} catch (HomeprezException e) {
			// add logger
			return Response.status(200).entity(e.getMessage()).build();
		}
		
	}
	
}
