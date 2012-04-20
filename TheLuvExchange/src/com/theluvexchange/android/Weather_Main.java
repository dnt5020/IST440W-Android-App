package com.theluvexchange.android;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import com.theluvexchange.weather.WeatherSet;

import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageView;
 
/**
* @author Shibani Chadha
*/

public class Weather_Main extends Activity {
 WeatherSet weatherO = new WeatherSet();
	
	
	City city;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			// Need to call the superclass constructor first
			super.onCreate(savedInstanceState);
			setContentView(R.layout.weatherlayout);
			
			TheLuvExchange application = (TheLuvExchange)getApplication();	 	 
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


			//3 Day Forecast Descriptions
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
}