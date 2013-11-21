package com.example.capstonedata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * To do:
 * 1. create device class and db handler DONE
 * 2. create store class and db handler DONE
 * 3. create user class and db handler DONE
 * 4. make demo program that shows all info DONE
 * 5. create a form class that holds all form info
 */


public class MainActivity extends Activity {


	DeviceHandler dh;
	StoreHandler sh;
	UserHandler uh;
	EditText et;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.w("test", "create items");
		Button goDevice = (Button) findViewById(R.id.button1);
		et = (EditText) findViewById(R.id.editText1);
		tv = (TextView) findViewById(R.id.textView2);
		Button goStore = (Button) findViewById(R.id.button2);
		Button reset = (Button) findViewById(R.id.button3);
		Button user = (Button) findViewById(R.id.button4);
		
		//get new object of the used databases
		dh = new DeviceHandler(this);
		sh = new StoreHandler(this);
		uh = new UserHandler(this);
		
		//initiates databases w data if no data exists
		if(dh.getDeviceCount() == 0){
			loadDevices();
		}
		
		if(sh.getStoreCount() == 0){
			loadStores();
		}
		
		if(uh.getUserCount()==0){
			loadUsers();
		}
		

		
		goDevice.setOnClickListener(new OnClickListener(){
			public void onClick(View viewParam){
				findDevice();
			}
		});
		
		goStore.setOnClickListener(new OnClickListener(){
			public void onClick(View viewParam){
				findStore();
			}
		});
		
		reset.setOnClickListener(new OnClickListener(){
			public void onClick(View viewParam){
				reset();
			}
		});
		user.setOnClickListener(new OnClickListener(){
			public void onClick(View viewParam){
				findUser();
			}
		});
		
		
		//StoreHandler sh = new StoreHandler(this);
	}
	
	
	public void reset(){
		dh.onUpgrade(dh.getReadableDatabase(), dh.getVersion(), dh.getVersion()+1);
		this.loadDevices();
	}
	
	public void findDevice(){
		Device dev = dh.getDevice(et.getText().toString());
		if(dev != null){
			tv.setText(dev.getDeviceID()+" "+dev.getDeviceType()+" "+dev.getSerialNumber());
		}else{
			tv.setText("no device by that id");
		}
		
	}
	
	public void findStore(){
		Store store = sh.getStore(et.getText().toString());
		if(store != null){
			tv.setText(store.getStoreID()+" "+store.getStoreName()+" "+store.getBusinessPhone());
		}else{
			tv.setText("no store by that id");
		}
		
	}
	
	public void findUser(){
		User user = uh.getUser(et.getText().toString());
		if(user != null){
			tv.setText(user.getUsername()+" "+user.getPassword());
		}else{
			tv.setText("no user by that id");
		}
		
	}
	
	private void loadUsers(){
		User user = new User("12345","test");
		uh.addUser(user);
	}
	
	private void loadDevices(){
		Resources res = getResources();
		InputStream inDevice = res.openRawResource(R.raw.devicessample);
		//InputStream inStore = res.openRawResource(R.raw.storessample);
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		
		
		try {
			br = new BufferedReader(new InputStreamReader(inDevice));
			while((line = br.readLine())!=null){
				String[] dev = line.split(splitBy);
				
				//System.out.println(country[1]+" "+country[2]);
				Device device = new Device(dev[0],dev[1],dev[2],dev[3],dev[4],
						dev[5],dev[6],dev[7],dev[8],dev[9],dev[10],dev[11],dev[12]);
				
				dh.addDevice(device);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void loadStores(){
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
		
	}
	
	
	
	/*public void loadStore(){
		Resources res = getResources();

		InputStream inStore = res.openRawResource(R.raw.storessample);
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		
		
		try {
			br = new BufferedReader(new InputStreamReader(inStore));
			while((line = br.readLine())!=null){
				String[] sto = line.split(splitBy);
				
				//System.out.println(country[1]+" "+country[2]);
				Stores store = new Device(sto[0],sto[1],sto[2],sto[3],sto[4],
						sto[5],sto[6],sto[7],sto[8],sto[9],sto[10],sto[11],sto[12]);
				
				sh.addDevice(device);;
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
