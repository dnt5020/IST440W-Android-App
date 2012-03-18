package com.theluvexchange.android;

import android.app.Application;

public class TheLuvExchange extends Application {
	private User user;
	private City city;

	public TheLuvExchange() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
