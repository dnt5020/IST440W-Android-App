package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BuySellRentItem extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buysellrentitem);
		
		TheLuvExchange application = (TheLuvExchange)this.getApplication();
		City city = application.getCity();

		TextView header = (TextView) findViewById(R.id.header);
		TextView displaydescription = (TextView) findViewById(R.id.displaydescription);
		TextView displayprice = (TextView) findViewById(R.id.displayprice);
		TextView cityname = (TextView) findViewById(R.id.textViewPickTitle1);
		header.setText(city.getName());
		
		BuySellRent item = (BuySellRent)getIntent().getExtras().getSerializable("item");
		
		displaydescription.setText(item.getBody());
		displayprice.setText("$ " + item.getPrice());
	    cityname.setText(item.getName());
		
		//TextView name = (TextView)findViewById(R.id.textViewMenuItemName);
	    
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options_menu_other, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		TheLuvExchange application = null;
		Context activity = null;
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
