package com.theluvexchange.android;


import java.util.ArrayList;
import java.util.List;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class BuySellRentActivity extends Activity{

	private TheLuvExchange application = null;
	private List<BuySellRent> picksList = null;
	private User user;
	private City city;
	
	private Activity activity = this;

		
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        /*setContentView(R.layout.);
	        
	     // Set the title 
	        TextView textViewPickTitle= (TextView) findViewById(R.id.textViewPickTitle);
	        textViewPickTitle.setText("BuySellRent");
	       
	        
	       application = (TheLuvExchange)this.getApplication();
	       picksList  = new ArrayList<BuySellRent>();
	        
	        ListView listViewBuySellRent = (ListView)findViewById(R.id.picksList);
	        
	       // Log.d("ThingsToDo.java", "just before user ");
	        
	        user = application.getUser();
	        city = application.getCity();
	        
	     // Call the WebService.getRestaurants() method to populate the cities list.
	     //	picksList.addAll(WebService.getRestaurants(user, city));
	     	
	     	 * “All items” 
2. "Electronics"
3. “Antiques & Collectibles"
4. "Computer & Accessories"
5. “Clothes & Fashion”
6. “Equipment & Tools”
7. “Health & Beauty”
8. “Furniture & Decor”
9. “Jewelry & Watches”
10. “Sports & Bikes”
11. “Outdoors”
12. “Toys & Games”
13. “Pets”
14. “Miscellaneous”

	     	 * 
	     	 * 
	     	 * 
	        picklist.add("All Items");
	        picklist.add("Electronics");
	        picklist.add("Antiques & Collectibles");
	        picklist.add("Computer & Accessories");
	        picklist.add("Clothes & Fashion");
	        picklist.add("Health & Beauty");
	        picklist.add("Furniture & Decor");
	        picklist.add("Jewelry & Watches");
	        
	      // change in xml layout  
	        
	     
	        
	        listViewBuySellRent.setAdapter(new BuySellRentAdapter());
	        listViewBuySellRent.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        
	        // Listener to handle click of an Item in the List
	        
	        // change to buy sell rent 
	        listViewBuySellRent.setOnItemClickListener(new OnItemClickListener(){
	        	 public void onItemClick(AdapterView<?> parent, View view,
	        	          int position, long id) {
	        		 
	        		 // Intent to start PickComments activity
	        		 
	        		 // this is for the next screen , particular category
	        		 
	        		 Intent intent = new Intent(activity, PickComments.class);

	        		 
	        		// Pass Pick to the PickComments activity
	        		 intent.putExtra("Pick", picksList.get(position));
	        		 
	        		 startActivity(intent);
	        	        
	        	 }
	        });
	               
	 } 
	  
	 // change this to reflect buysellrent
	 private class BuySellRentAdapter extends ArrayAdapter<Pick> {

		 public BuySellRentAdapter() {
			super(BuySellRent.this, R.layout.buysellrentrow, picksList);
			
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
		*//**
		 * private String id;
	private String userId;
	private String name;
	private String body;
	private String price;
	private String categoryId;
	private String cityId;
	private String created;
	private String albumId;
	private String filename;*//*
		class ViewHolder {
			
			TextView textViewName = null;
			TextView textViewPrice = null;
			
//			Button button = null;
			
			public ViewHolder (View row){
			
				textViewName = (TextView) row.findViewById(R.id.textViewPickName);
				textViewPrice = (TextView) row.findViewById(R.id.textViewPickPrice);
				

			}
			 //
			public void populateFrom(BuySellRent buysell){
				
				textViewName.setText(buysell.getName());
				textViewPrice.setText(buysell.getPrice());
				
			

				
			
				 
			}
			
			
			
		}
		 
		 
*/		 
	 }

}