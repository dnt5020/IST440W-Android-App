package com.theluvexchange.android;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

// Initial screen

public class BuySellRentDisplay extends Activity{

	private Activity activity = this;
	private City city;
	private User user;
	private TheLuvExchange application = null;
	private List<String> menuList;

	private ArrayList<BuySellRent> items;

	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.buysellrent);

			application = (TheLuvExchange)this.getApplication();
			city = application.getCity();
			user = application.getUser();

			int category = getIntent().getExtras().getInt("category");

			TextView header = (TextView) findViewById(R.id.header);
			header.setText(city.getName());

			menuList = new ArrayList<String>();
			items = new ArrayList<BuySellRent>();

			List<BuySellRent> results = WebService.getBuySellRent(city, user, category);



			if (results == null) {
				Toast.makeText(this, "Unable to display items", Toast.LENGTH_LONG);
			} else {
				for (BuySellRent item : results) {
					if (item.getTimeToExpire() > 0) {
						menuList.add(item.getName() + ":::" + item.getPrice());
						items.add(item);
					}
				}
			}

			// *****		what is this menulist pointing to ????/
			ListView listViewMenuList = (ListView)findViewById(R.id.menulist);
			listViewMenuList.setAdapter(new MenuAdapter(menuList, this));
			listViewMenuList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

			// Listener to handle click of an Item in the List

			// change to buy sell rent 
			listViewMenuList.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					Intent intent = new Intent(activity, BuySellRentItem.class);

					// Pass Pick to the PickComments activity
					intent.putExtra("item", items.get(position));
					startActivity(intent);
				}
			});
		} catch (Exception e) {
			Log.e("Error","Error in BuySellRentActivity", e);
		}

	}

	// change this to reflect buysellrent
	private class MenuAdapter extends BaseAdapter {
		private List<String> menuList;
		private LayoutInflater layoutInflater;

		public MenuAdapter(List<String> menuList,
				Context context) {
			this.menuList = menuList;
			layoutInflater = LayoutInflater.from(context);


			//super(BuySellRent.this, R.layout.buysellrentrow, menuList);

		}

		public int getCount() {
			return menuList.size();
		}
		public Object getItem(int position) {
			return menuList.get(position);
		}
		public long getItemId(int position) {
			return position;
		}




		// private LayoutInflater layoutInflater = getLayoutInflater();

		public View getView(int position, View convertView, ViewGroup parent) {


			ViewHolder myViewHolder = null;

			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.buysellrentrow, null);

				myViewHolder = new ViewHolder();
				myViewHolder.textViewMenuItemName = (TextView) convertView
						.findViewById(R.id.textViewMenuItemName);
				myViewHolder.textViewPrice = (TextView) convertView.findViewById(R.id.textViewPrice);

				//Take out for right now because I don'thave a image nor a drawable -->
				//	myViewHolder.imageViewMenuItem = (ImageView) convertView
				//	.findViewById(R.id.imageViewMenuItem);



				convertView.setTag(myViewHolder);

			} else {
				myViewHolder = (ViewHolder) convertView.getTag();
			}

			String[] namePrice = menuList.get(position).split(":::");

			myViewHolder.textViewMenuItemName.setText(namePrice[0]);
			myViewHolder.textViewPrice.setText("$" + namePrice[1]);


			return convertView;
		}

		private class ViewHolder {
			TextView textViewMenuItemName = null;
			TextView textViewPrice = null;

		}





		/*public ViewHolder (View row){

				textViewMenuItemName = (TextView) row.findViewById(R.id.textViewPickName);
			//	textViewPrice = (TextView) row.findViewById(R.id.textViewPickPrice);


			}
			 //
			public void populateFrom(BuySellRent buysell){

				textViewName.setText(buysell.getName());
				//textViewPrice.setText(buysell.getPrice());



		 */	



	}	





}