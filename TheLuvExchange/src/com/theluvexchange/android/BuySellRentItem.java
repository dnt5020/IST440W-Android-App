package com.theluvexchange.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BuySellRentItem extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.something);
		
		TheLuvExchange application = (TheLuvExchange)this.getApplication();
		City city = application.getCity();

		TextView header = (TextView) findViewById(R.id.header);
		header.setText(city.getName());
		
		BuySellRent item = (BuySellRent)getIntent().getExtras().getSerializable("item");
		
		TextView name = (TextView)findViewById(R.id.nameTextOrSomething);
	}
	
}
