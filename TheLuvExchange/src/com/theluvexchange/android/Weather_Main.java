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
			setContentView(R.layout.weatherscreen);
			TheLuvExchange application = (TheLuvExchange)getApplication();	 	 
	 	 	city = application.getCity();
			weatherO=(WebService.getWeather(city));
			setText(city.getName(),R.id.citySelected);
	 	    setText(" "+weatherO.getWeatherCurrentCondition().getTempF()+" °F",R.id.currentF);
	 	    setText(" "+weatherO.getWeatherCurrentCondition().getHumidity()+" %",R.id.humidity);
	 	    
	 	    setText(weatherO.getWeatherForecastConditions().get(3).getDesc(),R.id.desc3);
	 	    setText(weatherO.getWeatherForecastConditions().get(2).getDesc(),R.id.desc2);
	 	    setText(weatherO.getWeatherForecastConditions().get(1).getDesc(),R.id.desc1);
		 		 	
		 	setText(weatherO.getWeatherForecastConditions().get(1).getTempMinF()+" °F/"+ weatherO.getWeatherForecastConditions().get(1).getTempMaxF()+" °F"  ,R.id.minmax1);
		 	setText(weatherO.getWeatherForecastConditions().get(2).getTempMinF()+" °F/"+ weatherO.getWeatherForecastConditions().get(2).getTempMaxF()+" °F"  ,R.id.minmax2);
		 	setText(weatherO.getWeatherForecastConditions().get(3).getTempMinF()+" °F/"+ weatherO.getWeatherForecastConditions().get(3).getTempMaxF()+" °F"  ,R.id.minmax3);
		 	
		 	
		 	
		 	setText(" "+weatherO.getWeatherCurrentCondition().getVisibiltiy()+" mi",R.id.visibility); 
	 	    setText(" "+weatherO.getWeatherCurrentCondition().getPrecMM()+" in",R.id.prec); 
	 	    setText(weatherO.getWeatherCurrentCondition().getDescription(),R.id.desc); 
	 	    setText(" "+weatherO.getWeatherCurrentCondition().getWindDirection()+" at "+weatherO.getWeatherCurrentCondition().getWindspeedMiles()+" mph",R.id.windD); 
	 	    setText(" "+weatherO.getWeatherForecastConditions().get(0).getTempMaxF()+" °F",R.id.tempMax); 
	 	    setText(" "+weatherO.getWeatherForecastConditions().get(0).getTempMinF()+" °F" ,R.id.tempMin); 
		 	
	 	    setImage(weatherO.getWeatherCurrentCondition().getIconURL(),R.id.currentImage);
	 	    /*setImage(weatherO.getWeatherForecastConditions().get(1).getIconURL(),R.id.weather_image1);
	 	    setImage(weatherO.getWeatherForecastConditions().get(2).getIconURL(),R.id.weather_image2);
	 	    setImage(weatherO.getWeatherForecastConditions().get(3).getIconURL(),R.id.weather_image3);
	 	  */
	 	     
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