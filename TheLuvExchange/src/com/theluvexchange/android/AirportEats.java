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
 * @author Pranav Shirodkar
 * 
 * Activity to set up the custom list view of Restaurants and Bars
 * 
 * 
 */

public class AirportEats extends Activity {
	
	private TheLuvExchange application = null;
	private List<Pick> airportEatsList = null;
	private User user;
	private City city;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.airporteats);
	        
	       application = (TheLuvExchange)this.getApplication();
	       airportEatsList  = new ArrayList<Pick>();
	        
	        ListView listAirportEats = (ListView)findViewById(R.id.airportEatsList);
	        
	        Log.d("AirportEats.java", "just before user ");
	        
	        user = application.getUser();
	        city = application.getCity();
	        
	     // Call the WebService.getAirportEats() method to populate the cities list.
	     	airportEatsList.addAll(WebService.getAirportEats(user, city));
	        listAirportEats.setAdapter(new AirportEatsAdapter());
	        listAirportEats.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	               
	 }
	 
	 private class AirportEatsAdapter extends ArrayAdapter<Pick> {

		 public AirportEatsAdapter() {
			super(AirportEats.this, R.layout.airporteatsrow, airportEatsList);
			
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
				row = layoutInflater.inflate(R.layout.airporteatsrow, null);
				
				myViewHolder = new ViewHolder(row);
				
				row.setTag(myViewHolder);
				
			} else {
				myViewHolder = (ViewHolder) row.getTag();
			}
			
			
			myViewHolder.populateFrom(airportEatsList.get(position));
			
			
			return row;



			
			
		}
		
		class ViewHolder {
			TextView textViewNumber = null;
			TextView textViewName = null;
			TextView textViewAddress = null;
			TextView textViewPhoneNumber = null;
			RatingBar rating = null;
			
			public ViewHolder (View row){
				textViewNumber = (TextView) row.findViewById(R.id.textViewAirportEatsSerialNumber);
				//Log.d("AirportEats.java", textViewNumber.toString());
				textViewAddress = (TextView) row.findViewById(R.id.textViewAirportEatsAddress);
				//Log.d("AirportEats.java", textViewAddress.toString());
				textViewName = (TextView) row.findViewById(R.id.textViewAirportEatsName);
				//Log.d("AirportEats.java", textViewName.toString());
				textViewPhoneNumber = (TextView) row.findViewById(R.id.textViewAirportEatsPhoneNumber);
				//Log.d("AirportEats.java", textViewPhoneNumber.toString());
				rating = (RatingBar) row.findViewById(R.id.airportEatsRating);
				//Log.d("AirportEats.java", rating.toString());
				 
			}	
			
			public void populateFrom(Pick airportEats){
				//textViewAddress.setText(airportEats.getAddress());
				/*
				 * Author: Pranav Shirodkar
				 * Notes:
				 * There's no address for the airport eats. It's basically suggesting that the
				 * restaurant is on the airport itself. The database doesn't seem to have any records
				 * for it's address or telephone number. Although it does have ratings.
				 */
				textViewAddress.setText(city.getAirport().toString());
				textViewName.setText(airportEats.getName());
				textViewPhoneNumber.setText(airportEats.getPhone());
				textViewNumber.setText(Integer.toString(airportEats.getSerialNumber()));
				rating.setRating(Integer.parseInt(airportEats.getRatingAverage()));
			}
			
		}
		 
		 
		 
	 }

}


