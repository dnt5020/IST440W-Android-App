package com.theluvexchange.android;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.theluvexchange.weather.WeatherSet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
 
/**
* @author Shibani Chadha
*/

public class Weather_Main extends Activity {
 WeatherSet weatherO = new WeatherSet();
	private TheLuvExchange application = null;
	private User user;
	private City city;
	private Activity activity = this;

	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			// Need to call the superclass constructor first
			super.onCreate(savedInstanceState);
			setContentView(R.layout.weatherlayout);
			
			application = (TheLuvExchange) this.getApplication();
			
			user = application.getUser();

			
	 	 	city = application.getCity();
	 	 		 	 	
//	 	   // Set Header to selected city name
//	        TextView cityName = (TextView)findViewById(R.id.header);
//	        cityName.setText(city.getName());
	  
	 	 	 
			weatherO=(WebService.getWeather(city)); 
			setText(city.getName(),R.id.header);
			setText(city.getName()+" Weather",R.id.CityWeatherTitle);
	 	    
			//Left Side Temp
			setText(" "+weatherO.getWeatherCurrentCondition().getTempF()+(char) 0x00B0+"F",R.id.CurrentTemperature);
	 	    //Right Side Temp
			setText(" "+weatherO.getWeatherCurrentCondition().getTempF()+(char) 0x00B0+"F",R.id.textView1);
	 	    setText(" "+weatherO.getWeatherCurrentCondition().getWindspeedMiles()+"mph",R.id.CurrentWindSpeed); 
			setText(" "+weatherO.getWeatherCurrentCondition().getHumidity()+" %",R.id.TextView07);
	 	    //Current Weather Condition Below Image
			setText(weatherO.getWeatherCurrentCondition().getDescription(),R.id.textView2); 	 	    
	 	    //Current Weather Image
			setImage(weatherO.getWeatherCurrentCondition().getIconURL(),R.id.CurrentWeatherImage);
		 	//Current Visibility
			setText(" "+weatherO.getWeatherCurrentCondition().getVisibiltiy()+" mi",R.id.TextView06); 
			 
			
			int year;
			int month;
			int day;

			GregorianCalendar gregorianCalendar;
			
			//create an array of days
		    String[] strDays = new String[]{
		                      "Thusday",
		                      "Friday",
		                      "Saturday",
		                      "Sunday",
		                      "Monday",
		                      "Tuesday",
		                      "Wednesday"
		                    };
		    
		    String strDaysForecast[] = new String[3];
			
		 	Log.d("Weather", weatherO.getWeatherForecastConditions().get(2).getDate().substring(0, 4));
		 	Log.d("Weather", weatherO.getWeatherForecastConditions().get(2).getDate().substring(5, 7));
		 	Log.d("Weather", weatherO.getWeatherForecastConditions().get(2).getDate().substring(8, 10));
		 			 	
		 	for(int i=1;i<=3;i++){
		 		
		 		year = Integer.parseInt(weatherO.getWeatherForecastConditions().get(i).getDate().substring(0, 4));
				month = Integer.parseInt(weatherO.getWeatherForecastConditions().get(i).getDate().substring(5, 7));
				day = Integer.parseInt(weatherO.getWeatherForecastConditions().get(i).getDate().substring(8, 10));
				
				gregorianCalendar =  new GregorianCalendar(year, month, day);
				
				strDaysForecast[i-1] = strDays[gregorianCalendar.get(Calendar.DAY_OF_WEEK)-1];
				
		 		
		 	}

		 	

		 	
  
			//3 Day Forecast Descriptions
		 	setText(strDaysForecast[0], R.id.textViewForecast1);
		 	setText(strDaysForecast[1], R.id.textViewForecast2);
		 	setText(strDaysForecast[2], R.id.textViewForecast3);

		 	
	 	    setText(weatherO.getWeatherForecastConditions().get(1).getDesc(),R.id.TextView10);
	 	    setText(weatherO.getWeatherForecastConditions().get(2).getDesc(),R.id.TextView11);
	 	    setText(weatherO.getWeatherForecastConditions().get(3).getDesc(),R.id.TextView05);
		 		 	
		 	setText(weatherO.getWeatherForecastConditions().get(1).getTempMinF()+(char) 0x00B0+"F"+"/"+ weatherO.getWeatherForecastConditions().get(1).getTempMaxF()+(char) 0x00B0+"F",R.id.TextView12);
		 	setText(weatherO.getWeatherForecastConditions().get(2).getTempMinF()+(char) 0x00B0+"F"+"/"+ weatherO.getWeatherForecastConditions().get(2).getTempMaxF()+(char) 0x00B0+"F",R.id.TextView13);
		 	setText(weatherO.getWeatherForecastConditions().get(3).getTempMinF()+(char) 0x00B0+"F"+"/"+ weatherO.getWeatherForecastConditions().get(3).getTempMaxF()+(char) 0x00B0+"F",R.id.TextView14);
		 	
/*
 * Author Notes: Pranav		 	
 */
//			Commented because the weather web service is buggy and doesn't return any images for the forecasts
//		 	setImage(weatherO.getWeatherForecastConditions().get(1).getIconURL(),R.id.ImageView01);
//	 	    setImage(weatherO.getWeatherForecastConditions().get(2).getIconURL(),R.id.ImageView02);
//	 	    setImage(weatherO.getWeatherForecastConditions().get(3).getIconURL(),R.id.ImageView03);
		 	
//			Following not used
//	 	    setText(" "+weatherO.getWeatherForecastConditions().get(0).getTempMaxF()+(char) 0x00B0+"F",R.id.tempMax); 
//	 	    setText(" "+weatherO.getWeatherCurrentCondition().getPrecMM()+" in",R.id.prec); 
//	 	    setText(" "+weatherO.getWeatherForecastConditions().get(0).getTempMinF()+(char) 0x00B0+"F",R.id.tempMin); 
	 	    
	 	    this.setVisible(true);
	 	     
}
		catch (Exception e) {
			// Catch any errors and display them on LogCat
			Log.e("TheLuvExchange", "WeatherError", e);
		}
}
	public void setText(String text,Integer id)
	{
		 TextView t ;
		 t=(TextView)findViewById(id); 
 	     t.setText(text);		
	}
	public void setImage(String loc, Integer id)
	{
		try{
			 URL url=new URL(loc);
		 	    URLConnection conn = url.openConnection();
				conn.connect();
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				Bitmap bm = BitmapFactory.decodeStream(bis);
				bis.close();
				is.close();
				 ImageView iv;
		 	     iv=(ImageView)findViewById(id);
		 	     iv.setImageBitmap(bm);
		}catch(Exception e){
			// Catch any errors and display them on LogCat
						Log.e("TheLuvExchange", "WeatherImageError", e);	
		}
		
	}
	
	@Override
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