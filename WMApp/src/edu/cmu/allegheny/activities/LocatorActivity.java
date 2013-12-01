/**
 * 
 */
package edu.cmu.allegheny.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.R.layout;
import edu.cmu.allegheny.R.menu;
import edu.cmu.allegheny.data.Store;
import edu.cmu.allegheny.data.StoreHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * @author yinxu
 * 
 */
public class LocatorActivity extends FragmentActivity {

	// Views
	// private TextView text;
	private GoogleMap map;
	private String zipCode;

	// Data Transation
	private StoreHandler sh;
	private String[] addrBundle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locator_activity);
		
		// Data Transaction
		sh = new StoreHandler(this);

		// getting google map object
		SupportMapFragment mapFragment = 
				(SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.locator_map);
		map = mapFragment.getMap();
		final LocationManager locManager = 
				(LocationManager) getSystemService(LOCATION_SERVICE);
		// getting a Criteria object to retrieve provide
		final Criteria criteria = new Criteria();
		// getting the name of the best provider
		String provider = locManager.getBestProvider(criteria, true);
		// getting current location
		Location location = locManager.getLastKnownLocation(provider);

		if (location != null) {
			Log.d("MAP", "Location found!");
			map.clear();
			try {
				getZipCode(location);
				putMultiMarkers();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				AlertDialog.Builder builder = new Builder(LocatorActivity.this);
				builder.setMessage("GPS is not working. Please try again.");
				builder.show();
			}

			// putMarker(location);

		} else {
			AlertDialog.Builder builder = new Builder(LocatorActivity.this);
			builder.setMessage("Location currently unavailable.");
			builder.show();
		}
	}

	/**
	 * Get zip code based on current location.
	 * 
	 * @param loc
	 * @throws IOException
	 */
	public void getZipCode(Location loc) throws IOException {

		final Geocoder gcd = new Geocoder(getApplicationContext());

		List<Address> addresses = gcd.getFromLocation(loc.getLatitude(),
				loc.getLongitude(), 10);

		// get zip code based on current location, only select the 1st element
		for (Address address : addresses) {
			if (address.getLocality() != null
					&& address.getPostalCode() != null) {
				Log.d("LOCALITY", address.getLocality());
				Log.d("ZIP", address.getPostalCode());
				zipCode = address.getPostalCode();
				break;
			}
		}
	}

	/**
	 * Deprecated
	 * @param loc
	 */
	public void putMarker(Location loc) {
		LatLng currentPosition = new LatLng(loc.getLatitude(),
				loc.getLongitude());
		map.addMarker(new MarkerOptions().position(currentPosition)).setTitle(
				"Find me here");
		// Update camera
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(
				currentPosition, 14);
		map.animateCamera(update);
	}

	/**
	 * Multiple markers based on the zip code filter
	 * 
	 * @throws IOException
	 */
	public void putMultiMarkers() throws IOException {
		//
		List <Store> storeList = sh.getStoreByZip(zipCode);
		ArrayList<String> addrList = new ArrayList<String>();
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> storeIdList = new ArrayList<String>();
		if (storeList != null && storeList.size() != 0) {
			for (Store store : storeList) {
				nameList.add(store.getStoreName());
				addrList.add(store.getAddress()+" "+store.getState()+" "
				+store.getZip());
				storeIdList.add(store.getStoreID());
			}
		} else {
			AlertDialog.Builder builder = new Builder(LocatorActivity.this);
			builder.setMessage("No nearby stores unavailable.");
			builder.show();
		}
		

		// useful code
		final Geocoder gcd = new Geocoder(getApplicationContext());
		LatLng pos = null;
		for (int i = 0; i < addrList.size(); i++) {
			List<Address> addresses = gcd.getFromLocationName(addrList.get(i), 10);
			for (Address address : addresses) {
				pos = new LatLng(address.getLatitude(), address.getLongitude());
				map.addMarker(new MarkerOptions().position(pos))
				.setTitle(nameList.get(i)+", "+addrList.get(i) +
						"," + storeIdList.get(i)); //+", "+addrList.get(i)
			}
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(pos, 14);
			map.animateCamera(update);
			
			/*
			 * test
			 */
			map.setInfoWindowAdapter(new InfoWindowAdapter() {

		        // Use default InfoWindow frame
		        @Override
		        public View getInfoWindow(Marker args) {
		            return null;
		        }

		        // Defines the contents of the InfoWindow
		        @Override
		        public View getInfoContents(Marker args) {

		            // Getting view from the layout file info_window_layout
		            View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);
		            
		            // Getting the position from the marker
		            
		            LatLng clickMarkerLatLng = args.getPosition();
		            addrBundle = args.getTitle().split(",");

		            TextView title = (TextView) v.findViewById(R.id.info_window_name);
		            TextView infoAddr = (TextView) v.findViewById(R.id.info_window_address);
		            title.setText(addrBundle[0]);
		            infoAddr.setText(addrBundle[1]);

		            map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
		            	
		                public void onInfoWindowClick(Marker marker) 
		                {

		                	Intent intent = new Intent(getApplicationContext() ,FormActivity.class);
		                	Bundle bundle = new Bundle();
		        			bundle.putString("storeId",addrBundle[2]);
		        			intent.putExtras(bundle);
		                	startActivity(intent);
		                }
		            });

		            // Returning the view containing InfoWindow contents
		            return v;

		        }
		    });  
			
			/*test ends here */
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.locator, menu);
		return true;
	}
	
	
	
	
	
}
