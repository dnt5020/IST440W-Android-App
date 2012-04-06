package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * @author Shibani Chadha
 * 
 * This is the Main Menu activity. The user can click one of the several options.
 * 
 * 
 * */
public class CityMenu extends Activity {
	private Activity activity = this;
	 
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citymenu);
        // Button just to give a way to get to the Weather activity for testing
        ImageButton weatherButton = (ImageButton)findViewById(R.id.weatherImageButton); 
     // Listener waits for a click on cities button
        weatherButton.setOnClickListener(toWeather);
        
        ImageButton cityGallerybutton = (ImageButton)findViewById(R.id.imageButtonCityGallery); 
        // Listener waits for a click on cities button
        cityGallerybutton.setOnClickListener(toGallery);
        
        ImageButton thingsToDobutton = (ImageButton)findViewById(R.id.imageButtonThingsToDo); 
        // Listener waits for a click on ThingsToDo button
        thingsToDobutton.setOnClickListener(toThingsToDo);
        
        ImageButton restaurantsButton = (ImageButton)findViewById(R.id.imageButtonRestaurants); 
        // Listener waits for a click on ThingsToDo button
        restaurantsButton.setOnClickListener(toRestaurants);
    }
 // On click on cities button, opens Cities activity
    private View.OnClickListener toRestaurants = new View.OnClickListener() {
		public void onClick(View view) {
			startActivity(new Intent(activity, Restaurants.class));
		}
    };
    
    // On click on cities button, opens Cities activity
    private View.OnClickListener toWeather = new View.OnClickListener() {
		public void onClick(View view) {
			startActivity(new Intent(activity, Weather_Main.class));
		}
    };
    
   // On click on cities button, opens Cities activity
   private View.OnClickListener toGallery = new View.OnClickListener() {
		public void onClick(View view) {
			startActivity(new Intent(activity, IndivGallery.class));
		}
   };
   
   // On click on cities button, opens Cities activity
   private View.OnClickListener toThingsToDo = new View.OnClickListener() {
		public void onClick(View view) {
			startActivity(new Intent(activity, ThingsToDo.class));
		}
   };
   
}