package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;
	
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * @author Pranav Shirodkar
 * 
 * Activity to set up the custom list view of Restaurants and Clubs
 * 
 * 
 */
 
public class AirportEats extends Activity {
	
	private TheLuvExchange application = null;
	private List<Pick> picksList = null;
	private User user;
	private City city;
	
	private Activity activity = this;

		
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.picks);
	        
	     // Set the title 
	        TextView textViewPickTitle= (TextView) findViewById(R.id.textViewPickTitle);
	        textViewPickTitle.setText("Airport Eats");
	       
	        
	       application = (TheLuvExchange)this.getApplication();
	       picksList  = new ArrayList<Pick>();
	        
	        ListView listViewAirportEats = (ListView)findViewById(R.id.picksList);
	        
	        Log.d("AirportEats.java", "User Log");
	        
	        user = application.getUser();
	        city = application.getCity();
	        
	     // Call the WebService.getAirportEats() method to populate the cities list.
	     	picksList.addAll(WebService.getAirportEats(user, city));
	        
	        
	     
	        
	        listViewAirportEats.setAdapter(new RestaurantAdapter());
	        listViewAirportEats.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        
	        // Listener to handle click of an Item in the List
	        listViewAirportEats.setOnItemClickListener(new OnItemClickListener(){
	        	 public void onItemClick(AdapterView<?> parent, View view,
	        	          int position, long id) {
	        		 
	        		 // Intent to start PickComments activity
	        		 Intent intent = new Intent(activity, PickComments.class);

	        		 
	        		// Pass Pick to the PickComments activity
	        		 intent.putExtra("Pick", picksList.get(position));
	        		 
	        		 startActivity(intent);
	        	        
	        	 }
	        });
	               
	 } 
	  
	 private class RestaurantAdapter extends ArrayAdapter<Pick> {

		 public RestaurantAdapter() {
			super(AirportEats.this, R.layout.pickrow, picksList);
			
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
				row = layoutInflater.inflate(R.layout.pickrow, null);
				
				myViewHolder = new ViewHolder(row);
				
				row.setTag(myViewHolder);
				
			} else {
				myViewHolder = (ViewHolder) row.getTag();
			}
			
			
			myViewHolder.populateFrom(picksList.get(position));
			
			
			return row;



			
			
		}
		
		class ViewHolder {
			TextView textViewNumber = null;
			TextView textViewName = null;
			TextView textViewAddress = null;
			TextView textViewPhoneNumber = null;
			TextView textViewVoteCount = null;
			RatingBar rating = null;
//			Button button = null;
			
			public ViewHolder (View row){
				textViewNumber = (TextView) row.findViewById(R.id.textViewPickSerialNumber);
				textViewAddress = (TextView) row.findViewById(R.id.textViewPickAddress);
				textViewName = (TextView) row.findViewById(R.id.textViewPickName);
				textViewPhoneNumber = (TextView) row.findViewById(R.id.textViewPickPhoneNumber);
				textViewVoteCount = (TextView) row.findViewById(R.id.textViewVoteCount);

				rating = (RatingBar) row.findViewById(R.id.ratingBarPicks);
//				button = (Button) row.findViewById(R.id.button1);
//				
//				button.setOnClickListener(new Button.OnClickListener() {
//                    public void onClick(View v) {
//                    	
//                    	
//                    	// For testing 
//                    	Toast.makeText(activity, "Button with position - ", Toast.LENGTH_LONG);
//                    }
//                }
//            ); 
				  
			}
			 
			public void populateFrom(Pick restaurant){
				
				//textViewAddress.setText(airportEats.getAddress());
				/*
				 * Author: Pranav Shirodkar
				 * Notes:
				 * There's no address for the airport eats. It's basically suggesting that the
				 * restaurant is on the airport itself. The database doesn't seem to have any records
				 * for it's address or telephone number. Although it does have ratings.
				 */
				
				textViewAddress.setText(city.getAirport().toString());
				textViewName.setText(restaurant.getName());
				textViewPhoneNumber.setText(restaurant.getPhone());
				textViewNumber.setText(Integer.toString(restaurant.getSerialNumber()) + ".");
				textViewVoteCount.setText(restaurant.getRatingCount());
				rating.setRating(Integer.parseInt(restaurant.getRatingAverage()));
				
			

				
			
				 
			}
			
			
			
		}
		 
		 
		 
	 }

}


