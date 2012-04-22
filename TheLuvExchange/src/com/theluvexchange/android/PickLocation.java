package com.theluvexchange.android;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class PickLocation extends MapActivity {

	private MapActivity activity = this;
	private MapView mapView;
	private GeoPoint lastTap;
	private TapOverlay tapOverlay;
	private LocationManager locationManager;
	//private LocationListener locationListener;
	private MapController mapController;
	private GeoPoint locationGeoPoint;
	private EditText addressText;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);

			// main.xml contains a MapView
			setContentView(R.layout.pick_map);

			TheLuvExchange application = (TheLuvExchange)getApplication();
			City city = application.getCity();

			// extract MapView from layout
			mapView = (MapView) findViewById(R.id.mapview);
			mapView.setBuiltInZoomControls(true);
			mapView.setSatellite(true);

			mapController = mapView.getController();
			locationManager = (LocationManager)getSystemService(
					Context.LOCATION_SERVICE);
			//locationListener = new MyLocationListener();
			//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
			//		0, 0, locationListener);
			//Get the current location in start-up
			Location location = locationManager.getLastKnownLocation(
					LocationManager.GPS_PROVIDER);
			locationGeoPoint = new GeoPoint((int)(location.getLatitude() * 1000000),
					(int)(location.getLongitude() * 1000000));
			zoomToMyLocation();

			tapOverlay = new TapOverlay();

			// add this overlay to the MapView and refresh it
			List<Overlay> overlays = mapView.getOverlays();
			overlays.add(tapOverlay);
			mapView.postInvalidate();
			
			Button searchButton = (Button)findViewById(R.id.buttonAddress);
			addressText = (EditText)findViewById(R.id.editTextAddress);
			searchButton.setOnClickListener(onSearch);

			Button submitButton = (Button)findViewById(R.id.Submit);
			submitButton.setOnClickListener(onSubmit);
		} catch (Exception e) {                
			Log.e("PickLocationError", "Error in onCreate()", e);
		}
	}

	/**
	 * This method zooms to the user's location with a zoom level of 10.
	 */
	private void zoomToMyLocation() {
		if(locationGeoPoint != null) {
			MapController mapController = mapView.getController();
			mapController.animateTo(locationGeoPoint);
			mapController.setZoom(17);
		} else {
			Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
		}
	}

	private class TapOverlay extends Overlay
	{
		String address;

		@Override
		public boolean onTap(GeoPoint point, MapView mapView) {
			try {
				lastTap = point;
				mapView.getController().animateTo(point);

				Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());

				List<Address> addresses = geoCoder.getFromLocation(
						point.getLatitudeE6()  / 1E6, 
						point.getLongitudeE6() / 1E6, 1);

				address = "";
				if (addresses.size() > 0) 
				{
					for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
						address += addresses.get(0).getAddressLine(i) + "\n";
				}
			}
			catch (Exception e) {                
				Log.e("PickLocationError", "Error in TapOverlay.onTap()", e);
			}

			return true;        
		}    

		private void returnResult()
		{
			try {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("latitude", lastTap.getLatitudeE6());
				bundle.putInt("longitude", lastTap.getLongitudeE6());
				bundle.putString("address", address);
				intent.putExtras(bundle);
				activity.setResult(Activity.RESULT_OK, intent);
				activity.finish();
			} catch (Exception e) {                
				Log.e("PickLocationError", "Error in tapOverlay.returnResult()", e);
			}
		}
	}

	private OnClickListener onSearch = new View.OnClickListener() {
		public void onClick(View v) {
			// TODO implement search address
		}
	};
	
	private OnClickListener onSubmit = new View.OnClickListener() {
		public void onClick(View v) {
			if (lastTap != null) {
				tapOverlay.returnResult();
			} else {
				Toast.makeText(activity, "Tap the map to select the place location.", Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
