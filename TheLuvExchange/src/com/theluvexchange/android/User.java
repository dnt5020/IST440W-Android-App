package com.theluvexchange.android;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class User {
	private String userName;
	private String userId;
	private String code;
	private String allowAdd;
	private String message;
	
	public User() {
	}
	public User(SharedPreferences savedUser) {
		userName = savedUser.getString("username", null);
		userId = savedUser.getString("userid", null);
		code = savedUser.getString("code", null);
		allowAdd = savedUser.getString("allowadd", null);
		message = savedUser.getString("message", null);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAllowAdd() {
		return allowAdd;
	}
	public void setAllowAdd(String allowAdd) {
		this.allowAdd = allowAdd;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void save(Editor editor, boolean remember) {
		editor.putBoolean("remember", remember);
		editor.putString("username", userName);
		editor.putString("userid", userId);
		editor.putString("code", code);
		editor.putString("allowadd", allowAdd);
		editor.putString("message", message);
		editor.commit();
	}
}
