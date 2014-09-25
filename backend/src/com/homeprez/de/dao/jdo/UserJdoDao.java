package com.homeprez.de.dao.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.homeprez.de.dao.UserDao;
import com.homeprez.de.dao.jdo.util.Pmf;
import com.homeprez.de.exception.HomeprezException;
import com.homeprez.de.model.LoginUser;

public class UserJdoDao implements UserDao {

	private static UserJdoDao theInstance;

	  private UserJdoDao() { }

	  public static UserJdoDao getInstance() {
	    if (theInstance == null) {
	      theInstance = new UserJdoDao();
	    }
	    return theInstance;
	  }

	  @Override
	  public long saveUser(LoginUser user) throws HomeprezException {
	    PersistenceManager pm = Pmf.get().getPersistenceManager();
	    try {
	      validateDuplicateUser(user.getUserName());
	      pm.makePersistent(user);
	    } finally {
	      pm.close();
	    }
	    return user.getId();
	  }

	  @Override
	  public LoginUser getUser(String uname, String pass) {
	    PersistenceManager pm = Pmf.get().getPersistenceManager();
	    Query query = pm.newQuery(LoginUser.class);
	    query.setFilter("userName == uname && password == pass");
	    query.declareParameters("java.lang.String uname, java.lang.String pass");
	    query.setResultClass(LoginUser.class);
	    query.compile();
	    Object user = query.execute(uname, pass);
	    @SuppressWarnings("unchecked")
		List<LoginUser> users = (List<LoginUser>) user;
	    if (users == null || users.size() <= 0) {
	      return null;
	    } else {
	      return users.get(0);
	    }
	  }

	  @SuppressWarnings("unchecked")
	  private void validateDuplicateUser(String uname) throws HomeprezException {
	    PersistenceManager pm = Pmf.get().getPersistenceManager();

	    Query query = pm.newQuery(LoginUser.class);
	    query.setFilter("userName == uname");
	    query.declareParameters("java.lang.String uname");
	    query.setResultClass(LoginUser.class);
	    query.compile();
	    Object user = query.execute(uname);
	    List<LoginUser> users = (List<LoginUser>) user;
	    if (users != null && users.size() > 0) {
	      throw new HomeprezException("USER_NAME_ALREADY_EXISTS");
	    }
	  }
	
	
}
