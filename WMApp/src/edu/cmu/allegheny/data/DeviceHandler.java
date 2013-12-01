package edu.cmu.allegheny.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
 * Class that handles CRUD based calls to the internal SQLite JobAid database
 */
public class DeviceHandler extends SQLiteOpenHelper{
	
	//All Static variables
		//Database Version
	private static final int DATABASE_VERSION = 1;
	
	
	
	//Database Name
	private static final String DATABASE_NAME = "deviceManager";
	
	private static final String TABLE_DEVICE = "devices";
	
	//Table Column names
	private static final String KEY_ID = "id";
	private static final String KEY_STOREID = "storeid";
	private static final String KEY_DEVICETYPE = "devicetype";
	private static final String KEY_DEVICEGROUP = "devicegroup";
	private static final String KEY_MAKE = "make";
	private static final String KEY_MODEL = "model";
	private static final String KEY_SERIALNUMBER = "serialnumber";
	private static final String KEY_INSPECTIONCYCLE = "inspectioncycle";
	private static final String KEY_PUMP = "pump";
	private static final String KEY_GRADE = "grade";
	private static final String KEY_CAPACITY = "capacity";
	private static final String KEY_REMARKS = "remarks";
	private static final String KEY_OLDDUEDATE = "oldduedate";

	
		
	public DeviceHandler(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	//starts the activity .. for creation
	public void onCreate(SQLiteDatabase db) {
		String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +TABLE_DEVICE+
				"("+KEY_ID+" TEXT PRIMARY KEY,"+KEY_STOREID+" TEXT,"
				+KEY_DEVICETYPE+" TEXT,"+KEY_DEVICEGROUP+" TEXT,"+KEY_MAKE+" TEXT,"
				+KEY_MODEL+" TEXT,"+KEY_SERIALNUMBER+" TEXT,"+KEY_INSPECTIONCYCLE+" TEXT,"
				+KEY_PUMP+" TEXT,"+KEY_GRADE+" TEXT,"+KEY_CAPACITY+" TEXT,"+KEY_REMARKS+" TEXT,"
				+KEY_OLDDUEDATE+" TEXT"+")";
		db.execSQL(CREATE_QUESTIONS_TABLE);
		
	}
	



	@Override

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//drop older table if existing
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_DEVICE);
		
		//Create tables again
		onCreate(db);
	}
	//adds a response to the database
	public void addDevice (Device device){
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_ID, device.getDeviceID());
			values.put(KEY_STOREID, device.getStoreID());
			values.put(KEY_DEVICETYPE, device.getDeviceType());
			values.put(KEY_DEVICEGROUP, device.getDeviceGroup());
			values.put(KEY_MAKE, device.getMake());
			values.put(KEY_MODEL, device.getModel());
			values.put(KEY_SERIALNUMBER, device.getSerialNumber());
			values.put(KEY_INSPECTIONCYCLE, device.getInspectionCycle());
			values.put(KEY_PUMP, device.getPump());
			values.put(KEY_CAPACITY, device.getCapacity());
			values.put(KEY_REMARKS, device.getRemarks());
			//Inserting Row
			db.insert(TABLE_DEVICE, null, values);
			db.close();
		}

	
	//START HERE!!!!!
	
	//Getting single question
	public Device getDevice(String id){
		
		try{
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_DEVICE, new String[]{
					KEY_ID,KEY_STOREID,KEY_DEVICETYPE,KEY_DEVICEGROUP,KEY_MAKE,KEY_MODEL,
					KEY_SERIALNUMBER,KEY_INSPECTIONCYCLE,KEY_PUMP,KEY_GRADE,KEY_CAPACITY,
					KEY_REMARKS,KEY_OLDDUEDATE},KEY_ID+"=?",
					new String[]{id},null,null,null,null);
			if(cursor != null)
				cursor.moveToFirst();
			Device response = new Device(cursor.getString(0),cursor.getString(1), 
					cursor.getString(2),cursor.getString(3),cursor.getString(4),
					cursor.getString(5),cursor.getString(6),cursor.getString(7),
					cursor.getString(8),cursor.getString(9),cursor.getString(10),
					cursor.getString(11),cursor.getString(12));
			
			db.close();
			return response;
		}catch(CursorIndexOutOfBoundsException e){
			return null;
		}
		
		
	}
	
	//Getting All Questions
	public List<Device> getAllDevices(){
		
		List<Device> deviceList = new ArrayList();
		//Select All Query
		String selectQuery = "SELECT * FROM "+ TABLE_DEVICE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Device dev = new Device();
				
				dev.setDeviceID(cursor.getString(0));
				dev.setStoreID(cursor.getString(1));
				dev.setDeviceType(cursor.getString(2));
				dev.setDeviceGroup(cursor.getString(3));
				dev.setMake(cursor.getString(4));
				dev.setModel(cursor.getString(5));
				dev.setSerialNumber(cursor.getString(6));
				dev.setInspectionCycle(cursor.getString(7));
				dev.setPump(cursor.getString(8));
				dev.setGrade(cursor.getString(9));
				dev.setCapacity(cursor.getString(10));
				dev.setRemarks(cursor.getString(11));
				dev.setOldDueDate(cursor.getString(12));
				
				//Adding contact to list
				deviceList.add(dev);
			}while(cursor.moveToNext());
		}
		
		
		return deviceList;
		
	}
	

	
	

	////Getting response Count
	public int getDeviceCount(){

			String countQuery = "SELECT * FROM "+ TABLE_DEVICE;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			int i = cursor.getCount();
			cursor.close();
			
			//return count
			return i;

		
		
	}
	
	
	//Updating single response
	public int updateDevice(Device dev){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, dev.getDeviceID());
		values.put(KEY_STOREID,dev.getStoreID());
		values.put(KEY_DEVICETYPE,dev.getDeviceType());
		values.put(KEY_DEVICEGROUP, dev.getDeviceGroup());
		values.put(KEY_MAKE, dev.getMake());
		values.put(KEY_MODEL, dev.getModel());
		values.put(KEY_SERIALNUMBER, dev.getSerialNumber());
		values.put(KEY_INSPECTIONCYCLE, dev.getInspectionCycle());
		values.put(KEY_PUMP, dev.getPump());
		values.put(KEY_GRADE, dev.getGrade());
		values.put(KEY_CAPACITY, dev.getCapacity());
		values.put(KEY_REMARKS, dev.getRemarks());
		values.put(KEY_OLDDUEDATE, dev.getOldDueDate());
		
		return db.update(TABLE_DEVICE, values, KEY_ID+" = ?", new String[]{dev.getDeviceID()});
		
	}
	
	//Deleting single response
	public void deleteDevice(Device dev){
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DEVICE, KEY_ID+" = ?", new String[]{dev.getDeviceID()});
		
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
