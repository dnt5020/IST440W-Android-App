package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PickVote extends Activity {

	private TheLuvExchange application = null;
	private User user;
	private City city;

	private Pick pickSelected = null;
	
	private Activity activity = this;
	
	private Button submit;
	private TextView restaurantName;
	private TextView title;
	private RatingBar rateBar;
	private EditText comment;
	private CheckBox discount;
	


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.postcomment);

		application = (TheLuvExchange) this.getApplication();
		
        user = application.getUser();
		city = application.getCity();

		// Set Header to selected city name
		TextView cityName = (TextView) findViewById(R.id.header);
		cityName.setText(city.getName());

		// get the selected Pick passed through the intent
		pickSelected = (Pick) getIntent().getSerializableExtra("Pick");

		submit = (Button) findViewById(R.id.buttonSubmit);
		restaurantName = (TextView) findViewById(R.id.textViewCommentPickName);
		title = (TextView) findViewById(R.id.textViewCommentTitle);
		rateBar = (RatingBar) findViewById(R.id.ratingBarCommentPick);
		comment = (EditText) findViewById(R.id.editTextPickComment);
		discount = (CheckBox) findViewById(R.id.checkBoxDiscount);
		

		title.setText(getIntent().getCharSequenceExtra("Title"));
		restaurantName.setText(pickSelected.getName());
		
		// Click Listener for submit button
		submit.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				
				if(validateFields()){
					
					if(WebService.postRating(user, city, pickSelected, comment.getText().toString(), (int)rateBar.getRating(), discount.isChecked()) instanceof Object){
						 // Intent to start PickComments activity
			       		 Intent intent = new Intent(activity, PickComments.class);
			       		// Pass Pick to the PickComments activity
			       		 intent.putExtra("Pick", pickSelected);
			       		 intent.putExtra("Title", getIntent().getCharSequenceExtra("Title"));
			       		 startActivity(intent);
					}	else {
						Toast.makeText(activity, "Could not post comment",
								Toast.LENGTH_LONG).show();
					}
					 
					
					
		       		 
				} else {
					Toast.makeText(activity, "Please enter a comment",
							Toast.LENGTH_LONG).show();
				}
				
				
			}

		});

	}

	private boolean validateFields() {
		
		if(comment.getText().length() == 0)
			return false;
		else
			return true;
	}

}