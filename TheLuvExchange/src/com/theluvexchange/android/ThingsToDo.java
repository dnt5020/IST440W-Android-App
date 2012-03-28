package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * @author Niranjan Singh
 * 
 * Activity to set up the custom list view of Restaurants and Bars
 * 
 * 
 */

public class ThingsToDo extends Activity {
	
	private TheLuvExchange application = null;
	private List<Pick> restaurantsList = null;
	private User user;
	private City city;
	
	private int rowCount = 0;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.things_to_do);
	        
	       application = (TheLuvExchange)this.getApplication();
	       restaurantsList  = new ArrayList<Pick>();
	        
	        ListView listThings = (ListView)findViewById(R.id.thingsToDoList);
	        
	        Log.d("ThingsToDo.java", "just before user ");
	        
	        user = application.getUser();
	        city = application.getCity();
	        
	     // Call the WebService.getRestaurants() method to populate the cities list.
	     	restaurantsList.addAll(WebService.getRestaurants(user, city));
	        
	        
	     
	        
	        listThings.setAdapter(new RestaurantAdapter());
	        listThings.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	               
	 }
	 
	 private class RestaurantAdapter extends ArrayAdapter<Pick> {

		 public RestaurantAdapter() {
			super(ThingsToDo.this, R.layout.things_row, restaurantsList);
			
		}

		 private LayoutInflater layoutInflater = getLayoutInflater();

		public View getView(int position, View convertView, ViewGroup parent) {
			
			// If the row was already created before, we'll receive it here
			View row = convertView;
			
			// a ViewHolder keeps references to children views to avoid unnecessary calls 
			// to findById() on each row
			ViewHolder myViewHolder = null;
			
			// If this row wasn't created before, we'll create it here
			if(row == null){

				
				// inflater will be used to create Views from the things_row layout
				row = layoutInflater.inflate(R.layout.things_row, null);
				
				myViewHolder = new ViewHolder(row);
				
				row.setTag(myViewHolder);
				
			} else {
				myViewHolder = (ViewHolder) row.getTag();
			}
			
			
			myViewHolder.populateFrom(restaurantsList.get(position));
			
			
			return row;



			
			
		}
		
		class ViewHolder {
			TextView textViewNumber = null;
			TextView textViewName = null;
			TextView textViewAddress = null;
			TextView textViewPhoneNumber = null;
			RatingBar rating = null;
			
			public ViewHolder (View row){
				textViewNumber = (TextView) row.findViewById(R.id.textViewNumber);
				textViewAddress = (TextView) row.findViewById(R.id.textViewAddress);
				textViewName = (TextView) row.findViewById(R.id.textViewRestaurantName);
				textViewPhoneNumber = (TextView) row.findViewById(R.id.textViewPhoneNumber);
				rating = (RatingBar) row.findViewById(R.id.ratingBar1);
				 
			}
			
			public void populateFrom(Pick restaurant){
				rowCount++;

				textViewAddress.setText(restaurant.getAddress());
				textViewName.setText(restaurant.getName());
				textViewPhoneNumber.setText(restaurant.getPhone());
				
				// This is not the right method to display count, will need to change - Niranjan
				textViewNumber.setText(rowCount + ".");
				rating.setRating(Integer.parseInt(restaurant.getRatingAverage()));
			}
			
		}
		 
		 
		 
	 }

}


