package com.theluvexchange.android;

public class Rating extends Pick {
	private String created;
	private String userName;
	private String userId;
	
	public Rating() {
		super();
	}
	public Rating(Pick pick) {
		super();
		setId(pick.getId());
		setName(pick.getName());
		setCategory(pick.getCategory());
		setLatitude(pick.getLatitude());
		setLongitude(pick.getLongitude());
		setRatingTotal(pick.getRatingTotal());
		setAddress(pick.getAddress());
		setPhone(pick.getPhone());
		setRatingCount(pick.getRatingCount());
		setRatingAverage(pick.getRatingAverage());
		setLocation(pick.getLocation());
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
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
}
