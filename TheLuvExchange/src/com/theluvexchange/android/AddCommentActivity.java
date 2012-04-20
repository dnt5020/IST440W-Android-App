package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.*;

public class AddCommentActivity extends Activity {
	private EditText comment;
	private Activity activity = this;
	City city;
	User user;
	AddMessage message=new AddMessage();
	String result;

	 
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.addcomment);
	       
	        Button submit = (Button)findViewById(R.id.button1);
			submit.setOnClickListener(onSubmit);
			
			comment= (EditText)findViewById(R.id.comMessage);
			comment.setOnKeyListener(new OnKeyListener() {

		        public boolean onKey(View v, int keyCode, KeyEvent event) {


		                if (event.getAction() == KeyEvent.ACTION_DOWN
		                        && event.getKeyCode() ==       KeyEvent.KEYCODE_ENTER) {
		                   return true;
		                } 
		                
		                return false;
		        }
		    });
	
	}
	 
	 
	 private View.OnClickListener onSubmit = new View.OnClickListener() {
			public void onClick(View v) {
				
				comment= (EditText)findViewById(R.id.comMessage);
				String addm=comment.getText().toString();
				message.setMessage(addm);
				TheLuvExchange application = (TheLuvExchange)getApplication();		
				city=application.getCity();
				user= application.getUser();
				result= WebService.postMessage(user, message, city);
				if(result=="success")
				{
					Toast.makeText(activity, "You have successfully checked in",Toast.LENGTH_LONG).show();
					 startActivity(new Intent(activity, Whos_in_town.class));
						}else
			    {
			    	Toast.makeText(activity, "Error in checking in",Toast.LENGTH_LONG).show();
			    }
				
			}
	};
}
