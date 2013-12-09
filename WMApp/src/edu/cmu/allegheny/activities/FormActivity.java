/**
 * 
 */
package edu.cmu.allegheny.activities;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.R.id;
import edu.cmu.allegheny.R.layout;
import edu.cmu.allegheny.data.Store;
import edu.cmu.allegheny.data.StoreHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Activity displays overall information of a gas station. After picking up
 * a station/store from the map view, users will be directed to FormActivity.
 * 
 * @author yinxu
 *
 */
public class FormActivity extends Activity{
	
	//Input Views
	private TextView barcode;
	private EditText storeId;
	private EditText station;
	private EditText address;
	private EditText cityState;
	private EditText zip;
	private EditText phone;
	
	//Buttons
	private Button returnBtn;
	private Button scanPumpBtn;
	
	//data transaction
	private StoreHandler sh;
	private Store store;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_activity);
		storeId = (EditText) findViewById(R.id.form_storeId_input);
		station = (EditText) findViewById(R.id.form_station_input);
		address = (EditText) findViewById(R.id.form_addr_input);
		cityState = (EditText) findViewById(R.id.form_city_state_input);
		zip = (EditText) findViewById(R.id.form_zip_input);
		phone = (EditText) findViewById(R.id.form_phone_input);
		
		returnBtn = (Button) findViewById(R.id.form_btn_return);
		scanPumpBtn = (Button) findViewById(R.id.form_btn_scan_pump);
		
		Bundle bundle1 = this.getIntent().getExtras();
		storeId.setText(bundle1.getString("storeId"));
		sh = new StoreHandler(this);
		store = sh.getStore(bundle1.getString("storeId"));
		
		if (store == null) {
			store = new Store();
		}
		
		station.setText(store.getStoreName());
		address.setText(store.getAddress());
		cityState.setText(store.getMunicipality() +" "+ store.getState());
		zip.setText(store.getZip());
		phone.setText(store.getBusinessPhone());
		
		setListeners();
	}
	
	/**
	 * Set listeners for buttons.
	 */
	public void setListeners() {
		returnBtn.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// 2. return to map Activity
//				Toast.makeText(getApplicationContext(),"return clicked", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		scanPumpBtn.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// 1. save
				// 2. start another pump check activity
				Intent pumpCheck = new Intent(FormActivity.this, CheckPumpActivity.class);
				startActivity(pumpCheck);
			}
		});
	}
}
