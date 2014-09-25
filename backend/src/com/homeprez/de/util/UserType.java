package com.homeprez.de.util;

public enum UserType {

	SUPER_ADMIN(1, "Super Admin"), SOCIETY_ADMIN(2, "Society Admin"), BUILDING_ADMIN(
			3, "Building Admin");

	private UserType(int id, String text) {
		this.text = text;
	}

	private int id;
	private String text;

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public static UserType getUserTypeById(int id) {
		for (UserType type : UserType.values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return null;
	}
}
