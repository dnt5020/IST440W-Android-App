package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import com.theluvexchange.android.Cities.CityAdapter;
import com.theluvexchange.android.Cities.CityHolder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Tristan Maschke
 * 
 * This is the Login activity
 */
public class Login extends Activity {
	//private Activity activity;
	SharedPreferences savedUser;
	private EditText userNameField;
	private EditText passwordField;
	private CheckBox rememberMe;
	Dialog listDialog;
	private Activity activity = this;
	//	private View yourView;

	// cities tracks the list of cities
	List<City> cities = new ArrayList<City>();

	// city holds a current city
	City city = null;

	// adapter is used to populate the ListView with the city names
	CityAdapter adapter = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		// If intent asking to display city popup. Default is false
		if(getIntent().getBooleanExtra("ShowCity", false)){
			showCityPopUp();
		} else {
		
			activity = this;
			savedUser = getPreferences(MODE_PRIVATE);
			boolean ping = WebService.ping();
			if (ping && savedUser.getBoolean("remember", false)) {
				User user = new User(savedUser);
				TheLuvExchange application = (TheLuvExchange)getApplication();
				application.setUser(user);
				showCityPopUp();
				//startActivity(new Intent(activity, Splash.class));
			}
	
			
	
			userNameField = (EditText)findViewById(R.id.editTextUsername);
			passwordField = (EditText)findViewById(R.id.editTextPassword);
			rememberMe = (CheckBox)findViewById(R.id.rememberCheckbox);
	
	
			// Testing -- next line edited by Niranjan
			userNameField = (EditText)findViewById(R.id.editTextUsername);
	
	
			Button login = (Button)findViewById(R.id.btnLogin);
			login.setOnClickListener(onLogin);
	
			TextView register = (TextView)findViewById(R.id.link_to_register);
			register.setOnClickListener(onRegister);
	
			if (!ping) {
				Toast.makeText(activity, "Problem connecting to login service.",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	private void showCityPopUp() {
		try{

			listDialog = new Dialog(this);
			listDialog.setTitle("Select City");
			// used an inflator to pop the window out
			LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = li.inflate(R.layout.cities, null, false);
			listDialog.setContentView(v);
			listDialog.setCancelable(true);
			// add the listview to the dialog 
			ListView list1 = (ListView) listDialog.findViewById(R.id.cities);
			listDialog.show();


			list1.setOnItemClickListener(new OnItemClickListener(){
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					TheLuvExchange application = (TheLuvExchange)getApplication();		
					application.setCity(cities.get(position));



					startActivity(new Intent(activity, CityMenu.class));// When clicked, go to city menu page       	        
				}

			});
			//now that the dialog is set up, it's time to show it


			//Get the ListView from the cities layout
			//ListView list = (ListView)layout.findViewById(R.id.cities);

			// Instantiate the adapter for populating the ListView
			adapter = new CityAdapter();

			// Set the ListView to use the adapter
			list1.setAdapter(adapter);

			// Call the WebService.getCities() method to populate the cities list.
			cities.addAll(WebService.getCities());

		} catch (Exception e) {
			// Catch any errors and display them on LogCat
			Log.e("TheLuvExchange", "CitiesError", e);
		}		
	}

	private View.OnClickListener onLogin = new View.OnClickListener() {
		public void onClick(View v) {
			String userName = userNameField.getText().toString();
			Object result = WebService.login(userName, passwordField.getText().toString());
			Editor editor = savedUser.edit();
			if (result instanceof User) {
				User user = (User)result;
				user.setUserName(userName);

				TheLuvExchange application = (TheLuvExchange)getApplication();
				application.setUser(user);
				
				user.save(editor, rememberMe.isChecked());
				
				showCityPopUp();
				//startActivity(new Intent(activity, Splash.class));
			} else {
				Toast.makeText(activity, (String)result, Toast.LENGTH_LONG).show();
				editor.putBoolean("remember", false);
				editor.commit();
			}
		}
	};

	private View.OnClickListener onRegister = new View.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://theluvexchange.com/accounts/register/"));
			startActivity(intent);
		}
	};
	
	private class CityAdapter extends ArrayAdapter<City> {

		/**
		 * Constructor calls the supertype constructor.
		 * Pass the cityrow layout to use for displaying each city in the list.
		 */
		CityAdapter() {
			super(Login.this, R.layout.cityrow, cities);
		}

		/**
		 * getView is called when the row views are added to the ListView
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// If the row was already created before, we'll receive it here
			View row = convertView;

			// CityHolder is used to have an object that holds information to show in the row view
			CityHolder holder = null;

			// If this row wasn't created before, we'll create it here
			if (row == null) {

				// inflater will be used to create Views from the cityrow layout
				LayoutInflater inflater = getLayoutInflater();

				// Create a View object from the cityrow layout
				row = inflater.inflate(R.layout.cityrow, parent, false);

				// Create a CityHolder object which will set the name to show in the view
				holder = new CityHolder(row);

				// Tag the view to access again later if need be
				row.setTag(holder);

			} else {
				// If this row was previously created, get it back by its tag
				holder = (CityHolder)row.getTag();
			}

			// Populate the holder with a city
			holder.populateFrom(cities.get(position));

			// Return the row view
			return row;
		}
	}
	
	/*@Override  
	public void onBackPressed()  
	{  
		super.onBackPressed();
		TheLuvExchange application = (TheLuvExchange)getApplication();
		application.setUser(null);
		savedUser.edit();
		//application.
		userNameField.setText(" ");
		passwordField.setText(" ");
		//do whatever you want the 'Back' button to do  
		//as an example the 'Back' button is set to start a new Activity named 'NewActivity'

		this.finish();
		// this.startActivity(new Intent(Cities.this,Login.class));  

		return;  
	}  

*/	/**
	 * CityHolder is a simple class that just holds the information used to populate a row.
	 * As of 3/6/12, it's just holding a name and isn't well utilized.
	 */
	static class CityHolder {

		// name TextView
		private TextView name = null;

		// When the CityHolder is created, identify its associated name TextView
		CityHolder(View row) {
			name = (TextView)row.findViewById(R.id.cityName);
		}

		// Populate the TextView with the city name
		void populateFrom(City city) {
			name.setText(city.getName());
		}
	}
}
