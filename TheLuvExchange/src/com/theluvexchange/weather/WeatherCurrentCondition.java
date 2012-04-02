package com.theluvexchange.weather;


/**
* @author Shibani Chadha
*/

public class WeatherCurrentCondition {

	// ===========================================================
	// Fields
	// ===========================================================

	private String time = null;
	private String tempC = null;
	private String tempMinC = null;
	private String tempMaxC = null;
	private String tempMinF = null;
	private String tempMaxF = null;
	private String tempF = null;
	private String visibiltiy = null;
	private String windspeedMiles = null;
	private String windspeedKmph = null;
	private String windDirection = null;
	private String precMM = null;
	private String iconURL = null;
	private String description = null;
	private String humidity = null;

	// ===========================================================
	// Constructors
	// ===========================================================

	public WeatherCurrentCondition() {

	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTempC() {
		return tempC;
	}

	public void setTempC(String tempC) {
		this.tempC = tempC;
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

	public String getTempF() {
		return tempF;
	}

	public void setTempF(String tempF) {
		this.tempF = tempF;
	}

	public String getVisibiltiy() {
		return visibiltiy;
	}

	public void setVisibiltiy(String visibiltiy) {
		this.visibiltiy = visibiltiy;
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

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

}
