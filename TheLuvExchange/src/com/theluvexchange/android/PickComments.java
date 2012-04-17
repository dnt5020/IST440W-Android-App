package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PickComments extends Activity {

	private List<Rating> ratingsList = null;
	private Pick pickSelected = null;

	private TheLuvExchange application = null;
	private City city;

	TextView textViewRating;
	TextView textViewLatest;
	
	ListView listViewRatings;

	private Activity activity = this;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pickcomments);

		application = (TheLuvExchange)this.getApplication();
		city = application.getCity();

		// Set Header to selected city name
		TextView cityName = (TextView)findViewById(R.id.header);
		cityName.setText(city.getName());

		// get the selected Pick passed through the intent 
		pickSelected = (Pick) getIntent().getSerializableExtra("Pick");
		
		textViewRating = (TextView) findViewById(R.id.textViewRating);
		textViewLatest = (TextView) findViewById(R.id.textViewLatest);


		// List containing all the ratings
		ratingsList  = new ArrayList<Rating>();
		
		// By default, the list is sorted by Rating
		textViewRating.setTypeface(null, Typeface.BOLD);
		textViewLatest.setTypeface(null, Typeface.NORMAL);
		ratingsList.addAll(WebService.getRatings(pickSelected, "rating_avg", "desc"));
				

		listViewRatings = (ListView)findViewById(R.id.pickCommentsList);

		// Set the title to the restaurant name
		TextView textViewPickCommentTitle = (TextView) findViewById(R.id.textViewPickCommentTitle);
		textViewPickCommentTitle.setText(pickSelected.getName());

		Button addVote = (Button)findViewById(R.id.AddVoteButton);
		Button map = (Button)findViewById(R.id.MapButton);


		map.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				String latitude = pickSelected.getLatitude();
				String longitude = pickSelected.getLongitude();

				String url = "geo:";
				String address = pickSelected.getAddress();
				if (latitude == null || longitude == null || latitude.trim().equals("") || longitude.trim().equals("")) {
					if (address == null || address.trim().equals("")) {
						Toast.makeText(activity, "No valid address, map forwarding to center of city.",
								Toast.LENGTH_SHORT).show();
						url = String.format(url + "%s,%s", city.getLat(), city.getLongitude());
					} else {
						url = String.format(url + "%s,%s?q=%s", city.getLat(), city.getLongitude(), Uri.encode(address));
					}
				} else {
					if (address == null || address.trim().equals("")) {
						Toast.makeText(activity, "No valid address, map centering on place location.",
								Toast.LENGTH_SHORT).show();
						url = String.format(url + "%s,%s", pickSelected.getLatitude(), pickSelected.getLongitude());
					} else {
						url = String.format(url + "%s,%s?q=%s", pickSelected.getLatitude(), pickSelected.getLongitude(), Uri.encode(address));
					}
				}
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse(url));
				startActivity(intent);

			}

		});

		addVote.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {

				// Intent to start PickVote activity
				Intent intent = new Intent(activity, PickVote.class);

				// Pass Pick to the PickVote activity
				intent.putExtra("Pick", pickSelected);

				intent.putExtra("Title", getIntent().getCharSequenceExtra("Title"));


				startActivity(intent);

			}

		});

		textViewRating.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ratingsList.clear();

				// the list is sorted by Rating
				textViewRating.setTypeface(null, Typeface.BOLD);
				textViewLatest.setTypeface(null, Typeface.NORMAL);
				ratingsList.addAll(WebService.getRatings(pickSelected, "viewer_rating", "desc"));
						

				// Refresh list view
				listViewRatings.invalidateViews();
			
			}
		});
		
		textViewLatest.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ratingsList.clear();

				// Sorted by Latest
				textViewRating.setTypeface(null, Typeface.NORMAL);
				textViewLatest.setTypeface(null, Typeface.BOLD);
				ratingsList.addAll(WebService.getRatings(pickSelected, "created", "desc"));

				
				// Refresh list view
				listViewRatings.invalidateViews();
				
			}
		});

		// Set the discount available text view
		TextView textViewPickDiscount = (TextView) findViewById(R.id.textViewDiscountBool);
		if(pickSelected.getDiscounts()==null){
			textViewPickDiscount.setText("");	// if not specified
		} else {
			textViewPickDiscount.setText(pickSelected.getDiscounts().equals("1")?"Yes":"No");

		} 

		// Set the location text view
		TextView textViewPickLocation = (TextView) findViewById(R.id.textViewPickLocation);
		textViewPickLocation.setText(pickSelected.getLocation()==null?" ":pickSelected.getLocation());




		listViewRatings.setAdapter(new CommentAdapter());
		listViewRatings.setChoiceMode(ListView.CHOICE_MODE_SINGLE);




		Log.d("restaurant comment", "pick id is - " + getIntent().getExtras().getString("PickID"));
	}

	private class CommentAdapter extends ArrayAdapter<Rating> {

		public CommentAdapter() {
			super(activity, R.layout.pickcommentsrow, ratingsList);

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
				row = layoutInflater.inflate(R.layout.pickcommentsrow, null);

				myViewHolder = new ViewHolder(row);

				row.setTag(myViewHolder);

			} else {
				myViewHolder = (ViewHolder) row.getTag();
			}


			myViewHolder.populateFrom(ratingsList.get(position));


			return row;


		}

		class ViewHolder {
			TextView textViewUserName = null;
			TextView textViewCreated = null;
			TextView textViewBody = null;
			RatingBar ratingBar = null;

			public ViewHolder (View row){
				textViewUserName = (TextView) row.findViewById(R.id.textViewUserName);
				textViewCreated = (TextView) row.findViewById(R.id.textViewCommentDate);
				textViewBody = (TextView) row.findViewById(R.id.textViewUserCommentBody);
				ratingBar = (RatingBar) row.findViewById(R.id.ratingBarViewCommentPick);


			}

			public void populateFrom(Rating rating){
				textViewUserName.setText(rating.getUserName());
				textViewCreated.setText(rating.getCreated());
				textViewBody.setText(rating.getBody());
				ratingBar.setRating(Integer.parseInt(rating.getViewerRating()));

				//				// if rating is not specified, assign 0
				//				ratingBar.setRating(rating.getRatingAverage()==null?0:Integer.parseInt(rating.getRatingAverage()));



			}

		}



	}

}