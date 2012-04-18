package com.theluvexchange.android;


/*
 * Author: Pranav Shirodkar
 * Description: About Dialog
 * Date: 04/18/2012
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;


public class AboutApp extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		TextView aboutText = (TextView)findViewById(R.id.about_content);
		aboutText.setText(Html.fromHtml("<h3>Select City</h3>"+
				"Choose the city that you are currently in or travelling to." +
				"<br><br>" +
				"<h3>Restaurants & Clubs</h3>"+
				"View Restaurants and Clubs around that City." +
				"<br><br>" +
				"<h3>Things To Do</h3>" +
				"View things to do around that city" +
				"<br><br>" +
				"<h3>Airport Eats</h3>" +
				"View places to eat for the airport you are in" +
				"<br><br>" +
				"<h3>Who's in Town</h3>" +
				"Leave and view messages from other members in town in our 36-hour message center" +
				"<br><br>" +
				"<h3>City I-Gallery</h3>" +
				"Upload your city photos from your iPhone, View other member's city photos." +
				"<br><br>" +
				"<h3>Weather</h3>" +
				"View the current temperature and 3-day forecast for any Southwest Airline city." +
				"<br><br>"+
				"<h3>Map Icon</h3>" +
				"Maps chosen pick." +
				"<br><br><br><br>" +
				"Copyright 2012 The Luv Exchange LLC" +
				"<br><br>" +
				"<p>Website and Android application policies, procedures, terms of agreement, privacy policy, copyrights, etc " +
				"can be found at <a href=\"http://www.theluvexchange.com\">www.theluvexchange.com</a>. Visit " +
				"<a href=\"http://www.theluvexchange.com\">www.theluvexchange.com</a> or Email info@theluvexchange.com for more " +
				"detailed information or to submit questions."));
		aboutText.setMovementMethod(new ScrollingMovementMethod());
		
	}

}
