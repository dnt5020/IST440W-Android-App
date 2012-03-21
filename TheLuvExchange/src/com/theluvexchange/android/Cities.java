package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
 


/**
 * @author Tristan Maschke
 * 
 * This Activity simply creates a list of cities populated from the web service.
 * 
 * edited by Shibani Chadha 3/20/12 cities now displayed in pop up window.
 */
public class Cities extends Activity {
	Dialog listDialog;
	private Activity activity = this;
	// cities tracks the list of cities
	List<City> cities = new ArrayList<City>();
	
	// city holds a current city
	City city = null;
	
	// adapter is used to populate the ListView with the city names
	CityAdapter adapter = null;
	
	/**
	 * onCreate is called when the Activity is launched.
	 * This creates the list and calls the WebService.getCities() method to populate it.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			// Need to call the superclass constructor first
			super.onCreate(savedInstanceState);
			
			
			// Use the cities XML layout for this Activity
			//setContentView(R.layout.cities);
			listDialog = new Dialog(this);
	        listDialog.setTitle("Select City");
	        // used an inflator to pop the window out
	         LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         View v = li.inflate(R.layout.cities, null, false);
	         listDialog.setContentView(v);
	         listDialog.setCancelable(true);
	        // add the listview to the dialog 
	         ListView list1 = (ListView) listDialog.findViewById(R.id.cities);
	         list1.setOnItemClickListener(new OnItemClickListener(){
	        	 public void onItemClick(AdapterView<?> parent, View view,
	        	          int position, long id) {
	        	        
	        		 startActivity(new Intent(activity, CityMenu.class));// When clicked, go to city menu page       	        
	        	 }
	         
	         });
	         //now that the dialog is set up, it's time to show it
	         listDialog.show();
			
			// Get the ListView from the cities layout
		//ListView list = (ListView)layout.findViewById(R.id.cities);
			
			// Instantiate the adapter for populating the ListView
			adapter = new CityAdapter();
			
			// Set the ListView to use the adapter
			list1.setAdapter(adapter);
			
			// Call the WebService.getCities() method to populate the cities list.
			cities.addAll(WebService.getCities());
			
		} catch (Exception e) {
			// Catch any errors and display them on LogCat
			Log.e("TheLuvExchange", "CitiesError", e);
		}
	}
	/*public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
    {
 
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete item "+arg2)
                   .setPositiveButton("OK ", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       System.out.println("OK CLICKED");
 
                   }
               });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                 dialog.dismiss();
                 listDialog.cancel();
 
               }
           });
 
        AlertDialog alert = builder.create();
        alert.setTitle("Information");
        alert.show();
    }
*/
	/**
	 * This class is used to create the adapter for populating the ListView
	 */
	class CityAdapter extends ArrayAdapter<City> {
		
		/**
		 * Constructor calls the supertype constructor.
		 * Pass the cityrow layout to use for displaying each city in the list.
		 */
		CityAdapter() {
			super(Cities.this, R.layout.cityrow, cities);
		}
		
		/**
		 * getView is called when the row views are added to the ListView
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// If the row was already created before, we'll receive it here
			View row = convertView;
			
			// CityHolder is used to have an object that holds information to show in the row view
			CityHolder holder = null;
			
			// If this row wasn't created before, we'll create it here
			if (row == null) {
				
				// inflater will be used to create Views from the cityrow layout
				LayoutInflater inflater = getLayoutInflater();
				
				// Create a View object from the cityrow layout
				row = inflater.inflate(R.layout.cityrow, parent, false);
				
				// Create a CityHolder object which will set the name to show in the view
				holder = new CityHolder(row);
				
				// Tag the view to access again later if need be
				row.setTag(holder);
				
			} else {
				// If this row was previously created, get it back by its tag
				holder = (CityHolder)row.getTag();
			}
			
			// Populate the holder with a city
			holder.populateFrom(cities.get(position));
			
			// Return the row view
			return row;
		}
	}
	
	/**
	 * CityHolder is a simple class that just holds the information used to populate a row.
	 * As of 3/6/12, it's just holding a name and isn't well utilized.
	 */
	static class CityHolder {
		
		// name TextView
		private TextView name = null;
		
		// When the CityHolder is created, identify its associated name TextView
		CityHolder(View row) {
			name = (TextView)row.findViewById(R.id.cityName);
		}
		
		// Populate the TextView with the city name
		void populateFrom(City city) {
			name.setText(city.getName());
		}
	}
}
