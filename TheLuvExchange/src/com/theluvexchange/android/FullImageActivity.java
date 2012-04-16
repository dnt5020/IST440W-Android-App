package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
 
public class FullImageActivity extends Activity {
 
	private User user;
	private City city;
	private String filename;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
 
        // get intent data
        Intent i = getIntent();
 
        // Selected image id
        int position = i.getExtras().getInt("id");
     
        try
        {
        filename = i.getExtras().getString("photo");
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageDrawable(WebService.getFullSizeImage(filename));
        }
        catch (Exception e)
		{
			Log.e("fullImageError2", "error2", e);
		}
        
    }
 
}