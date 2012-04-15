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
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

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
		
	private Activity activity = this;

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.picks);
	        
	        
		     // Set the title 
		        TextView textViewPickTitle= (TextView) findViewById(R.id.textViewPickTitle);
		        textViewPickTitle.setText("Things To Do");
	        
	       application = (TheLuvExchange)this.getApplication();
	       thingToDoList  = new ArrayList<Pick>();
	        
	        ListView listViewThings = (ListView)findViewById(R.id.picksList);
	        
//	        Log.d("ThingsToDo.java", "testing");
	        
	        user = application.getUser();
	        city = application.getCity();
	        
	     // Call the WebService.getthings() method to populate the cities list.
	        thingToDoList.addAll(WebService.getThings(user, city));
	        
	        
	        // Set Header to selected city name
	        TextView cityName = (TextView)findViewById(R.id.header);
	        cityName.setText(city.getName());
	        
	        listViewThings.setAdapter(new ThingsToDoAdapter());
	        listViewThings.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        
	        // Listener to handle click of an Item in the List
	        listViewThings.setOnItemClickListener(new OnItemClickListener(){
	        	 public void onItemClick(AdapterView<?> parent, View view,
	        	          int position, long id) {
	        		 
	        		 // Intent to start PickComments activity
	        		 Intent intent = new Intent(activity, PickComments.class);

	        		 
	        		// Pass Pick to the PickComments activity
	        		 intent.putExtra("Pick", thingToDoList.get(position));
	        		 
	        		 TextView textViewPickTitle= (TextView) findViewById(R.id.textViewPickTitle);
	        		 intent.putExtra("Title", textViewPickTitle.getText());

	        		 startActivity(intent);
	        	        
	        	 }
	        });
	               
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
			TextView textViewVoteCount = null;

			RatingBar rating = null;
			
			public ViewHolder (View row){
				textViewNumber = (TextView) row.findViewById(R.id.textViewPickSerialNumber);
				textViewAddress = (TextView) row.findViewById(R.id.textViewPickAddress);
				textViewName = (TextView) row.findViewById(R.id.textViewPickName);
				textViewPhoneNumber = (TextView) row.findViewById(R.id.textViewPickPhoneNumber);
				textViewVoteCount = (TextView) row.findViewById(R.id.textViewVoteCount);
				rating = (RatingBar) row.findViewById(R.id.ratingBarPicks);

				 
			}
			
			public void populateFrom(Pick thing){
				textViewAddress.setText(thing.getAddress());
				textViewName.setText(thing.getName());
				textViewPhoneNumber.setText(thing.getPhone());
				textViewNumber.setText(Integer.toString(thing.getSerialNumber()) + ".");
				textViewVoteCount.setText(thing.getRatingCount());
				rating.setRating(Integer.parseInt(thing.getRatingAverage()));
			}
			
		}
		 
		 
		 
	 }

}

