package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * @author Niranjan Singh
 * 
 * This is the Main Menu activity. The user can click one of the several options.
 * 
 * Joe DiZio and Matt Glaser - Edited for Gallery on 3/14/2012 @ 11:19
 */
public class MainMenu extends Activity {
	 private Activity activity = this;
	 
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
     // Button just to give a way to get to the Cities activity for testing
        ImageButton citiesButton = (ImageButton)findViewById(R.id.imageButtonCities); 
        
     // Listener waits for a click on cities button
        citiesButton.setOnClickListener(toCities);
        
     
     // Button to click to Gallery activity for testing
        ImageButton galleryButton = (ImageButton)findViewById(R.id.imageButtonGallery); 
        
      // Listener waits for a click on gallery button
        galleryButton.setOnClickListener(toGallery);
        
        
        
    }
    
 // On click on cities button, opens Cities activity
    private View.OnClickListener toCities = new View.OnClickListener() {
		public void onClick(View view) {
			startActivity(new Intent(activity, Cities.class));
		}
    };
		
// On click on cities button, opens Gallery activity
	private View.OnClickListener toGallery = new View.OnClickListener() {
		public void onClick(View view) {
			startActivity(new Intent(activity, IndivGallery.class));
			}
    };
}
