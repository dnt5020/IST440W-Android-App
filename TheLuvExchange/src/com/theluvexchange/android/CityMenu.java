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
	// private Activity activity = this;
	 
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citymenu);
        
    }
}