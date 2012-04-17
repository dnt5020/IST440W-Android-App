package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
 
public class FullImageActivity extends Activity {
 
	private User user;
	private City city;
	private AlbumPhoto photo;
	private String filename;
	private RatingBar rateBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
 
        // get intent data
        Intent i = getIntent();
 
        // Selected image id
        int position = i.getExtras().getInt("id");
        
        Button submitButton = (Button)findViewById(R.id.Submit);
        rateBar = (RatingBar) findViewById(R.id.ratingBar1);

		
     
        try
        {
        filename = i.getExtras().getString("photo");
        submitButton.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) {
				WebService.postRating(user, photo, (int)rateBar.getRating());
				
			}
		});
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageDrawable(WebService.getFullSizeImage(filename));
        }
        catch (Exception e)
		{
			Log.e("fullImageError2", "error2", e);
		}
        
    }
 
}