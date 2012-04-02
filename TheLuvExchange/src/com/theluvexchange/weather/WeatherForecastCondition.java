package com.theluvexchange.weather;
/**
* @author Shibani Chadha
 */

public class WeatherForecastCondition {

	// ===========================================================
	// Fields
	// ===========================================================

	private String date = null;
	private String tempMinC = null;
	private String tempMaxC = null;
	private String tempMinF = null;
	private String tempMaxF = null;
	private String windspeedMiles = null;
	private String windspeedKmph = null;
	private String windDirection = null;
	private String precMM = null;
	private String iconURL = null;
	private String condition = null;
	private String desc=null;
	
	public String getIconURL() {
		return iconURL;
	}


	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}

	
	public WeatherForecastCondition() {

	}

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTempMinC() {
		return tempMinC;
	}

	public void setTempMinC(String tempMinC) {
		this.tempMinC = tempMinC;
	}

	public String getTempMaxC() {
		return tempMaxC;
	}

	public void setTempMaxC(String tempMaxC) {
		this.tempMaxC = tempMaxC;
	}

	public String getTempMinF() {
		return tempMinF;
	}

	public void setTempMinF(String tempMinF) {
		this.tempMinF = tempMinF;
	}

	public String getTempMaxF() {
		return tempMaxF;
	}

	public void setTempMaxF(String tempMaxF) {
		this.tempMaxF = tempMaxF;
	}

	public String getWindspeedMiles() {
		return windspeedMiles;
	}

	public void setWindspeedMiles(String windspeedMiles) {
		this.windspeedMiles = windspeedMiles;
	}

	public String getWindspeedKmph() {
		return windspeedKmph;
	}

	public void setWindspeedKmph(String windspeedKmph) {
		this.windspeedKmph = windspeedKmph;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getPrecMM() {
		return precMM;
	}

	public void setPrecMM(String precMM) {
		this.precMM = precMM;
	}

	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
}
	

