package com.example.capstonedata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/*
 * Class that handles CRUD based calls to the internal SQLite JobAid database
 */
public class StoreHandler extends SQLiteOpenHelper{
	
	//All Static variables
		//Database Version
	private static final int DATABASE_VERSION = 1;
	
	
	
	//Database Name
	private static final String DATABASE_NAME = "storeManager";
	
	private static final String TABLE_STORES = "stores";
	
	//Table Column names
	private static final String KEY_STOREID = "id";
	private static final String KEY_STORENAME = "storename";
	private static final String KEY_ADDRESS = "address";
	private static final String KEY_MAILINGCITY = "mailingcity";
	private static final String KEY_STATE = "state";
	private static final String KEY_ZIP = "zip";
	private static final String KEY_MUNICIPALITY = "municipality";
	private static final String KEY_CORPID = "corpid";
	private static final String KEY_NEIGHBORHOOD = "neighborhood";
	private static final String KEY_BUSINESSPHONE = "businessphone";
	private static final String KEY_ALTERNATEPHONE = "alternatephone";
	private static final String KEY_PRICE_VERIFICATION = "price_verification";
	private static final String KEY_PRICE_VERDUEDATE = "price_verduedate";
	private static final String KEY_FUELDISPENSER = "fueldispenser";
	private static final String KEY_FUELDUEDATE = "fuelduedate";
	private static final String KEY_SCALE = "scale";
	private static final String KEY_SCALEDUEDATE = "scaleduedate";
	private static final String KEY_TIMING = "timing";
	private static final String KEY_TIMINGDUEDATE = "timingduedate";
	private static final String KEY_MISCINSPECTION = "miscinspection";
	private static final String KEY_MISCDUEDATE = "miscduedate";
	private static final String KEY_FEE = "fee";
	private static final String KEY_OOB = "oob";
	private static final String KEY_REMARKS = "remarks";
	private static final String KEY_NEWADDRESS = "newaddress";
	private static final String KEY_NEWMUNICIPALITY = "newmunicipality";
	
		
	public StoreHandler(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	//START HERE
	
	
	@Override
	//starts the activity .. for creation
	public void onCreate(SQLiteDatabase db) {
		String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +TABLE_STORES+
				"("+KEY_STOREID+" TEXT PRIMARY KEY,"+KEY_STORENAME+" TEXT,"
				+KEY_ADDRESS+" TEXT,"+KEY_MAILINGCITY+" TEXT,"+KEY_STATE+" TEXT,"
				+KEY_ZIP+" TEXT,"+KEY_MUNICIPALITY+" TEXT,"+KEY_CORPID+" TEXT,"
				+KEY_NEIGHBORHOOD+" TEXT,"+KEY_BUSINESSPHONE+" TEXT,"+KEY_ALTERNATEPHONE+" TEXT,"+KEY_PRICE_VERIFICATION+" TEXT,"
				+KEY_PRICE_VERDUEDATE+" TEXT,"+KEY_FUELDISPENSER+" TEXT,"+KEY_FUELDUEDATE+" TEXT,"+
				KEY_SCALE+" TEXT,"+KEY_SCALEDUEDATE+" TEXT,"+KEY_TIMING+" TEXT,"+KEY_TIMINGDUEDATE+" TEXT,"+
				KEY_MISCINSPECTION+" TEXT,"+KEY_MISCDUEDATE+" TEXT,"+KEY_FEE+" TEXT,"+KEY_OOB+" TEXT,"+
				KEY_REMARKS+" TEXT,"+KEY_NEWADDRESS+" TEXT,"+KEY_NEWMUNICIPALITY+" TEXT"+")";
		db.execSQL(CREATE_QUESTIONS_TABLE);
		
	}
	



	@Override

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//drop older table if existing
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_STORES);
		
		//Create tables again
		onCreate(db);
	}
	//adds a response to the database
	public void addStore (Store store){
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_STOREID, store.getStoreID());
			values.put(KEY_STORENAME, store.getStoreName());
			values.put(KEY_ADDRESS, store.getAddress());
			values.put(KEY_MAILINGCITY, store.getMailingCity());
			values.put(KEY_STATE, store.getState());
			values.put(KEY_ZIP, store.getZip());
			values.put(KEY_MUNICIPALITY, store.getMunicipality());
			values.put(KEY_CORPID, store.getCorpID());
			values.put(KEY_NEIGHBORHOOD, store.getNeighborhood());
			values.put(KEY_ALTERNATEPHONE, store.getAlternatePhone());
			values.put(KEY_PRICE_VERIFICATION, store.getPrice_Verification());
			values.put(KEY_PRICE_VERDUEDATE, store.getPrice_VerDueDate());
			values.put(KEY_FUELDISPENSER, store.getFuelDispenser());
			values.put(KEY_FUELDUEDATE, store.getFuelDueDate());
			values.put(KEY_SCALE, store.getScale());
			values.put(KEY_SCALEDUEDATE, store.getScaleDueDate());
			values.put(KEY_TIMING, store.getTiming());
			values.put(KEY_TIMINGDUEDATE, store.getTimingDueDate());
			values.put(KEY_MISCINSPECTION, store.getMiscInspection());
			values.put(KEY_MISCDUEDATE, store.getMiscDueDate());
			values.put(KEY_FEE, store.getFee());
			values.put(KEY_OOB, store.getOOB());
			values.put(KEY_REMARKS, store.getRemarks());
			values.put(KEY_NEWADDRESS, store.getNewAddress());
			values.put(KEY_NEWMUNICIPALITY, store.getNewMunicipality());
			
			//Inserting Row
			db.insert(TABLE_STORES, null, values);
			db.close();
		}


	
	//Getting single store
	public Store getStore(String id){
		
		try{
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_STORES, new String[]{
					KEY_STOREID,KEY_STORENAME,KEY_ADDRESS,KEY_MAILINGCITY,KEY_STATE,KEY_ZIP,
					KEY_MUNICIPALITY,KEY_CORPID,KEY_NEIGHBORHOOD,KEY_BUSINESSPHONE,KEY_ALTERNATEPHONE,
					KEY_PRICE_VERIFICATION,KEY_PRICE_VERDUEDATE,KEY_FUELDISPENSER,KEY_FUELDUEDATE,
					KEY_SCALE,KEY_SCALEDUEDATE,KEY_TIMING,KEY_TIMINGDUEDATE,KEY_MISCINSPECTION,
					KEY_MISCDUEDATE, KEY_FEE,KEY_OOB, KEY_REMARKS, KEY_NEWADDRESS, KEY_NEWMUNICIPALITY},
					KEY_STOREID+"=?",new String[]{id},null,null,null,null);
			if(cursor != null)
				cursor.moveToFirst();
			Store response = new Store(cursor.getString(0),cursor.getString(1), 
					cursor.getString(2),cursor.getString(3),cursor.getString(4),
					cursor.getString(5),cursor.getString(6),cursor.getString(7),
					cursor.getString(8),cursor.getString(9),cursor.getString(10),
					cursor.getString(11),cursor.getString(12),cursor.getString(13),
					cursor.getString(14),cursor.getString(15),cursor.getString(16),
					cursor.getString(17),cursor.getString(18),cursor.getString(19),
					cursor.getString(20),cursor.getString(21),cursor.getString(22),
					cursor.getString(23),cursor.getString(24),cursor.getString(25));
			return response;
		}catch(CursorIndexOutOfBoundsException e){
			return null;
		}
		
	}
	
	
	//Getting all stores in a zipcode
		public List <Store> getStoreByZip(String zip){
			List<Store> storeList = new ArrayList();
			try{
				SQLiteDatabase db = this.getReadableDatabase();
				Cursor cursor = db.query(TABLE_STORES, new String[]{
						KEY_STOREID,KEY_STORENAME,KEY_ADDRESS,KEY_MAILINGCITY,KEY_STATE,KEY_ZIP,
						KEY_MUNICIPALITY,KEY_CORPID,KEY_NEIGHBORHOOD,KEY_BUSINESSPHONE,KEY_ALTERNATEPHONE,
						KEY_PRICE_VERIFICATION,KEY_PRICE_VERDUEDATE,KEY_FUELDISPENSER,KEY_FUELDUEDATE,
						KEY_SCALE,KEY_SCALEDUEDATE,KEY_TIMING,KEY_TIMINGDUEDATE,KEY_MISCINSPECTION,
						KEY_MISCDUEDATE, KEY_FEE,KEY_OOB, KEY_REMARKS, KEY_NEWADDRESS, KEY_NEWMUNICIPALITY},
						KEY_ZIP+"=?",new String[]{zip},null,null,null,null);
				if(cursor.moveToFirst()){
					do{
						Store response = new Store(cursor.getString(0),cursor.getString(1), 
								cursor.getString(2),cursor.getString(3),cursor.getString(4),
								cursor.getString(5),cursor.getString(6),cursor.getString(7),
								cursor.getString(8),cursor.getString(9),cursor.getString(10),
								cursor.getString(11),cursor.getString(12),cursor.getString(13),
								cursor.getString(14),cursor.getString(15),cursor.getString(16),
								cursor.getString(17),cursor.getString(18),cursor.getString(19),
								cursor.getString(20),cursor.getString(21),cursor.getString(22),
								cursor.getString(23),cursor.getString(24),cursor.getString(25));
						
						//Adding contact to list
						storeList.add(response);
					}while(cursor.moveToNext());
				}
				
				
				return storeList;
			}catch(CursorIndexOutOfBoundsException e){
				return null;
			}
			
		}
	
	
	//Getting All Stores
	public List<Store> getAllStores(){
		
		List<Store> storeList = new ArrayList();
		//Select All Query
		String selectQuery = "SELECT * FROM "+ TABLE_STORES;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Store response = new Store(cursor.getString(0),cursor.getString(1), 
						cursor.getString(2),cursor.getString(3),cursor.getString(4),
						cursor.getString(5),cursor.getString(6),cursor.getString(7),
						cursor.getString(8),cursor.getString(9),cursor.getString(10),
						cursor.getString(11),cursor.getString(12),cursor.getString(13),
						cursor.getString(14),cursor.getString(15),cursor.getString(16),
						cursor.getString(17),cursor.getString(18),cursor.getString(19),
						cursor.getString(20),cursor.getString(21),cursor.getString(22),
						cursor.getString(23),cursor.getString(24),cursor.getString(25));
				
				//Adding contact to list
				storeList.add(response);
			}while(cursor.moveToNext());
		}
		
		
		return storeList;
		
	}
	

	
	

	////Getting response Count
	public int getStoreCount(){

			String countQuery = "SELECT * FROM "+ TABLE_STORES;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			int i = cursor.getCount();
			cursor.close();
			
			//return count
			return i;

		
		
	}
	
	
	//Updating single response
	public int updateStore(Store store){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_STOREID, store.getStoreID());
		values.put(KEY_STORENAME, store.getStoreName());
		values.put(KEY_ADDRESS, store.getAddress());
		values.put(KEY_MAILINGCITY, store.getMailingCity());
		values.put(KEY_STATE, store.getState());
		values.put(KEY_ZIP, store.getZip());
		values.put(KEY_MUNICIPALITY, store.getMunicipality());
		values.put(KEY_CORPID, store.getCorpID());
		values.put(KEY_NEIGHBORHOOD, store.getNeighborhood());
		values.put(KEY_ALTERNATEPHONE, store.getAlternatePhone());
		values.put(KEY_PRICE_VERIFICATION, store.getPrice_Verification());
		values.put(KEY_PRICE_VERDUEDATE, store.getPrice_VerDueDate());
		values.put(KEY_FUELDISPENSER, store.getFuelDispenser());
		values.put(KEY_FUELDUEDATE, store.getFuelDueDate());
		values.put(KEY_SCALE, store.getScale());
		values.put(KEY_SCALEDUEDATE, store.getScaleDueDate());
		values.put(KEY_TIMING, store.getTiming());
		values.put(KEY_TIMINGDUEDATE, store.getTimingDueDate());
		values.put(KEY_MISCINSPECTION, store.getMiscInspection());
		values.put(KEY_MISCDUEDATE, store.getMiscDueDate());
		values.put(KEY_FEE, store.getFee());
		values.put(KEY_OOB, store.getOOB());
		values.put(KEY_REMARKS, store.getRemarks());
		values.put(KEY_NEWADDRESS, store.getNewAddress());
		values.put(KEY_NEWMUNICIPALITY, store.getNewMunicipality());
		
		
		
		return db.update(TABLE_STORES, values, KEY_STORENAME+" = ?", new String[]{store.getStoreID()});
		
	}
	
	//Deleting single response
	public void deleteStore(Store store){
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_STORES, KEY_STORENAME+" = ?", new String[]{store.getStoreID()});
		
	}
	//returns the database version
	public int getVersion() {
		return DATABASE_VERSION;
	}
	
	//Identifies what type of database this is
		public String getType(){
			return "DeviceHandler";
		}
	
}
