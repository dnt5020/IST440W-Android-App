package com.theluvexchange.android;

import java.util.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * @author Niranjan Singh
 * 
 * Activity to set up the custom list view
 * 
 *  -- The code is a mess. Was trying to make it working first. Hardcoded the 3 resaturants to test
 */

// The code is a mess and has to be cleaned up and commented

// Reference - http://chengalva.com/dnn_site/Home/tabid/41/EntryId/83/Android-Create-Custom-ListView-by-adding-an-Image-Icon-and-Rating-Bar.aspx

public class ThingsToDo extends Activity {
	
	private ArrayList <HashMap<String, Object>> thingsList;
	private static final String NUMBER = "Number";
	private static final String NAME = "Name";
	private static final String ADDRESS = "Address";
	private static final String RATING = "Rating";


	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.things_to_do);
	        
	        ListView listThings = (ListView)findViewById(R.id.thingsToDoList);
	        
	        thingsList = new ArrayList<HashMap<String,Object>>();
	        HashMap<String, Object> hashMap;
	        
	        //HashMap and Key, Values of list
	        hashMap = new HashMap<String, Object>();
	        hashMap.put(NUMBER, "1");
	        hashMap.put(NAME, "Sheetz");
	        hashMap.put(ADDRESS, "Queenswood, York");
	        hashMap.put(RATING, 5);
	        
	        thingsList.add(hashMap);
	        
	      //HashMap and Key, Values of list
	        hashMap = new HashMap<String, Object>();
	        hashMap.put(NUMBER, "2");
	        hashMap.put(NAME, "Red Lobster");
	        hashMap.put(ADDRESS, "southQueen, York");
	        hashMap.put(RATING, 3);
	        
	        thingsList.add(hashMap);
	        
	      //HashMap and Key, Values of list
	        hashMap = new HashMap<String, Object>();
	        hashMap.put(NUMBER, "3");
	        hashMap.put(NAME, "Something");
	        hashMap.put(ADDRESS, "Queenswood, York");
	        hashMap.put(RATING, 5);
	        
	        thingsList.add(hashMap);
	        
	        listThings.setAdapter(new myListAdapter(thingsList, this));
	        listThings.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	               
	 }
	 
	 private class myListAdapter extends BaseAdapter {

		 private ArrayList<HashMap<String, Object>> things;
		 private LayoutInflater layoutInflater;
		 
		 public myListAdapter(ArrayList<HashMap<String, Object>> things, Context context) {
			 
			 this.things = things;
			 layoutInflater = LayoutInflater.from(context);
			 
		 }
		 
		public int getCount() {
			return things.size();
		}

		public Object getItem(int position) {
			return things.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			// a ViewHolder keeps references to children views to avoid unnecessary calls 
			// to findById() on each row
			ViewHolder myViewHolder;
			
			if(convertView == null){
				convertView = layoutInflater.inflate(R.layout.things_row, null);
				
				myViewHolder = new ViewHolder();
				myViewHolder.textViewNumber = (TextView) convertView.findViewById(R.id.textViewNumber);
				myViewHolder.textViewAddress = (TextView) convertView.findViewById(R.id.textViewAddress);
				myViewHolder.textViewName = (TextView) convertView.findViewById(R.id.textViewRestaurantName);
				
				convertView.setTag(myViewHolder);
				
			} else {
				myViewHolder = (ViewHolder) convertView.getTag();
			}
			
			myViewHolder.textViewAddress.setText((String) thingsList.get(position).get(ADDRESS));
			myViewHolder.textViewName.setText((String) thingsList.get(position).get(NAME));
			myViewHolder.textViewNumber.setText((String) thingsList.get(position).get(NUMBER));
//			myViewHolder.rating.setRating((Integer) thingsList.get(position).get(RATING));
			
			
			
			return convertView;



			
			
		}
		
		class ViewHolder {
			TextView textViewNumber;
			TextView textViewName;
			TextView textViewAddress;
			RatingBar rating;
			
		}
		 
		 
		 
	 }

}
