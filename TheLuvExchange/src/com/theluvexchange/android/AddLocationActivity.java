package com.theluvexchange.android;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLocationActivity extends Activity {
		private EditText name,address,no,comment;
		private Activity activity = this;
		City city;
		String result;
		Pick pick= new Pick();
		User user;
		List listofaddress;
			public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.addlocation);
		        Button add = (Button)findViewById(R.id.button1);
				add.setOnClickListener(onAdd);
		 }
			private View.OnClickListener onAdd = new View.OnClickListener() {
				public void onClick(View v) {
					
					comment= (EditText)findViewById(R.id.loccomment);
					pick.setComment(comment.getText().toString());
					name= (EditText)findViewById(R.id.locname);
					pick.setName(name.getText().toString());
					address= (EditText)findViewById(R.id.locaddr);
					pick.setAddress(address.getText().toString());
					no= (EditText)findViewById(R.id.locno);
					pick.setPhone(no.getText().toString());
					
					// to get lat and long
					/*Geocoder coder = new Geocoder(activity);
					try {
						listofaddress=coder.getFromLocationName(address.getText().toString(), 3);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listofaddress.get(0);
					*/
					TheLuvExchange application = (TheLuvExchange)getApplication();		
					city=application.getCity();
					user= application.getUser();
					
					//TEST data 
					result= (String) WebService.postRestaurant(user,city,name.getText().toString(),comment.getText().toString(),"test",5,true,"test",address.getText().toString(),no.getText().toString(),45,23);
					
					/*if(result=="success")
					{
						Toast.makeText(activity, "You have successfully added your pick",Toast.LENGTH_LONG).show();
						 startActivity(new Intent(activity, PicksDisplay.class));
							}else
				    {
				    	Toast.makeText(activity, "Error in adding pick",Toast.LENGTH_LONG).show();
				    }*/
					
				}
		};
}
