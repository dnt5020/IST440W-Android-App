package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class PickLocation extends MapActivity {

	private MapActivity activity = this;
	private MapView mapView;
	private GeoPoint point;
	private LocationManager locationManager;
	private EditText addressText;
	private SitesOverlay sitesOverlay;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);

			// main.xml contains a MapView
			setContentView(R.layout.pick_map);

			// extract MapView from layout
			mapView = (MapView) findViewById(R.id.mapview);
			mapView.setBuiltInZoomControls(true);
			mapView.setSatellite(true);

			locationManager = (LocationManager)getSystemService(
					Context.LOCATION_SERVICE);

			Location location = locationManager.getLastKnownLocation(
					LocationManager.GPS_PROVIDER);
			point = new GeoPoint((int)(location.getLatitude() * 1000000),
					(int)(location.getLongitude() * 1000000));
			zoomToMyLocation();

			Drawable marker = getResources().getDrawable(R.drawable.marker);
			marker.setBounds(0, 0, marker.getIntrinsicWidth(),
					marker.getIntrinsicHeight());

			sitesOverlay = new SitesOverlay(marker);

			// add this overlay to the MapView and refresh it
			List<Overlay> overlays = mapView.getOverlays();
			overlays.add(sitesOverlay);
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
		if(point != null) {
			MapController mapController = mapView.getController();
			mapController.animateTo(point);
			mapController.setZoom(17);

		} else {
			Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
		}
	}

	private OnClickListener onSearch = new View.OnClickListener() {
		public void onClick(View v) {
			// TODO implement search address
		}
	};

	private OnClickListener onSubmit = new View.OnClickListener() {
		public void onClick(View v) {
			if (point != null) {
				sitesOverlay.returnResult();
			} else {
				Toast.makeText(activity, "Tap the map to select the place location.", Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private class SitesOverlay extends ItemizedOverlay<OverlayItem> {
		private List<OverlayItem> items = new ArrayList<OverlayItem>();
		private Drawable marker = null;
		private OverlayItem inDrag = null;
		private ImageView dragImage = null;
		private int xDragImageOffset = 0;
		private int yDragImageOffset = 0;
		private int xDragTouchOffset = 0;
		private int yDragTouchOffset = 0;
		private String address;

		public SitesOverlay(Drawable marker) {
			super(marker);
			this.marker=marker;

			dragImage = (ImageView)findViewById(R.id.drag);
			xDragImageOffset=dragImage.getDrawable().getIntrinsicWidth()/2;
			yDragImageOffset=dragImage.getDrawable().getIntrinsicHeight();

			items.add(new OverlayItem(point, "Location", "Location to submit"));

			populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return(items.get(i));
		}

		@Override
		public void draw(Canvas canvas, MapView mapView,
				boolean shadow) {
			super.draw(canvas, mapView, shadow);

			boundCenterBottom(marker);
		}

		@Override
		public int size() {
			return(items.size());
		}

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
			try {
				final int action = event.getAction();
				final int x = (int)event.getX();
				final int y = (int)event.getY();
				boolean result = false;

				if (action == MotionEvent.ACTION_DOWN) {
					for (OverlayItem item : items) {
						Point p = new Point(0,0);

						mapView.getProjection().toPixels(item.getPoint(), p);

						if (hitTest(item, marker, x-p.x, y-p.y)) {
							result = true;
							inDrag = item;
							items.remove(inDrag);
							populate();

							xDragTouchOffset = 0;
							yDragTouchOffset = 0;

							setDragImagePosition(p.x, p.y);
							dragImage.setVisibility(View.VISIBLE);

							xDragTouchOffset = x - p.x;
							yDragTouchOffset = y - p.y;

							break;
						}
					}

				} else if (action == MotionEvent.ACTION_MOVE && inDrag != null) {
					setDragImagePosition(x, y);
					result = true;

				} else if (action == MotionEvent.ACTION_UP && inDrag != null) {
					dragImage.setVisibility(View.GONE);

					point = mapView.getProjection().fromPixels(x - xDragTouchOffset,
							y - yDragTouchOffset);
					OverlayItem toDrop = new OverlayItem(point, inDrag.getTitle(),
							inDrag.getSnippet());

					items.add(toDrop);
					populate();

					Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());

					List<Address> addresses = geoCoder.getFromLocation(
							point.getLatitudeE6() / 1E6, 
							point.getLongitudeE6() / 1E6, 1);

					address = "";
					if (addresses.size() > 0) {
						for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
							address += addresses.get(0).getAddressLine(i) + "\n";
					}

					inDrag = null;
					result = true;
				}
				return(result || super.onTouchEvent(event, mapView));
			} catch (Exception e) {
				Log.e("PickLocation Error", "Error in SiteOverlay.onTouchEvent()", e);
				return false;
			}
		}

		private void setDragImagePosition(int x, int y) {
			RelativeLayout.LayoutParams lp =
					(RelativeLayout.LayoutParams)dragImage.getLayoutParams();

			lp.setMargins(x - xDragImageOffset - xDragTouchOffset,
					y - yDragImageOffset - yDragTouchOffset, 0, 0);
			dragImage.setLayoutParams(lp);
		}

		public void returnResult() {
			try {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("latitude", point.getLatitudeE6());
				bundle.putInt("longitude", point.getLongitudeE6());
				bundle.putString("address", address);
				intent.putExtras(bundle);
				activity.setResult(Activity.RESULT_OK, intent);
				activity.finish();
			} catch (Exception e) {                
				Log.e("PickLocationError", "Error in tapOverlay.returnResult()", e);
			}
		}
	}
}
