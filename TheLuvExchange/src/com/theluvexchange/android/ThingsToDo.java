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
 * Activity to set up the custom list view of things and Bars
 * 
 * 
 */
 
public class ThingsToDo extends Activity {
	 
	private TheLuvExchange application = null;
	private List<Pick> thingToDoList = null;
	private User user;
	private City city;
		
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.picks);
	        
	       application = (TheLuvExchange)this.getApplication();
	       thingToDoList  = new ArrayList<Pick>();
	        
	        ListView listThings = (ListView)findViewById(R.id.picksList);
	        
//	        Log.d("ThingsToDo.java", "testing");
	        
	        user = application.getUser();
	        city = application.getCity();
	        
	     // Call the WebService.getthings() method to populate the cities list.
	        thingToDoList.addAll(WebService.getThings(user, city));
	        
	        
	     
	        
	        listThings.setAdapter(new ThingsToDoAdapter());
	        listThings.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	               
	 }
	 
	 private class ThingsToDoAdapter extends ArrayAdapter<Pick> {

		 public ThingsToDoAdapter() {
			super(ThingsToDo.this, R.layout.pickrow, thingToDoList);
			
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
			
			
			myViewHolder.populateFrom(thingToDoList.get(position));
			
			
			return row;



			
			
		}
		
		class ViewHolder {
			TextView textViewNumber = null;
			TextView textViewName = null;
			TextView textViewAddress = null;
			TextView textViewPhoneNumber = null;
			RatingBar rating = null;
			
			public ViewHolder (View row){
				textViewNumber = (TextView) row.findViewById(R.id.textViewPickSerialNumber);
				textViewAddress = (TextView) row.findViewById(R.id.textViewPickAddress);
				textViewName = (TextView) row.findViewById(R.id.textViewPickName);
				textViewPhoneNumber = (TextView) row.findViewById(R.id.textViewPickPhoneNumber);
				rating = (RatingBar) row.findViewById(R.id.ratingBarPicks);

				 
			}
			
			public void populateFrom(Pick thing){
				textViewAddress.setText(thing.getAddress());
				textViewName.setText(thing.getName());
				textViewPhoneNumber.setText(thing.getPhone());
				textViewNumber.setText(Integer.toString(thing.getSerialNumber()));
				rating.setRating(Integer.parseInt(thing.getRatingAverage()));
			}
			
		}
		 
		 
		 
	 }

}


