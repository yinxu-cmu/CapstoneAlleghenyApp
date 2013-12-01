package edu.cmu.allegheny.activities;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.data.Device;
import edu.cmu.allegheny.data.DeviceHandler;
import edu.cmu.allegheny.data.Store;
import edu.cmu.allegheny.data.StoreHandler;
import edu.cmu.allegheny.data.User;
import edu.cmu.allegheny.data.UserHandler;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LoginActivity extends Activity {
	
	//Views in the activity
	private EditText userNameEText;
	private EditText passwordEText;
	
	private Button loginBtn;
	
	//Data Transaction
	private UserHandler uHandler;
	private StoreHandler sh;
	private DeviceHandler dh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		userNameEText = (EditText) findViewById(R.id.usernameEText);
		passwordEText = (EditText) findViewById(R.id.passwordEdTxt);
		loginBtn = (Button) findViewById(R.id.loginBtn);
		
		//config Views
		userNameEText.setCursorVisible(true);
		passwordEText.setCursorVisible(true);
		userNameEText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		
		//hide status bar
//		View decorView = getWindow().getDecorView();
//		// Hide the status bar.
//		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//		decorView.setSystemUiVisibility(uiOptions);
//		// Remember that you should never show the action bar if the
//		// status bar is hidden, so hide that too if necessary.
//		ActionBar actionBar = getActionBar();
//		actionBar.hide();
		
		//config data handler
		//initiates databases w data if no data exists
		uHandler = new UserHandler(this);
		if(uHandler.getUserCount()==0){
			loadUsers();
		}
		sh = new StoreHandler(this);
		if(sh.getStoreCount() == 0){
			loadStores();
		}
		dh = new DeviceHandler(this);
		if(dh.getDeviceCount() == 0){
			loadDevices();
		}
		
		//config Listerns
		loginBtn.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				
				String sUserName = userNameEText.getText().toString();
				String sPassword = passwordEText.getText().toString();
				// debug
				Log.d("LOGIN", "login clicked. Matching...");
				
				if(validateFields(sUserName, sPassword)) {
					Log.d("LOGIN", "FILEDS VALID");
					//
					if (checkCredentials(sUserName, sPassword)) {
						Toast.makeText(LoginActivity.this, R.string.login_success,
								Toast.LENGTH_SHORT).show();
						Log.d("LOGIN", "Successful");
						Intent optionMenu = new Intent(LoginActivity.this, OptionMenuActivity.class);
						startActivity(optionMenu); // ??? ADD BUNDLE!
					} else {
						Toast.makeText(LoginActivity.this, R.string.login_fail,
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(LoginActivity.this, R.string.insert_valid_data,
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	/**
	 * Checks the consistency of each of the text fields
	 * 
	 * @return true if no errors found, false otherwise
	 */
	private boolean validateFields(String username, String password) {
		if (username.length() == 0) {
			userNameEText.requestFocus();
			return false;
		} else if (password.length() == 0) {
			passwordEText.requestFocus();
			return false;
		}
		return true;
	}
	
	/**
	 * Check the credentials
	 */
	private boolean checkCredentials(String username, String password) {
		//
		User user = uHandler.getUser(username);
		if(user != null && user.getPassword().equals(password)){
			
			return true;
		}
		
		return false;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
//		return true;
//	}
	
	/**
	 * load user?
	 */
	private void loadUsers(){
		User user = new User("123","pwd");
		uHandler.addUser(user);
	}
	
	/**
	 * load store info
	 */
	private void loadStores(){
		LoadStoresTask task = new LoadStoresTask();
		task.execute(null, null);
	}
	
	/**
	 * Multi-threading, used to load the store data 
	 * @author yinxu
	 *
	 */
	public class LoadStoresTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// 
			Resources res = getResources();
			InputStream inStores = res.openRawResource(R.raw.storessample);
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";
			
			
			try {
				br = new BufferedReader(new InputStreamReader(inStores));
				while((line = br.readLine())!=null){
					String[] dev = line.split(splitBy);
					Log.w("stores", "start load");
					Log.w("stores", ""+dev.length);
					//System.out.println(country[1]+" "+country[2]);
					Store store = new Store(dev[0],dev[1],dev[2],dev[3],dev[4],
							dev[5],dev[6],dev[7],dev[8],dev[9],dev[10],dev[11],dev[12],
							dev[13],dev[14],dev[15],dev[16],dev[17],dev[18],dev[19],
							dev[20],dev[21],dev[22],dev[23],dev[24],dev[25]);
					
					
					
					sh.addStore(store);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.w("stores", "stores loaded");
			return null;
		}
		
	}
	
	private void loadDevices() {
		
		LoadDevicesTask task = new LoadDevicesTask();
		task.execute(null, null);
	}
	
	/**
	 * Load devices thread
	 * @author yinxu
	 *
	 */
	public class LoadDevicesTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// 
			Resources res = getResources();
			InputStream inDevice = res.openRawResource(R.raw.devicessample);
			// InputStream inStore = res.openRawResource(R.raw.storessample);
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";

			try {
				br = new BufferedReader(new InputStreamReader(inDevice));
				while ((line = br.readLine()) != null) {
					String[] dev = line.split(splitBy);

					// System.out.println(country[1]+" "+country[2]);
					Device device = new Device(dev[0], dev[1], dev[2], dev[3],
							dev[4], dev[5], dev[6], dev[7], dev[8], dev[9],
							dev[10], dev[11], dev[12]);

					dh.addDevice(device);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}

}
