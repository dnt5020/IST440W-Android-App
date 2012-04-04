package com.theluvexchange.android;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.theluvexchange.weather.WeatherSet;

/**
 * @author Tristan Maschke
 * 
 * This class is meant to serve as the base for providing web service calls to
 * the rest of the program.
 * 
 * Implementation as of 3/5/12 includes just a static method for getting the list of cities
 */
public class WebService {
	// Base URL is usually going to start with this
	private static final String ADDRESS = "http://www.theluvexchange.com/iphone/";
	private static SAXParser parser; // Singleton SAXParser object
	
	/*
	 * Singleton object that is used to create XMLReader objects for other service calls.
	 * This prevents the SAXParser object from having to be created multiple times,
	 * and boosts responsiveness of all the methods slightly.
	 */
	private static SAXParser getParser() {
		if (parser == null) {
			try {
				parser = SAXParserFactory.newInstance().newSAXParser();
			} catch (Exception e) {
				Log.e("TheLuvExchange", "WebServiceError", e);
			}
		}
		return parser;
	}
	
	/**
	 * Returns true if website is reachable.
	 * @return
	 */
	public static boolean ping() {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					"http://www.theluvexchange.com").openConnection();
			connection.setRequestMethod("HEAD");
			int responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				return false;
			}
		} catch (Exception e) {
			Log.e("TheLuvExchange", "WebServiceError", e);
			return false;
		}
		return true;
	}
	
	/**
	 * Method to authenticate login credentials with the website.
	 * Returns an Object that will either be an instanceof User or String.
	 * If the return value is a User, then the login was successful.
	 * Otherwise the String will be a message explaining why it was not.
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Object login(String userName, String password) {
		User user = null;

		boolean blankUserName = userName == null || userName.trim().equals("");
		boolean blankPassword = password == null || password.trim().equals("");
		if (blankUserName && blankPassword) {
			return "Please enter a user name and password.";
		}
		if (blankUserName) {
			return "Please enter a user name.";
		}
		if (blankPassword) {
			return "Please enter a password.";
		}

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(ADDRESS + "login");
		try {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(2);
			pairs.add(new BasicNameValuePair("data[Account][username]", userName));
			pairs.add(new BasicNameValuePair("data[Account][password]", password));

			httpPost.setEntity(new UrlEncodedFormEntity(pairs));

			XMLReader reader = getParser().getXMLReader();

			LoginHandler handler = new LoginHandler();
			reader.setContentHandler(handler);

			String response = httpClient.execute(httpPost, new BasicResponseHandler());
			InputSource input = new InputSource();
			input.setCharacterStream(new StringReader(response));
			reader.parse(input);

			user = handler.getUser();

			if (user == null) {
				return "Authentication failed.";
			}

			return user;

		} catch (Exception e) {
			Log.e("TheLuvExchange", "WebServiceError", e);
			return "Problem connecting to login service.";
		}
	}

	/**
	 * Static method to provide back a list of City objects received from the Web service
	 * @return
	 */
	public static List<City> getCities() {

		// List of cities to be populated and returned
		List<City> cities = null;

		try {
			// URL object used to create a connection to the cities XML page
			URL url = new URL(ADDRESS + "cities");

			// SAX XMLReader object used for parsing the XML file
			XMLReader reader = getParser().getXMLReader();

			// SAXHandler class used for parsing the XML
			CityHandler handler = new CityHandler();

			// Set the XMLReader to use the SAXHandler for parsing rules
			reader.setContentHandler(handler);

			// Run the parsing
			reader.parse(new InputSource(url.openStream()));

			// Receive the list of City objects from the parse
			cities = handler.getCityData();

		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}

		// Return the list of cities
		return cities;
	}
	
	/**
	 * Returns a list of Picks that represent restaurants given a user and city.
	 * The return value will be null if the service call failed.
	 * @param user
	 * @param city
	 * @return
	 */
	public static List<Pick> getRestaurants(User user, City city) {
		URL url = null;
		try {
			// URL object used to create a connection to the cities XML page
			url = new URL(ADDRESS + "restaurants/" + city.getId() 
					+ "/sort:Pick.rating_count/direction:desc/viewer_id:" + user.getUserId());
		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}
		return getPicks(url);
	}
	
	/**
	 * Returns a list of Picks that represent things to do given a user and city.
	 * The return value will be null if the service call failed.
	 * @param user
	 * @param city
	 * @return
	 */
	public static List<Pick> getThings(User user, City city) {
		URL url = null;
		try {
			// URL object used to create a connection to the cities XML page
			url = new URL(ADDRESS + "things/" + city.getId() 
					+ "/sort:Pick.rating_count/direction:desc/viewer_id:" + user.getUserId());
		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}
		return getPicks(url);
	}
	
	/**
	 * Returns a list of Picks that represent airport eats given a user and city.
	 * The return value will be null if the service call failed.
	 * @param user
	 * @param city
	 * @return
	 */
	public static List<Pick> getAirportEats(User user, City city) {
		URL url = null;
		try {
			// URL object used to create a connection to the cities XML page
			url = new URL(ADDRESS + "airports/" + city.getId() 
					+ "/sort:Pick.rating_count/direction:desc/viewer_id:" + user.getUserId());
		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}
		return getPicks(url);
	}
	
	/*
	 * Used by other methods, not for public access
	 */
	private static List<Pick> getPicks(URL url) {
		// List of cities to be populated and returned
		List<Pick> picks = null;

		try {
			// SAX XMLReader object used for parsing the XML file
			XMLReader reader = getParser().getXMLReader();

			// SAXHandler class used for parsing the XML
			PickHandler handler = new PickHandler();

			// Set the XMLReader to use the SAXHandler for parsing rules
			reader.setContentHandler(handler);

			// Run the parsing
			reader.parse(new InputSource(url.openStream()));

			// Receive the list of City objects from the parse
			picks = handler.getPickData();

		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}
		return picks;
	}
	
	/**
	 * This returns a list of Ratings for a given Pick.
	 * The return value will be null if the service call failed.
	 * @param pick
	 * @return
	 */
	public static List<Rating> getRatings(Pick pick) {
		List<Rating> ratings = null;

		try {
			// URL object used to create a connection to the cities XML page
			URL url = new URL(ADDRESS + "pick/" + pick.getId() 
					+ "/sort:Pick.rating_count/direction:desc");

			// SAX XMLReader object used for parsing the XML file
			XMLReader reader = getParser().getXMLReader();

			// SAXHandler class used for parsing the XML
			RatingHandler handler = new RatingHandler();

			// Set the XMLReader to use the SAXHandler for parsing rules
			reader.setContentHandler(handler);

			// Run the parsing
			reader.parse(new InputSource(url.openStream()));

			// Receive the list of City objects from the parse
			ratings = handler.getRatingData();

		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}

		return ratings;
	}
	
	/**
	 * This method takes inputs of a user, city, pick, comment, rating, and discount.
	 * It returns an Object which is either an instanceof Rating or String.
	 * If a Rating is returned, then the post was successful.
	 * If the result is a String, then its an error message describing why it failed.
	 * @param user
	 * @param city
	 * @param pick
	 * @param comment
	 * @param rating
	 * @param discount
	 * @return
	 */
	public static Object postRating(User user, City city, Pick pick, String comment,
			int rating, boolean discount) {
		
		Rating vote = new Rating(pick);
		vote.setBody(comment);
		vote.setViewerRating(Integer.toString(rating));
		vote.setDiscounts(discount ? "1" : "0");
		
		HttpClient httpClient = new DefaultHttpClient();
		
		StringBuilder address = new StringBuilder(ADDRESS);
		address.append("picks_add?user_id=");
		address.append(user.getUserId());
		address.append("&code=");
		address.append(user.getCode());
		
		HttpPost httpPost = new HttpPost(address.toString());
		try {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(2);
			pairs.add(new BasicNameValuePair("data[Pick][name]", vote.getName()));
			pairs.add(new BasicNameValuePair("data[Pick][body]", vote.getBody()));
			pairs.add(new BasicNameValuePair("data[Pick][location]", vote.getLocation()));
			pairs.add(new BasicNameValuePair("data[Pick][near]", null)); // ?
			pairs.add(new BasicNameValuePair("data[Pick][address]", vote.getAddress()));
			pairs.add(new BasicNameValuePair("data[Pick][phone]", vote.getPhone()));
			pairs.add(new BasicNameValuePair("data[Pick][latitude]", vote.getLatitude()));
			pairs.add(new BasicNameValuePair("data[Pick][longitude]", vote.getLongitude()));
			pairs.add(new BasicNameValuePair("data[Pick][discounts]", vote.getDiscounts()));
			pairs.add(new BasicNameValuePair("data[Pick][category]", vote.getCategory()));
			pairs.add(new BasicNameValuePair("data[Pick][parent_id]", vote.getId()));
			pairs.add(new BasicNameValuePair("data[Pick][city_id]", city.getId()));
			pairs.add(new BasicNameValuePair("data[Pick][rating]", vote.getViewerRating()));

			httpPost.setEntity(new UrlEncodedFormEntity(pairs));

			XMLReader reader = getParser().getXMLReader();

			PostHandler handler = new PostHandler();
			reader.setContentHandler(handler);

			String response = httpClient.execute(httpPost, new BasicResponseHandler());
			InputSource input = new InputSource();
			input.setCharacterStream(new StringReader(response));
			reader.parse(input);

			String result = handler.getResult();
			
			return result.equals("success") ? vote : result;

		} catch (Exception e) {
			Log.e("TheLuvExchange", "WebServiceError", e);
			return "Error: " + e;
		}
	}
	
	/*
	edited by Shibani Chadha 3/29 added getWeather
	*/
	public static WeatherSet getWeather(City city) {
		URL url=null;
		WeatherSet weather = null;

		try {
			
			String queryString="http://free.worldweatheronline.com/feed/weather.ashx?q="+city.getName()+"&format=xml&num_of_days=4&key=359fc57879001200121303";
			// URL object used to create a connection to the weather's XML page
			url = new URL(queryString.replace(" ", "%20"));
			// SAX XMLReader object used for parsing the XML file
			XMLReader reader = getParser().getXMLReader();

			// SAXHandler class used for parsing the XML
			WeatherHandler handler = new WeatherHandler();

			// Set the XMLReader to use the SAXHandler for parsing rules
			reader.setContentHandler(handler);

			// Run the parsing
			reader.parse(new InputSource(url.openStream()));

			// Receive the weather objects from the parse
			weather= handler.getWeatherData();

		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}

		// Return the weather
		return weather;
	}
} 
