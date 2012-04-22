package com.theluvexchange.android;






import android.R.drawable;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;



public class BuySellRentActivityFS extends Activity{

    private Activity activity = this;
	//private TheLuvExchange application = null;
	private City city;
	private TheLuvExchange application = null;
	//private List<BuySellRent> picksList = null;
	private User user;
	//private City city;
	
	

	private ArrayList<HashMap<String, Object>> menuList;
	private static final String MENUITEM = "Menu Item";
	private static final String RESOURCE_ID = "RESOURCE_ID";
		
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.buysellrent);
	        
	     // Set the title 
	        TextView textViewPickTitle= (TextView) findViewById(R.id.header);
	        textViewPickTitle.setText("Buy/Sell/Rent");
	       
	        
	       //application = (TheLuvExchange)this.getApplication();
	      // city = application.getCity();
	      
	      // picksList  = new ArrayList<BuySellRent>();
	        
	     //  ListView listViewBuySellRent = (ListView)findViewById(R.id.picksList);
	        
	       // Log.d("ThingsToDo.java", "just before user ");
	        
	       // user = application.getUser();
	        
	        
	     // Call the WebService.getRestaurants() method to populate the cities list.
	     //	picksList.addAll(WebService.getRestaurants(user, city));


	    	
	        menuList = new ArrayList<HashMap<String, Object>>();
		    HashMap<String, Object> hashMap;
			//HashMap<String, Object> hashMap;
			
	        hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "All items");
		   hashMap.put(RESOURCE_ID, R.drawable.restaurants_clubs);
			menuList.add(hashMap);

			
			// HashMap and key Values of list
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Electronics");
			hashMap.put(RESOURCE_ID, R.drawable.things_to_do);
			menuList.add(hashMap);

			// HashMap and key Values of list
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Antiques & Collectibles");
			hashMap.put(RESOURCE_ID, R.drawable.airport_eats);
			menuList.add(hashMap);

			// HashMap and key Values of list
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Computer & Accessories");
			 hashMap.put(RESOURCE_ID, R.drawable.gallery);
			menuList.add(hashMap);

			// HashMap and key Values of list
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Clothes & Fashion");
			hashMap.put(RESOURCE_ID, R.drawable.weather);
			menuList.add(hashMap);

			// HashMap and key Values of list
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Equipment & Tools");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Health & Beauty");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Furniture & Decor");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Jewelry & Watches");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Sports & Bikes");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Outdoors");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Toys & Games");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Pets");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
			hashMap = new HashMap<String, Object>();
			hashMap.put(MENUITEM, "Miscellaneous");
			hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
			menuList.add(hashMap);
			
	// *****		what is this menulist pointing to ????/
			ListView listViewMenuList = (ListView)findViewById(R.id.menuList);
	        listViewMenuList.setAdapter(new MenuAdapter(menuList, this));
	        listViewMenuList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        
	        // Listener to handle click of an Item in the List
	        
	        // change to buy sell rent 
	        listViewMenuList.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					String itemClicked = ((String) menuList.get(position).get(
							MENUITEM));
					if (itemClicked.equalsIgnoreCase("Things To Do")
							|| itemClicked.equalsIgnoreCase("Restaurants")
							|| itemClicked.equalsIgnoreCase("Airport Eats")) {

						Intent intent = new Intent(activity, PicksDisplay.class);

						// Pass Pick to the PickComments activity
						intent.putExtra("MenuSelected", itemClicked);
						startActivity(intent);

					} else if (((String) menuList.get(position).get(MENUITEM))
							.equalsIgnoreCase("City Gallery")) {
						startActivity(new Intent(activity, IndivGallery.class));

					} else if (((String) menuList.get(position).get(MENUITEM))
							.equalsIgnoreCase("Weather")) {
						startActivity(new Intent(activity, Weather_Main.class));

					} 
					
					else if (((String) menuList.get(position).get(MENUITEM))
							.equalsIgnoreCase("Whos in town")) {

						// Add activity here
						startActivity(new Intent(activity, Whos_in_town.class));

					}
					
					else if (((String) menuList.get(position).get(MENUITEM))
							.equalsIgnoreCase("Buy/Sell/Rent")) {
						startActivity(new Intent(activity, BuySellRentActivityFS.class));
					}

				}
			});

		}
	  
	 // change this to reflect buysellrent
	 private class MenuAdapter extends BaseAdapter {
		 private ArrayList<HashMap<String, Object>> menuList;
		 private LayoutInflater layoutInflater;
			
		 public MenuAdapter(ArrayList<HashMap<String, Object>> menuList,
					Context context) {
			 this.menuList = menuList;
				layoutInflater = LayoutInflater.from(context);

			 
			//super(BuySellRent.this, R.layout.buysellrentrow, menuList);
			
		}

		 public int getCount() {
				return menuList.size();
			}
		 public Object getItem(int position) {
				return menuList.get(position);
			}
		 public long getItemId(int position) {
				return position;
			}
			
			
			
			
		// private LayoutInflater layoutInflater = getLayoutInflater();

		public View getView(int position, View convertView, ViewGroup parent) {
			
			
			ViewHolder myViewHolder = null;
			
			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.buysellrentrow, null);
					
					myViewHolder = new ViewHolder();
				myViewHolder.textViewMenuItemName = (TextView) convertView
						.findViewById(R.id.textViewMenuItemName);
			
			//Take out for right now because I don'thave a image nor a drawable -->
			//	myViewHolder.imageViewMenuItem = (ImageView) convertView
					//	.findViewById(R.id.imageViewMenuItem);

						
						
			convertView.setTag(myViewHolder);
			
			} else {
				myViewHolder = (ViewHolder) convertView.getTag();
			}

			myViewHolder.textViewMenuItemName.setText((String) menuList.get(
					position).get(MENUITEM));
					
					
					
					return convertView;
					}

class ViewHolder {
			TextView textViewMenuItemName = null;
			//ImageView imageViewMenuItem = null;

		}
		
		
		
		
		
	/*public ViewHolder (View row){
			
				textViewMenuItemName = (TextView) row.findViewById(R.id.textViewPickName);
			//	textViewPrice = (TextView) row.findViewById(R.id.textViewPickPrice);
				

			}
			 //
			public void populateFrom(BuySellRent buysell){
				
				textViewName.setText(buysell.getName());
				//textViewPrice.setText(buysell.getPrice());
				
			

			*/	
			
				 
			
			}	
			
			
		
		 
	 
	 }

	
