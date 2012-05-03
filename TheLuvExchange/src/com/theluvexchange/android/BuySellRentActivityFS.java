package com.theluvexchange.android;


/*
 * 
 * 
 * itemClicked.equalsIgnoreCase("Electronics")
							|| itemClicked.equalsIgnoreCase("Antiques & Collectibles")
				            || itemClicked.equalsIgnoreCase("Computer & Accessories") 
				            || itemClicked.equalsIgnoreCase("Health & Beauty")
							|| itemClicked.equalsIgnoreCase("Furniture & Decor")
							|| itemClicked.equalsIgnoreCase("Jewelry & Watches")
						    || itemClicked.equalsIgnoreCase("Sports & Bikes")
							|| itemClicked.equalsIgnoreCase("Toys & Games")
							|| itemClicked.equalsIgnoreCase("Pets")
							|| itemClicked.equalsIgnoreCase("Miscellaneous"))
							
							*
							*
							*
							*
							*
							*			menuList.add("Electronics");
			menuList.add("Antiques & Collectibles");
			menuList.add("Computer & Accessories");
			menuList.add("Health & Beauty");
			menuList.add("Furniture & Decor");
			menuList.add("Jewelry & Watches");
			menuList.add("Sports & Bikes");
			menuList.add("Toys & Games");
			menuList.add("Pets");
			menuList.add("Miscellaneous");
							*
							*
							*
							*
							*/

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

// Initial screen

public class BuySellRentActivityFS extends Activity{

	private Activity activity = this;
	//private TheLuvExchange application = null;
	private City city;
	private TheLuvExchange application = null;
	//private List<BuySellRent> picksList = null;
	private User user;
	//private City city;



	private List<String> menuList;

	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.buysellrent);
			
			application = (TheLuvExchange)this.getApplication();
			city = application.getCity();
			
			TextView header = (TextView) findViewById(R.id.header);
			header.setText(city.getName());

		//	 picksList  = new ArrayList<BuySellRent>();

			  ListView listViewBuySellRent = (ListView)findViewById(R.id.picksList);
			

			 Log.d("ThingsToDo.java", "just before user ");

			 user = application.getUser();




			menuList = new ArrayList<String>();

			menuList.add("Electronics");
			menuList.add("Antiques & Collectibles");
			menuList.add("Computer & Accessories");
			menuList.add("Health & Beauty");
			menuList.add("Furniture & Decor");
			menuList.add("Jewelry & Watches");
			menuList.add("Sports & Bikes");
			menuList.add("Toys & Games");
			menuList.add("Pets");
			menuList.add("Miscellaneous");
	


			// *****		what is this menulist pointing to ????/
			ListView listViewMenuList = (ListView)findViewById(R.id.menulist);
			listViewMenuList.setAdapter(new MenuAdapter(menuList, this));
			listViewMenuList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

			// Listener to handle click of an Item in the List

			// change to buy sell rent 
			listViewMenuList.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					String itemClicked = ((String) menuList.get(position));
					Intent intent = new Intent(activity, BuySellRentDisplay.class);
					intent.putExtra("MenuSelected", itemClicked);
//					if (itemClicked.equalsIgnoreCase("Electronics")
//							|| itemClicked.equalsIgnoreCase("Antiques & Collectibles")
//				            || itemClicked.equalsIgnoreCase("Computer & Accessories") 
//				            || itemClicked.equalsIgnoreCase("Health & Beauty")
//							|| itemClicked.equalsIgnoreCase("Furniture & Decor")
//							|| itemClicked.equalsIgnoreCase("Jewelry & Watches")
//						    || itemClicked.equalsIgnoreCase("Sports & Bikes")
//							|| itemClicked.equalsIgnoreCase("Toys & Games")
//							|| itemClicked.equalsIgnoreCase("Pets")
//							|| itemClicked.equalsIgnoreCase("Miscellaneous")) 
//					
					if (itemClicked.equalsIgnoreCase("Electronics")) {


						intent.putExtra("category", BuySellRent.ELECTRONICS);

					}
					//  (((String) menuList.get(position)).equals
					else if (itemClicked.equalsIgnoreCase("Antiques & Collectibles")) {

						intent.putExtra("category", BuySellRent.ANTIQUES);

					}
					else if (itemClicked.equalsIgnoreCase("Computer & Accessories")) {
						intent.putExtra("category", BuySellRent.COMPUTERS);

					}
					else if (itemClicked.equalsIgnoreCase("Health & Beauty")) {
						intent.putExtra("category", BuySellRent.HEALTH);

					}

					else if (itemClicked.equalsIgnoreCase("Furniture & Decor")) {

						intent.putExtra("category", BuySellRent.FURNITURE);

					}

					else if (itemClicked.equalsIgnoreCase("Jewelry & Watches")) {
						intent.putExtra("category", BuySellRent.JEWELRY);

					}
					else if (itemClicked.equalsIgnoreCase("Sports & Bikes")) {

						intent.putExtra("category", BuySellRent.SPORTS );


					}
					else if (itemClicked.equalsIgnoreCase("Toys & Games")) {

						intent.putExtra("category", BuySellRent.TOYS );


					}
					else if (itemClicked.equalsIgnoreCase("Pets")) {

						intent.putExtra("category", BuySellRent.PETS );


					}
					else if (itemClicked.equalsIgnoreCase("Miscellaneous")) {

						intent.putExtra("category", BuySellRent.MISCELLANEOUS );


					}
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

				//Take out for right now because I don'thave a image nor a drawable -->
				//	myViewHolder.imageViewMenuItem = (ImageView) convertView
				//	.findViewById(R.id.imageViewMenuItem);



				convertView.setTag(myViewHolder);

			} else {
				myViewHolder = (ViewHolder) convertView.getTag();
			}

			myViewHolder.textViewMenuItemName.setText((String) menuList.get(
					position));



			return convertView;
		}

		class ViewHolder {
			TextView textViewMenuItemName = null;
			//ImageView imageViewMenuItem = null;

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
			
