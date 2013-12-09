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
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * After login, users will be directed to OptionMenu Activity where they 
 * can select the next step.
 *  
 * @author yinxu
 *
 */
public class OptionMenuActivity extends Activity{
	
	private Context mContext;
	
	//Views
	private Button checkPump;
	private Button button1;
	private Button button2;
	private Button button3;
	
	//
	private ProgressDialog pDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		//set layout
		setContentView(R.layout.option_menu_activity);
		
		//associate views
		checkPump = (Button) findViewById(R.id.check_pump);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button4);
		setListeners();
	}
	
	public void setListeners() {
		checkPump.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Menu", "check pump is clicked");
				
				
				// new version: start gps
				pDialog = new ProgressDialog(OptionMenuActivity.this);
				pDialog.setTitle("Locating");
				pDialog.setMessage("Please wait");
				pDialog.show();
				Intent locatorIntent = new Intent(OptionMenuActivity.this, LocatorActivity.class);
				//put the progressDialog reference into next Activity
				startActivity(locatorIntent);
				
			}
		});
	}
	
	/**
	 * Unused in this class.
	 */
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
	
	/**
	 * Dismiss the progressDialog when the Activity is not active.
	 */
	@Override
	protected void onPause () {
		super.onPause();
		
		if (pDialog != null) {
			pDialog.dismiss();
		}
	}
	

}
