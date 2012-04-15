package com.theluvexchange.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


/**
 * @author Android Developers Gallery Tutorial, edit by Matt Glasert
 * 
 * Re-did the gallery for a gridview 3/19/12
 */
public class IndivGallery extends Activity
{
	 private Activity activity = this;
	 private TheLuvExchange application = null;
	 private City city;


	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gallery);
	    
	    application = (TheLuvExchange)this.getApplication();
        city = application.getCity();
	    
	    // Set Header to selected city name
        TextView cityName = (TextView)findViewById(R.id.header);
        cityName.setText(city.getName());
  

	    GridView gridview = (GridView) findViewById(R.id.gallery);
	    gridview.setAdapter(new ImageAdapter(this));
	    
        Button addPhotoButton = (Button)findViewById(R.id.button1); 
        
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
    			startActivity(new Intent(activity, ImageUploader.class));
    		}
        });
        
     
    		
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {
 
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
	        }
	    }
	    
	    		
	    		);
	}
}