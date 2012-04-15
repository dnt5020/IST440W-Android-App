package com.theluvexchange.android;

import android.util.Log;

public class Comment {
	
	private String cityId;
	private String message;
	private String time;
	private String username;
	private int serialNumber;
	
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCityId() {
		return cityId;
	}
	
	public void setCityId(String cityId) {
	this.cityId=cityId	;
	}
	

}
