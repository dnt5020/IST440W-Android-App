package com.theluvexchange.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
	
}
