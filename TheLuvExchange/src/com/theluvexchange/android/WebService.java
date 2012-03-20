package com.theluvexchange.android;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

/**
 * @author Tristan Maschke
 * 
 * This class is meant to serve as the base for providing web service calls to
 * the rest of the program.
 * 
 * Implementation as of 3/5/12 includes just a static method for getting the list of cities
 */
public class WebService {
	// Base URL is always going to start with this
	private static final String ADDRESS = "http://www.theluvexchange.com/iphone/";

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

	public static Object login(String userName, String password) {
		User user = null;

		boolean blankUserName = userName == null || userName.equals("");
		boolean blankPassword = password == null || password.equals("");
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
		HttpPost httpPost = new HttpPost("http://www.theluvexchange.com/iphone/login");
		try {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(2);
			pairs.add(new BasicNameValuePair("data[Account][username]", userName));
			pairs.add(new BasicNameValuePair("data[Account][password]", password));

			httpPost.setEntity(new UrlEncodedFormEntity(pairs));

			XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();

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
			XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();

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

public static List<Restaurant> getRestaurants(User user, City city) {
		
		// List of cities to be populated and returned
		List<Restaurant> restaurants = null;
		
		try {
			// URL object used to create a connection to the cities XML page
			URL url = new URL(ADDRESS + "restaurants/" + city.getId() 
					+ "/sort:Pick.rating_count/direction:desc/viewer_id:" + user.getUserId());
			
			// SAX XMLReader object used for parsing the XML file
			XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			
			// SAXHandler class used for parsing the XML
			RestaurantHandler handler = new RestaurantHandler();
			
			// Set the XMLReader to use the SAXHandler for parsing rules
			reader.setContentHandler(handler);
			
			// Run the parsing
			reader.parse(new InputSource(url.openStream()));
			
			// Receive the list of City objects from the parse
			restaurants = handler.getRestaurantData();
			
		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}
		
		
		return restaurants;
	}	

} 
