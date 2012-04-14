package com.theluvexchange.android;

import java.io.Serializable;

import android.util.Log;

public class Pick implements Serializable {

	private String id;
	private String name;
	private String category;
	private String body;
	private String latitude;
	private String longitude;
	private String discounts; 
	private String ratingTotal;
	private String address;
	private String phone;
	private String ratingCount;
	private String ratingAverage;
	private String location;
	private String viewerRating;
	private int serialNumber;
	private String near;

	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		
		Log.d("Pick class", "inside setSerialNumberWith number = " + serialNumber);
		
		this.serialNumber = serialNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDiscounts() {
		return discounts;
	}
	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}
	public String getRatingTotal() {
		return ratingTotal;
	}
	public void setRatingTotal(String ratingTotal) {
		this.ratingTotal = ratingTotal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(String ratingCount) {
		this.ratingCount = ratingCount;
	}
	public String getRatingAverage() {
		return ratingAverage;
	}
	public void setRatingAverage(String ratingAverage) {
		this.ratingAverage = ratingAverage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getViewerRating() {
		return viewerRating;
	}
	public void setViewerRating(String viewerRating) {
		this.viewerRating = viewerRating;
	}
	/**
	 * @return the near
	 */
	public String getNear() {
		return near;
	}
	/**
	 * @param near the near to set
	 */
	public void setNear(String near) {
		this.near = near;
	}
	
	
}
