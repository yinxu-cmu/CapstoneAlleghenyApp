/**
 * 
 */
package edu.cmu.alleghenywm;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * After login, user will be directed to OptionMenu Activity where he 
 * can select the next step.
 *  
 * @author yinxu
 *
 */
public class OptionMenuActivity extends Activity{
	
	private Context mContext;
	
	//Views
	private Button scanBarcode;
	private Button button1;
	private Button button2;
	private Button button3;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		//set layout
		setContentView(R.layout.option_menu_activity);
		
		//associate views
		scanBarcode = (Button) findViewById(R.id.scan_barcode);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button4);
		setListeners();
	}
	
	public void setListeners() {
		scanBarcode.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Menu", "scan barcode is clicked");
				//Toast.makeText(mContext, "start barcode scanner", Toast.LENGTH_SHORT).show();
				//instantiate ZXing integration class
				IntentIntegrator scanIntegrator = new IntentIntegrator(OptionMenuActivity.this);
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
			
			/*
			 * after getting the result, start FormActivity with barcode as input
			 */
			Intent formIntent = new Intent(OptionMenuActivity.this, FormActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("barcode_result",scanContent);
			formIntent.putExtras(bundle);
			startActivity(formIntent);
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
