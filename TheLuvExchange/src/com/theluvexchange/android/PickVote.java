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
import android.widget.AdapterView.OnItemClickListener;



public class PickVote extends Activity {

	
	private TheLuvExchange application = null;
	private User user;
	private City city;
	
	 public void onCreate(Bundle savedInstanceState) {	        
		 
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.postcomment);
//		 Button submit = (Button)findViewById(R.id.buttonSubmit);
//		 TextView restaurantName = (TextView)findViewById(R.id.textViewCommentPickName); 
//		 TextView pickName = (TextView)findViewById(R.id.textViewPickCommentTitle);
//		 RatingBar rateBar = (RatingBar)findViewById(R.id.ratingBarCommentPick);
//		 EditText comment = (EditText)findViewById(R.id.editTextPickComment);
//		 CheckBox discount = (CheckBox)findViewById(R.id.checkBoxDiscount);
		 
	 }
	 
	 private boolean validateFields()
	 {
		 return true;
	 }
	 
}
