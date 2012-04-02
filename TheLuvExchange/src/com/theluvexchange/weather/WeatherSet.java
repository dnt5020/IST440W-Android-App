package com.theluvexchange.weather;

/** 
 *  
 * @ Shibani Chadha 3/24/12 
 */
import java.util.ArrayList;

/**
 * Combines one WeatherCurrentCondition with a List of
 * WeatherForecastConditions.
 */
public class WeatherSet {
	
	// ===========================================================
	// Fields
	// ===========================================================

	private WeatherCurrentCondition myCurrentCondition = null;
	private ArrayList<WeatherForecastCondition> myForecastConditions = 
		new ArrayList<WeatherForecastCondition>(4);

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public WeatherCurrentCondition getWeatherCurrentCondition() {
		return myCurrentCondition;
	}

	public void setWeatherCurrentCondition(
			WeatherCurrentCondition myCurrentWeather) {
		this.myCurrentCondition = myCurrentWeather;
	}

	public ArrayList<WeatherForecastCondition> getWeatherForecastConditions() {
		return this.myForecastConditions;
	}

	public WeatherForecastCondition getLastWeatherForecastCondition() {
		return this.myForecastConditions
				.get(this.myForecastConditions.size() - 1);
	}
}
