package com.theluvexchange.android;
import java.util.ArrayList;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.theluvexchange.weather.WeatherCurrentCondition;
import com.theluvexchange.weather.WeatherForecastCondition;
import com.theluvexchange.weather.WeatherSet;

/**
* @author Shibani Chadha
* 
* This class is used by the SAX XMLReader to handle parsing for the current and forecasted weather condition
* from the web service.
*/
public class WeatherHandler extends DefaultHandler {
	// Boolean flags to mark when we are inside of an element
	private boolean time = false;
	private boolean tempC = false;
	private boolean tempMinC = false;
	private boolean tempMaxC = false;
	private boolean tempMinF = false;
	private boolean tempMaxF = false;
	private boolean tempF = false;
	private boolean visibility = false;
	private boolean windspeedMiles = false;
	private boolean windspeedKmph = false;
	private boolean windDirection = false;
	private boolean precMM = false;
	private boolean iconURL = false;
	private boolean description = false;
	private boolean humidity = false;
	private boolean date = false;
	
	private  WeatherSet myWeatherSet=null ;
	
    private boolean in_current_conditions=false;
	private boolean in_forecast_conditions=false;


		/**
		 * startDocument is called automatically when the parsing begins.
		 * Here, we instantiate the weatherSet object.
		 */
		@Override
		public void startDocument() throws SAXException {
			this.myWeatherSet=new WeatherSet();
		}
		/**
		 * startElement is called every time we come across a starting element tag.
		 * Here, we check for the tag name and act accordingly.
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			
			if (localName.equals("current_condition")) {
				this.myWeatherSet
				.setWeatherCurrentCondition(new WeatherCurrentCondition());
		        this.in_current_conditions = true;
	     } else if (localName.equals("weather")) {
		        this.myWeatherSet.getWeatherForecastConditions().add(
				new WeatherForecastCondition());
		        this.in_forecast_conditions = true;
		 } else if (localName.equals("temp_F")) {
			
			 tempF = true;
		 }else if (localName.equals("temp_C")) {
			
			 tempC = true;
		}else if (localName.equals("weatherIconUrl")) {
			
			iconURL = true;
		}else if (localName.equals("weatherDesc")) {
			
			description = true;
		}else if (localName.equals("windspeedMiles")) {
			
			windspeedMiles = true;
		}else if (localName.equals("windspeedKmph")) {

			windspeedKmph= true;
		}else if (localName.equals("winddir16Point")) {

			windDirection = true;
		}else if (localName.equals("precipMM")) {

			precMM = true;
		}else if (localName.equals("visibility")) {

			visibility = true;
		}else if (localName.equals("humidity")) {

			humidity = true;
		}else if (localName.equals("date")) {

			date = true;
		}else if (localName.equals("tempMaxC")) {

			tempMaxC = true;
		}else if (localName.equals("tempMaxF")) {
			
			tempMaxF = true;
		}else if (localName.equals("tempMinC")) {

			tempMinC = true;
		}else if (localName.equals("tempMinF")) {

			tempMinF = true;
		}
		}
		
	public WeatherSet getWeatherData() {
		return this.myWeatherSet;
	}
	/**
	 * characters is called whenever we come across text between the tags.
	 * It is also useful because it will parse out CDATA which comes in handy here.
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if (in_current_conditions) {
			if(tempF){
			// If this text is inside an id tag, set the city id
			this.myWeatherSet.getWeatherCurrentCondition().setTempF(new String(ch, start, length));
			}
			if(tempC){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setTempC(new String(ch, start, length));
				}
			if(iconURL){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setIconURL(new String(ch, start, length));
				}
			if(description){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setDescription(new String(ch, start, length));
				}
			if(windspeedMiles){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setWindspeedMiles(new String(ch, start, length));
				}
			if(windspeedKmph){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setWindspeedKmph(new String(ch, start, length));
				}
			if(windDirection){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setWindDirection(new String(ch, start, length));
				}
			if(precMM){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setPrecMM(new String(ch, start, length));
				}
			if(humidity){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setHumidity(new String(ch, start, length));
				}
			if(visibility){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getWeatherCurrentCondition().setVisibiltiy(new String(ch, start, length));
				}
		} else if (in_forecast_conditions) {
			if(tempMaxF){
				// If this text is inside an id tag, set the city id
				this.myWeatherSet.getLastWeatherForecastCondition().setTempMaxF(new String(ch, start, length));
				}
				if(tempMaxC){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setTempMaxC(new String(ch, start, length));
					}
				if(tempMinC){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setTempMinC(new String(ch, start, length));
					}
				if(description){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setDesc(new String(ch, start, length));
					}
				if(windspeedMiles){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setWindspeedMiles(new String(ch, start, length));
					}
				if(windspeedKmph){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setWindspeedKmph(new String(ch, start, length));
					}
				if(windDirection){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setWindDirection(new String(ch, start, length));
					}
				if(tempMinF){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setTempMinF(new String(ch, start, length));
					}
				if(precMM){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setPrecMM(new String(ch, start, length));
					}
				if(iconURL){
					// If this text is inside an id tag, set the city id
					this.myWeatherSet.getLastWeatherForecastCondition().setIconURL(new String(ch, start, length));
					}
				if(date){
					// If this text is inside an id tag, set the date
					this.myWeatherSet.getLastWeatherForecastCondition().setDate(new String(ch, start, length));
				}
		}
		}

	/**
	 * endElement is called whenever we come across an ending element tag.
	 * Here we check if we're no longer in a tag, and unmark the boolean flag.
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if (localName.equals("current_condition")) {
			this.in_current_conditions = false;
     } else if (localName.equals("weather")) {
	        this.in_forecast_conditions = false;
	 } else if (localName.equals("temp_F")) {
			// Track if we're inside the id tag
			tempF = false;
	 }else if (localName.equals("temp_C")) {
			// Track if we're inside the id tag
			tempC = false;
	}else if (localName.equals("weatherIconUrl")) {
		// Track if we're inside the id tag
		iconURL = false;
	}else if (localName.equals("weatherDesc")) {
		// Track if we're inside the id tag
		description = false;
	}else if (localName.equals("windspeedMiles")) {
		// Track if we're inside the id tag
		windspeedMiles = false;
	}else if (localName.equals("windspeedKmph")) {
		// Track if we're inside the id tag
		windspeedKmph= false;
	}else if (localName.equals("winddir16Point")) {
		// Track if we're inside the id tag
		windDirection = false;
	}else if (localName.equals("precipMM")) {
		// Track if we're inside the id tag
		precMM = false;
	}else if (localName.equals("visibility")) {
		// Track if we're inside the id tag
		visibility = false;
	}else if (localName.equals("humidity")) {
			// Track if we're inside the id tag
			humidity = false;
	}else if (localName.equals("date")) {
		// Track if we're inside the id tag
		date = false;
	}else if (localName.equals("tempMaxC")) {
		// Track if we're inside the id tag
		tempMaxC = false;
	}else if (localName.equals("tempMaxF")) {
		// Track if we're inside the id tag
		tempMaxF = false;
	}else if (localName.equals("tempMinC")) {
		// Track if we're inside the id tag
		tempMinC = false;
	}else if (localName.equals("tempMinF")) {
		// Track if we're inside the id tag
		tempMinF = false;
	}
	} 
	}

