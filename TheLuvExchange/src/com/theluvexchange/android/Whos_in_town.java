package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Whos_in_town extends Activity{
	private Activity activity = this;
	private TheLuvExchange application = null;
	private List<Comment> commentList = null;
	private City city;
	TextView t;
	User user;
	AddMessage message=new AddMessage();
	String result;
	 
		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
		        setContentView(R.layout.whosintown);
		        t=(TextView) findViewById(R.id.nomes);
				t.setVisibility(View.INVISIBLE);
		        
		        application = (TheLuvExchange)this.getApplication();
		        city=application.getCity();
			    commentList  = new ArrayList<Comment>();
			        
			    ListView listViewRestaurants = (ListView)findViewById(R.id.commentlistView1);
			    commentList.addAll(WebService.getComment(city));
			    
			    if (commentList.isEmpty()==true)
			    {
			    	
			    	t.setVisibility(View.VISIBLE);
			    }
		       
			    listViewRestaurants.setAdapter(new CommentAdapter());
		        listViewRestaurants.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		        
		        Button addcom = (Button)findViewById(R.id.addcominbtn);
				addcom.setOnClickListener(onCom);
				Button checkin = (Button)findViewById(R.id.checkinbtn);
				checkin.setOnClickListener(onCheckIn);
		 
				
		 }
		 
		 
		 private View.OnClickListener onCom = new View.OnClickListener() {
				public void onClick(View v) {
					
					 startActivity(new Intent(activity, AddCommentActivity.class));
				}
		};
		
		 private View.OnClickListener onCheckIn = new View.OnClickListener() {
				public void onClick(View v) {
					
					message.setMessage("Checked in");
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
		
		 private class CommentAdapter extends ArrayAdapter<Comment> {

			 public CommentAdapter() {
				super(Whos_in_town.this, R.layout.commentrow, commentList);
				
			}
			 
			 private LayoutInflater layoutInflater = getLayoutInflater();

			 public View getView(int position, View convertView, ViewGroup parent) {
				
				// If the row was already created before, we'll receive it here
				View row = convertView;
				
				// a ViewHolder keeps references to children views to avoid unnecessary calls 
				// to findById() on each row
				ViewHolder myViewHolder = null;
				
				// If this row wasn't created before, we'll create it here
				if(row == null){

					
					// inflater will be used to create Views from the things_row layout
					row = layoutInflater.inflate(R.layout.commentrow, null);
					
					myViewHolder = new ViewHolder(row);
					
					row.setTag(myViewHolder);
					
				} else {
					myViewHolder = (ViewHolder) row.getTag();
				}
				
				
				myViewHolder.populateFrom(commentList.get(position));
				
				
				return row;
	
				
			}
			 class ViewHolder {
					TextView textViewNumber = null;
					TextView textViewName = null;
					TextView textViewTime = null;
					TextView textViewMessage = null;
					
					public ViewHolder (View row){
						textViewNumber = (TextView) row.findViewById(R.id.textViewNo);
						textViewName = (TextView) row.findViewById(R.id.textViewUsername);
						textViewTime = (TextView) row.findViewById(R.id.textViewTime);
						textViewMessage = (TextView) row.findViewById(R.id.textViewMessage);
					}
						
					public void populateFrom(Comment message){
						textViewMessage.setText(message.getMessage());
						textViewName.setText(message.getUsername());
						textViewTime.setText(message.getTime());
						textViewNumber.setText(Integer.toString(message.getSerialNumber()) + ".");
								 
					}
					
					
					
				}
				 
				 
				 
			 }

		}