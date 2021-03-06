package com.theluvexchange.android;

import android.R.drawable;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Niranjan Singh
 * email - developer.nir@gmail.com
 * 
 *         Main Menu Acitvity
 * 
 * 
 * */
public class CityMenu extends Activity {
	private Activity activity = this;
	private City city;
	private TheLuvExchange application = null;

	private ArrayList<HashMap<String, Object>> menuList;
	private static final String MENUITEM = "Menu Item";
	private static final String RESOURCE_ID = "RESOURCE_ID";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citymenu);

		application = (TheLuvExchange) this.getApplication();
		city = application.getCity();

		// Set the city photo
		ImageView imageViewCityPhoto = (ImageView) findViewById(R.id.imageViewCityPhoto);
		imageViewCityPhoto.setImageDrawable(WebService.getCityPhoto(city));

		// Set Header to selected city name
		TextView cityName = (TextView) findViewById(R.id.header);
		cityName.setText(city.getName());

		menuList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashMap;

		// HashMap and key Values of list
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "Restaurants & Clubs");
		hashMap.put(RESOURCE_ID, R.drawable.restaurants_clubs);
		menuList.add(hashMap);

		// HashMap and key Values of list
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "Things To Do");
		hashMap.put(RESOURCE_ID, R.drawable.things_to_do);
		menuList.add(hashMap);

		// HashMap and key Values of list
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "Airport Eats");
		hashMap.put(RESOURCE_ID, R.drawable.airport_eats);
		menuList.add(hashMap);
		

		// HashMap and key Values of list
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "Deals of the Day");
		hashMap.put(RESOURCE_ID, R.drawable.dealsoftheday);
		menuList.add(hashMap);

		// HashMap and key Values of list
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "City Gallery");
		hashMap.put(RESOURCE_ID, R.drawable.gallery);
		menuList.add(hashMap);

		// HashMap and key Values of list
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "Weather");
		hashMap.put(RESOURCE_ID, R.drawable.weather);
		menuList.add(hashMap);

		// HashMap and key Values of list
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "Who's In Town");
		hashMap.put(RESOURCE_ID, R.drawable.whos_in_town);
		menuList.add(hashMap);
		
		hashMap = new HashMap<String, Object>();
		hashMap.put(MENUITEM, "Buy/Sell/Rent");
		hashMap.put(RESOURCE_ID, R.drawable.airport_eats);
		menuList.add(hashMap);

		ListView listViewMenuList = (ListView) findViewById(R.id.menuList);

		listViewMenuList.setAdapter(new MenuAdapter(menuList, this));
		listViewMenuList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		// Listener to handle click of an Item in the List
		listViewMenuList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String itemClicked = ((String) menuList.get(position).get(
						MENUITEM));
				if (itemClicked.equalsIgnoreCase("Things To Do")
						|| itemClicked.equalsIgnoreCase("Restaurants & Clubs")
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

				} else if (((String) menuList.get(position).get(MENUITEM))
						.equalsIgnoreCase("Who's In Town")) {

					// Add activity here
					startActivity(new Intent(activity, Whos_in_town.class));

				} else if (((String) menuList.get(position).get(MENUITEM))
						.equalsIgnoreCase("Buy/Sell/Rent")) {
					startActivity(new Intent(activity, BuySellRentActivity.class));
				} else if (((String) menuList.get(position).get(MENUITEM))
						.equalsIgnoreCase("Deals of the Day")) {
					Intent intent = new Intent(Intent.ACTION_VIEW,
							Uri.parse("http://theluvexchange.com/deals/dealsoftheday.html"));
					startActivity(intent);
				}


			}
		});

	}

	private class MenuAdapter extends BaseAdapter {
		private ArrayList<HashMap<String, Object>> menuList;
		private LayoutInflater layoutInflater;

		public MenuAdapter(ArrayList<HashMap<String, Object>> menuList,
				Context context) {

			this.menuList = menuList;
			layoutInflater = LayoutInflater.from(context);

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

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder myViewHolder = null;

			if (convertView == null) {
				convertView = layoutInflater
						.inflate(R.layout.citymenurow, null);

				myViewHolder = new ViewHolder();
				myViewHolder.textViewMenuItemName = (TextView) convertView
						.findViewById(R.id.textViewMenuItemName);
				myViewHolder.imageViewMenuItem = (ImageView) convertView
						.findViewById(R.id.imageViewMenuItem);

				convertView.setTag(myViewHolder);

			} else {
				myViewHolder = (ViewHolder) convertView.getTag();
			}

			myViewHolder.textViewMenuItemName.setText((String) menuList.get(
					position).get(MENUITEM));
			myViewHolder.imageViewMenuItem.setImageResource((Integer) menuList
					.get(position).get(RESOURCE_ID));

			return convertView;
		}

		class ViewHolder {
			TextView textViewMenuItemName = null;
			ImageView imageViewMenuItem = null;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options_menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.itemAbout:
			/*
			 * Note by: Pranav
			 * Description: Added call to About class
			 * --Check call
			 */
			Intent i = new Intent(this, AboutApp.class);
			startActivity(i);

			break;
		case R.id.itemLogout:

			
			SharedPreferences savedUser = getSharedPreferences("Login", MODE_PRIVATE);
			Editor editor = savedUser.edit();
			
			User user = application.getUser();
			user.clear(editor);
			application.setUser(null); 
			application.setCity(null);
			
//			editor.clear();
//			editor.commit();
			
			startActivity(new Intent(activity, Login.class));

			break;
		case R.id.itemChangeCity:

			Intent intent = new Intent(activity, Login.class);

			// Pass Pick to the Login activity to display the cities pop up
			intent.putExtra("ShowCity", true);
			startActivity(intent);
		}
		return false;
	}

}
