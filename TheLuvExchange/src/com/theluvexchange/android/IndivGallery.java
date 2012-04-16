package com.theluvexchange.android;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;


/**
 * @author Android Developers Gallery Tutorial, edit by Matt Glasert
 * 
 * Re-did the gallery for a gridview 3/19/12
 */
public class IndivGallery extends Activity
{
	private User user;
	private City city;
	private List<AlbumPhoto> photosData;
	private AlbumPhoto photo;

	private Activity activity = this;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		TheLuvExchange application = (TheLuvExchange)getApplication();
		user = application.getUser();
		city = application.getCity();

		GridView gridview = (GridView) findViewById(R.id.gallery);
		photosData = WebService.getPhotos(user, city);
		gridview.setAdapter(new ImageAdapter(this, photosData));


		Button addPhotoButton = (Button)findViewById(R.id.button1); 

		addPhotoButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startActivity(new Intent(activity, ImageUploader.class));
			}
		});





		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				try
				{
					// Sending image id to FullScreenActivity
					Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
					// passing array index
					i.putExtra("id", position);
					i.putExtra("photo", photosData.get(position).getFilename());
					startActivity(i);
				}
				catch (Exception e)
				{
					Log.e("fullImageError", "error", e);
				}
			}
		}


				);
	}
}