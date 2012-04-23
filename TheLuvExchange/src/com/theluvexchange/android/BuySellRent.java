package com.theluvexchange.android;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.util.Log;

public class BuySellRent implements Serializable {
	
	public static final int ALL_ITEMS = 0;
	/*public static final int ALL_ASSETS = 1;
	public static final int ALL_VEHICLES = 2;
	public static final int ALL_HOUSING = 3;
	public static final int ALL_WANTED = 4;*/
	public static final int ANTIQUES = 5;
	public static final int ELECTRONICS = 6;
	public static final int COMPUTERS = 7;
	public static final int CLOTHES = 8;
	public static final int EQUIPMENT = 9;
	public static final int HEALTH = 10;
	public static final int FURNITURE = 11;
	public static final int JEWELRY = 12;
	public static final int SPORTS = 13;
	public static final int OUTDOORS = 14;
	public static final int TOYS = 15;
	public static final int PETS = 16;
	public static final int MISCELLANEOUS = 17;
	public static final int CARS = 18;
	public static final int CLASSIC = 20;
	public static final int MOTORCYCLES = 21;
	public static final int BOATS = 22;
	public static final int PLANES = 23;
	public static final int PARTS = 24;
	public static final int TRAILERS = 25;
	public static final int VACATION_RENTALS = 26;
	public static final int HOMES_FOR_RENT = 27;
	public static final int HOMES_FOR_SALE = 28;
	public static final int OTHER_HOUSING = 29;
	public static final int MERCHANDISE = 31;
	public static final int SERVICES = 32;
	
	private static final long MILLISECONDS_PER_DAY = 86400000;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
	private String id;
	private String userId;
	private String name;
	private String body;
	private String price;
	private String categoryId;
	private String cityId;
	private String created;
	private String albumId;
	private String filename;
	private long timeToExpire;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 * @throws ParseException 
	 */
	public void setCreated(String created) {
		this.created = created;
		try {
			long timeCreated = dateFormat.parse(created).getTime();
			long current = System.currentTimeMillis();
			long days = Math.round((current - timeCreated) / ((double)MILLISECONDS_PER_DAY));
			timeToExpire = 60 - days;
		} catch (ParseException e) {
			Log.e("BuySellRent Error", "Problem parsing date returned for created.", e);
		}
	}
	public long getTimeToExpire()
	{
		return timeToExpire;
	}
	public String getAlbumId() {
		return albumId;
	}
	/**
	 * @param albumId the albumId to set
	 */
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
