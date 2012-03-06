package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TheLuvExchange extends Activity {
    private Activity activity = this;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// Main layout screen set up
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Button just to give a way to get to the Cities activity for testing
        Button citiesButton = (Button)findViewById(R.id.citiesButton);
        
        // Listener waits for a click on cities button
        citiesButton.setOnClickListener(toCities);
    }
    
    // On click on cities button, opens Cities activity
    private View.OnClickListener toCities = new View.OnClickListener() {
		public void onClick(View view) {
			startActivity(new Intent(activity, Cities.class));
		}
    };
}