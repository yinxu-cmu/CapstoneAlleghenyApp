/**
 * 
 */
package edu.cmu.allegheny.activities;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.R.id;
import edu.cmu.allegheny.R.layout;
import edu.cmu.allegheny.data.Device;
import edu.cmu.allegheny.data.DeviceHandler;
import edu.cmu.allegheny.data.FormHandler;
import edu.cmu.allegheny.data.FormLine;
import edu.cmu.allegheny.data.Store;
import edu.cmu.allegheny.data.StoreHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yinxu
 *
 */
public class DeviceActivity extends Activity{
	
	//Input Views
	private TextView barcode;
	private String deviceId;
	private EditText makeOfPump;
	private EditText sn;
	private EditText pumpNum;
	private EditText brandOfGas;
	private EditText gallonsDrawn;
	private EditText errorFast;
	private EditText errorSlow;
	private EditText tolTable;
	private Spinner spinner1;
	private EditText remarks;
	
	//Input strings
	private String makeOfPumpStr;
	private String snStr;
	private String pumpNumStr;
	private String brandOfGasStr;
	private String gallonsDrawnStr;
	private String errorFastStr;
	private String errorSlowStr;
	private String tolTableStr;
	private String spinner1Str;
	private String remarksStr;
	
	//Buttons
	private Button submitBtn;
	private Button nxtPumpBtn;
	
	//data transaction
	private DeviceHandler dh;
	private Device device;
	private FormHandler fh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_activity);
//		deviceId = (EditText) findViewById(R.id.de);
		makeOfPump = (EditText) findViewById(R.id.device_activity_makeofpump_input);
		sn = (EditText) findViewById(R.id.device_activity_sn_input);
		pumpNum = (EditText) findViewById(R.id.device_activity_pumpnumber_input);
		brandOfGas = (EditText) findViewById(R.id.device_activity_brandofgas_input);
		gallonsDrawn = (EditText) findViewById(R.id.device_activity_gallonsdrawn_input);
		errorFast = (EditText) findViewById(R.id.device_activity_errorfast_input);
		errorSlow = (EditText) findViewById(R.id.device_activity_errorslow_input);
		tolTable = (EditText) findViewById(R.id.device_activity_toltable_input);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		remarks = (EditText) findViewById(R.id.device_activity_remarks_input);
		
		submitBtn = (Button) findViewById(R.id.device_btn_submit);
		nxtPumpBtn = (Button) findViewById(R.id.device_btn_next_pump);
		
		Bundle bundle1 = this.getIntent().getExtras();
		deviceId = bundle1.getString("barcode_result");
		
		//set a default device for demo purpose
		if (deviceId == null) {
			deviceId = "40";
		}
		
//		Log.d("DEVICE", deviceId);
		brandOfGas.setText(deviceId);
		
		dh = new DeviceHandler(this);
		fh = new FormHandler(this);
		
		Log.d("DEVICE", "going to search device");
		device = dh.getDevice(deviceId);
		Log.d("DEVICE", "search device done");
		
		//set a default device for demo purpose
		if(device == null) {
			device = dh.getDevice("40");
		}
		
		//set views
		makeOfPump.setText(device.getMake());
		sn.setText(device.getSerialNumber());
		pumpNum.setText(device.getPump());
		gallonsDrawn.setText("5");
		
		setListeners();
	}
	
	/**
	 * set button listeners.
	 */
	public void setListeners() {
		
		submitBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// 1. save the record
				saveFormLine();
				// 2. return to map Activity
				//test file path
				Intent sumIntent = new Intent(DeviceActivity.this, SummaryActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("storeId", device.getStoreID());
				sumIntent.putExtras(bundle);
				startActivity(sumIntent);
			}
		});
		
		nxtPumpBtn.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// 1. save 
				 saveFormLine();
				// 2. start another pump check activity
				Intent pumpCheck = new Intent(DeviceActivity.this, CheckPumpActivity.class);
				startActivity(pumpCheck);
			}
		});
		
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	/**
	 * save form line to db
	 * @return success or not
	 */
	public boolean saveFormLine() {
		makeOfPumpStr = makeOfPump.getText().toString();
		snStr = sn.getText().toString();
		pumpNumStr = pumpNum.getText().toString();
		brandOfGasStr = brandOfGas.getText().toString();
		gallonsDrawnStr = gallonsDrawn.getText().toString();
		errorFastStr = errorFast.getText().toString();
		errorSlowStr = errorSlow.getText().toString();
		tolTableStr = tolTable.getText().toString();
		spinner1Str = String.valueOf(spinner1.getSelectedItem());
		remarksStr = remarks.getText().toString();
		
		FormLine formLine = new FormLine(null, null, null, null, null,
				null, makeOfPumpStr, snStr, pumpNumStr, brandOfGasStr, gallonsDrawnStr,
				errorSlowStr, errorFastStr, tolTableStr, spinner1Str, remarksStr);
		fh.addForm(formLine);
		
		return true;
	}
}
