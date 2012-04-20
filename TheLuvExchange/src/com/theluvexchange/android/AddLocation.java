package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddLocation extends Activity {
	private Activity activity = this;
	City city;
	User user;
	private EditText near;
	private EditText comment;
	private EditText name;
	private TheLuvExchange application;
	private int latitude;
	private int longitude;
	private String address;
	private int type;
	private CheckBox discount;
	private RatingBar rating;

	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);

			application = (TheLuvExchange) this.getApplication();

			city = application.getCity();
			user = application.getUser();

			type = getIntent().getExtras().getInt("type");

			if (type != WebService.AIRPORT_EATS) {
				startActivityForResult(new Intent(activity, PickLocation.class), 1);
			}

			comment = (EditText)findViewById(R.id.editTextPlaceComment);
			name = (EditText)findViewById(R.id.editTextPlaceName);
			near = (EditText)findViewById(R.id.editTextPlaceNear);
			discount = (CheckBox)findViewById(R.id.checkBoxDiscount);
			rating = (RatingBar)findViewById(R.id.addPlaceRating);

			setContentView(R.layout.addlocation);
			Button addButton = (Button)findViewById(R.id.btnPlaceSubmit);
			addButton.setOnClickListener(onAdd);
		} catch (Exception e) {
			Log.e("AddLocation", "Error in onCreate()", e);
		}
	}

	private View.OnClickListener onAdd = new View.OnClickListener() {
		public void onClick(View v) {
			try {
				Object result = WebService.postPick(user, city, name.getText().toString(), comment.getText().toString(),
						near.getText().toString(), new Float(rating.getRating()).intValue(), discount.isChecked(), null,
						address, null, latitude, longitude, type);

				if (result instanceof String) {
					Toast.makeText(activity, (String)result, Toast.LENGTH_LONG);
				} else {
					activity.finish();
				}
			} catch (Exception e) {
				Log.e("AddLocation", "Error in onAdd.onClick()", e);
			}
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (resultCode == RESULT_OK && requestCode == 1) {
				Bundle bundle = data.getExtras();
				latitude = bundle.getInt("latitude");
				longitude = bundle.getInt("longitude");
				address = bundle.getString("address");
			} else {
				activity.finish();
			}
		} catch (Exception e) {
			Log.e("AddLocation", "Error in onActivityResult()", e);
		}
	}

	/**
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		if (type != WebService.AIRPORT_EATS) {
			startActivityForResult(new Intent(activity, PickLocation.class), 1);
		}
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
			break;
		case R.id.itemLogout:

			SharedPreferences savedUser = getPreferences(MODE_PRIVATE);
			Editor editor = savedUser.edit();

			User user = application.getUser();
			user.save(editor, false);
			application.setUser(null); 
			application.setCity(null);

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
