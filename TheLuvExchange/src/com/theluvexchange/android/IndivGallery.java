package com.theluvexchange.android;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;


/**
 * @author Android Developers Gallery Tutorial, edit by Matt Glasert
 * 
 * Re-did the gallery for a gridview 3/19/12
 */
public class IndivGallery extends Activity
{


	/*@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));

		gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(IndivGallery.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gallery);

	    GridView gridview = (GridView) findViewById(R.id.gallery);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(IndivGallery.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	}
}