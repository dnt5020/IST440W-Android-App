package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class FullImageActivity extends Activity {

	private User user;
	private City city;
	private AlbumPhoto photo;
	private String filename;
	private RatingBar rateBar;
	private Activity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_image);
		activity = this;

		TheLuvExchange application = (TheLuvExchange)getApplication();
		user = application.getUser();

		// get intent data
		Intent i = getIntent();

		// Selected image id
		int position = i.getExtras().getInt("id");

		final Button submitButton = (Button)findViewById(R.id.Submit);
		rateBar = (RatingBar) findViewById(R.id.ratingBar2);



		try
		{
			filename = i.getExtras().getString("photo");
			final String id = i.getExtras().getString("id");
			String viewerRating = i.getExtras().getString("rating");
			if (viewerRating != null && !viewerRating.trim().equals("") && Integer.parseInt(viewerRating.trim()) > 0) {
				rateBar.setRating(Integer.parseInt(viewerRating));
				submitButton.setEnabled(false);
			} else {
				submitButton.setEnabled(true);
				submitButton.setOnClickListener(new View.OnClickListener() 
				{
					public void onClick(View view) {
						String result = WebService.postRating(user, id, (int)rateBar.getRating());
						if (!result.equals("success")) {
							Toast.makeText(activity, result, Toast.LENGTH_LONG);
							Log.e("Error", "Problem posting rating to photo: " + result);
						} else {
							submitButton.setEnabled(false);
						}
					}
				});
			}
			ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
			imageView.setImageDrawable(WebService.getFullSizeImage(filename));
		}
		catch (Exception e)
		{
			Log.e("fullImageError2", "error2", e);
		}

	}

}