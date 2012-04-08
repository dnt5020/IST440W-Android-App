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

public class RestaurantComments extends Activity {
	
	private List<Rating> ratingsList = null;
	private Pick pickSelected = null;

	
	private Activity activity = this;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.pickcomments);
	  
	        	// get the selected Pick passed through the intent 
	        	pickSelected = (Pick) getIntent().getSerializableExtra("Pick");
	        
	        	// List containing all the ratings
	        	ratingsList  = new ArrayList<Rating>();
		        ratingsList.addAll(WebService.getRatings(pickSelected));

		        
		        ListView listViewRatings = (ListView)findViewById(R.id.pickCommentsList);
				
		        // Set the title to the restaurant name
		        TextView textViewPickCommentTitle = (TextView) findViewById(R.id.textViewPickCommentTitle);
		        textViewPickCommentTitle.setText(pickSelected.getName());
		        
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
