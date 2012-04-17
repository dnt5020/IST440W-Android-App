package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
 * @author Niranjan Singh
 * 
 *         Activity to set up the custom list view of PicksDisplay
 * 
 * 
 */

public class PicksDisplay extends Activity {

	private TheLuvExchange application = null;
	private List<Pick> picksList = null;
	private User user;
	private City city;
	ListView listViewRestaurants;
	TextView textViewPopularity;
	TextView textViewRating;
	TextView textViewLatest;
	String itemClicked;

	private Activity activity = this;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picks);

		itemClicked = getIntent().getStringExtra("MenuSelected");

		TextView textViewPickTitle = (TextView) findViewById(R.id.textViewPickTitle);

		application = (TheLuvExchange) this.getApplication();
		picksList = new ArrayList<Pick>();

		listViewRestaurants = (ListView) findViewById(R.id.picksList);

		user = application.getUser();
		city = application.getCity();

		textViewPopularity = (TextView) findViewById(R.id.textViewPopularity);
		textViewRating = (TextView) findViewById(R.id.textViewRating);
		textViewLatest = (TextView) findViewById(R.id.textViewLatest);

		// By default, the list is sorted by Popularity
		textViewPopularity.setTypeface(null, Typeface.BOLD);
		textViewRating.setTypeface(null, Typeface.NORMAL);
		textViewLatest.setTypeface(null, Typeface.NORMAL);

		// Set the title
		if (itemClicked.equalsIgnoreCase("Things To Do")) {
			textViewPickTitle.setText("Things To Do");

			// Call the WebService.getThings() method to populate the cities
			// list. Default sorting- Popularity
			picksList.addAll(WebService.getThings(user, city));

		} else if (itemClicked.equalsIgnoreCase("Restaurants")) {
			textViewPickTitle.setText("Restaurants");

			// Call the WebService.getRestaurants() method to populate the cities
			// list. Default sorting- Popularity
			picksList.addAll(WebService.getRestaurants(user, city));
		} else if (itemClicked.equalsIgnoreCase("Airport Eats")) {
			textViewPickTitle.setText("Airport Eats");

			// Call the WebService.getAirportEats() method to populate the cities
			// list. Default sorting- Popularity
			picksList.addAll(WebService.getAirportEats(user, city));
		}
		
		// Set Header to selected city name
		TextView cityName = (TextView) findViewById(R.id.header);
		cityName.setText(city.getName());

		RestaurantAdapter restaurantAdapter = new RestaurantAdapter();
		listViewRestaurants.setAdapter(restaurantAdapter);
		listViewRestaurants.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		// Listener to handle click of an Item in the List
		listViewRestaurants.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Intent to start PickComments activity
				Intent intent = new Intent(activity, PickComments.class);

				// Pass Pick to the PickComments activity
				intent.putExtra("Pick", picksList.get(position));

				TextView textViewPickTitle = (TextView) findViewById(R.id.textViewPickTitle);
				intent.putExtra("Title", textViewPickTitle.getText());

				startActivity(intent);

			}
		});

		textViewRating.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picksList.clear();

				// Call the WebService.getRestaurants() method to populate the
				// cities
				// Sorted by rating
				picksList.addAll(WebService.getRestaurants(user, city,
						"rating_avg", "desc"));

				// Refresh list view
				listViewRestaurants.invalidateViews();
				textViewPopularity.setTypeface(null, Typeface.NORMAL);
				textViewRating.setTypeface(null, Typeface.BOLD);
				textViewLatest.setTypeface(null, Typeface.NORMAL);

				// Toast.makeText(activity, "rating clicked",
				// Toast.LENGTH_LONG).show();
			}
		});

		textViewPopularity.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picksList.clear();

				// Call the WebService.getRestaurants() method to populate the
				// cities
				// Sorted by default popularity
				picksList.addAll(WebService.getRestaurants(user, city));

				// Refresh list view
				listViewRestaurants.invalidateViews();
				textViewPopularity.setTypeface(null, Typeface.BOLD);
				textViewRating.setTypeface(null, Typeface.NORMAL);
				textViewLatest.setTypeface(null, Typeface.NORMAL);

				// Toast.makeText(activity, "popularity clicked",
				// Toast.LENGTH_LONG).show();
			}
		});

		textViewLatest.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picksList.clear();

				// Call the WebService.getRestaurants() method to populate the
				// cities
				// Sorted by created
				picksList.addAll(WebService.getRestaurants(user, city,
						"created", "desc"));

				// Refresh list view
				listViewRestaurants.invalidateViews();
				textViewPopularity.setTypeface(null, Typeface.NORMAL);
				textViewRating.setTypeface(null, Typeface.NORMAL);
				textViewLatest.setTypeface(null, Typeface.BOLD);

				// Toast.makeText(activity, "Latest clicked",
				// Toast.LENGTH_LONG).show();
			}
		});

	}

	private class RestaurantAdapter extends ArrayAdapter<Pick> {

		public RestaurantAdapter() {
			super(PicksDisplay.this, R.layout.pickrow, picksList);

		}

		private LayoutInflater layoutInflater = getLayoutInflater();

		public View getView(int position, View convertView, ViewGroup parent) {

			// If the row was already created before, we'll receive it here
			View row = convertView;

			// a ViewHolder keeps references to children views to avoid
			// unnecessary calls
			// to findById() on each row
			ViewHolder myViewHolder = null;

			// If this row wasn't created before, we'll create it here
			if (row == null) {

				// inflater will be used to create Views from the things_row
				// layout
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

			public ViewHolder(View row) {
				textViewNumber = (TextView) row
						.findViewById(R.id.textViewPickSerialNumber);
				textViewAddress = (TextView) row
						.findViewById(R.id.textViewPickAddress);
				textViewName = (TextView) row
						.findViewById(R.id.textViewPickName);
				textViewPhoneNumber = (TextView) row
						.findViewById(R.id.textViewPickPhoneNumber);
				textViewVoteCount = (TextView) row
						.findViewById(R.id.textViewVoteCount);

				rating = (RatingBar) row.findViewById(R.id.ratingBarPicks);

			}

			public void populateFrom(Pick restaurant) {
				
				if(itemClicked.equalsIgnoreCase("Airport Eats")){
					textViewAddress.setText(city.getAirport());
				} else {
					textViewAddress.setText(restaurant.getAddress());
				}
				
				
				textViewName.setText(restaurant.getName());
				textViewPhoneNumber.setText(restaurant.getPhone());
				textViewNumber.setText(Integer.toString(restaurant
						.getSerialNumber()) + ".");
				textViewVoteCount.setText(restaurant.getRatingCount());
				rating.setRating(Integer.parseInt(restaurant.getRatingAverage()));

			}

		}

	}

}