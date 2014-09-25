package com.homeprez.de.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.homeprez.de.util.UserType;

@PersistenceCapable(detachable = "true")
public class LoginUser {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String userName;
	@Persistent
	private String password;
	@Persistent
	private int typeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int type) {
		this.typeId = type;
	}

	public UserType getType() {
		return UserType.getUserTypeById(typeId);
	}
}
