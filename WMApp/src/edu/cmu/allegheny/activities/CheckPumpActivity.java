/**
 * 
 */
package edu.cmu.allegheny.activities;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.R.id;
import edu.cmu.allegheny.R.layout;
import edu.cmu.allegheny.util.IntentIntegrator;
import edu.cmu.allegheny.util.IntentResult;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yinxu
 *
 */
public class CheckPumpActivity extends Activity{
	
	private Context mContext;
	//Views
	private TextView text;
	private Button dialogButton1;
	private Button dialogButton2;
	private Button dialogButton3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.check_pump_activity);
		
		text = (TextView) findViewById(R.id.check_pump_dialog_text);
		dialogButton2 = (Button) findViewById(R.id.check_pump_dialog_input_barcode);
		dialogButton3 = (Button) findViewById(R.id.check_pump_dialog_scan_barcode);
		
		setListeners();
	}
	
	public void setListeners() {
		
//		dialogButton1.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
////				dismiss();
//				//start gps locator
//				//For Future Usage
//			}
//		});
		
		dialogButton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				dismiss();
				//start manual input
				LayoutInflater inflater = CheckPumpActivity.this.getLayoutInflater();
		        View layout=inflater.inflate(R.layout.manual_input_dialog, null); 
				
				AlertDialog.Builder builder = new Builder(CheckPumpActivity.this);
				builder.setTitle("Input Device ID");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setView(layout);
				
				final EditText deviceId = (EditText) layout.findViewById(R.id.manual_input_deviceId);
				
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// get the EditText value
						String sDeviceId = deviceId.getText().toString();
						
						Intent formIntent = new Intent(CheckPumpActivity.this, DeviceActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("barcode_result", sDeviceId);
						formIntent.putExtras(bundle);
						startActivity(formIntent);
					}
				});
				builder.setNegativeButton("Cancel", null);
				builder.show();
			}
		});
		
		dialogButton3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				dismiss();
				//start barcode scan
				//Toast.makeText(mContext, "start barcode scanner", Toast.LENGTH_SHORT).show();
				//instantiate ZXing integration class
				IntentIntegrator scanIntegrator = new IntentIntegrator(CheckPumpActivity.this);
				//start scanning
				scanIntegrator.initiateScan();
			}
		});
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			//get content from Intent Result
			String scanContent = scanningResult.getContents();
			
			// error handling
			if (scanContent == null || scanContent.length() == 0) {
				//TO-DO
				
			} else {
				
				/*
				 * after getting the result, start FormActivity with barcode as input
				 */
				Intent deviceIntent = new Intent(CheckPumpActivity.this, DeviceActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("barcode_result",scanContent);
				deviceIntent.putExtras(bundle);
				startActivity(deviceIntent);
			}
			
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
