package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
						Toast.makeText(activity, "Comment successully posted",
								Toast.LENGTH_LONG).show(); 
						
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
					Toast.makeText(activity, "Please enter a comment/rating",
							Toast.LENGTH_LONG).show();
				}
				
				
			}

		});

	}

	private boolean validateFields() {
		
		if(comment.getText().length() == 0 || (int)rateBar.getRating() == 0)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options_menu_other, menu);
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

			
			SharedPreferences savedUser = getPreferences(MODE_PRIVATE);
			Editor editor = savedUser.edit();
			
			User user = application.getUser();
			user.save(editor, false);
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
			
			break;
			
		case R.id.itemMainMenu:
			
			startActivity(new Intent(activity, CityMenu.class));
			break;
		}
		return false;
	}


}