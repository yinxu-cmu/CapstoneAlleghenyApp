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
public class FormHandler extends SQLiteOpenHelper{
	
	//All Static variables
		//Database Version
	private static final int DATABASE_VERSION = 1;
	
	
	
	//Database Name
	private static final String DATABASE_NAME = "formManager";
	
	private static final String TABLE_FORM = "form";
	
	//Table Column names
	private static final String KEY_STATIONID = "stationid";
	private static final String KEY_ADDRESS = "address";
	private static final String KEY_CITY = "city";
	private static final String KEY_STATE = "state";
	private static final String KEY_ZIP = "zip";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_MAKEOFPUMP = "makeofpump";
	private static final String KEY_SERIALNUMBER = "serialnumber";
	private static final String KEY_PUMPNUMBER = "pumpnumber";
	private static final String KEY_BRANDOFGAS = "brandofgas";
	private static final String KEY_GALLONSDRAWN = "gallonsdrawn";
	private static final String KEY_ERRORSLOW = "errorslow";
	private static final String KEY_ERRORFAST = "errorfast";
	private static final String KEY_TOLTABLE = "toltable";
	private static final String KEY_ACTIONTAKEN = "actiontaken";
	private static final String KEY_REMARKS = "remarks";

	
		
	public FormHandler(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	//starts the activity .. for creation
	public void onCreate(SQLiteDatabase db) {
		String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +TABLE_FORM+
				"("+KEY_STATIONID+" TEXT PRIMARY KEY,"+KEY_ADDRESS+" TEXT,"
				+KEY_CITY+" TEXT,"+KEY_STATE+" TEXT,"+KEY_ZIP+" TEXT,"
				+KEY_PHONE+" TEXT,"+KEY_MAKEOFPUMP+" TEXT,"+KEY_SERIALNUMBER+" TEXT,"
				+KEY_PUMPNUMBER+" TEXT,"+KEY_BRANDOFGAS+" TEXT,"+KEY_GALLONSDRAWN+" TEXT,"+KEY_ERRORSLOW+" TEXT,"
				+KEY_ERRORFAST+" TEXT,"+KEY_TOLTABLE+" TEXT,"+KEY_ACTIONTAKEN+" TEXT,"+
				KEY_REMARKS+" TEXT"+")";
		db.execSQL(CREATE_QUESTIONS_TABLE);
		
	}
	



	@Override

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//drop older table if existing
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_FORM);
		
		//Create tables again
		onCreate(db);
	}
	//adds a response to the database
	public void addForm (FormLine line){
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_STATIONID, line.getStationID());
			values.put(KEY_ADDRESS, line.getAddress());
			values.put(KEY_CITY, line.getCity());
			values.put(KEY_STATE, line.getState());
			values.put(KEY_ZIP, line.getZip());
			values.put(KEY_PHONE, line.getPhone());
			values.put(KEY_MAKEOFPUMP, line.getMakeOfPump());
			values.put(KEY_SERIALNUMBER, line.getSerialNumber());
			values.put(KEY_PUMPNUMBER, line.getPumpNumber());
			values.put(KEY_GALLONSDRAWN, line.getGallonsDrawn());
			values.put(KEY_ERRORSLOW, line.getErrorSlow());
			values.put(KEY_ERRORFAST, line.getErrorFast());
			values.put(KEY_TOLTABLE, line.getTolTable());
			values.put(KEY_ACTIONTAKEN, line.getActionTaken());
			values.put(KEY_REMARKS, line.getRemarks());
			//Inserting Row
			db.insert(TABLE_FORM, null, values);
			db.close();
		}

	
	//START HERE!!!!!
	
	//Getting single question
	public FormLine getFormLine(String id){
		
		try{
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_FORM, new String[]{
					KEY_STATIONID,KEY_ADDRESS,KEY_CITY,KEY_STATE,KEY_ZIP,KEY_PHONE,
					KEY_MAKEOFPUMP,KEY_SERIALNUMBER,KEY_PUMPNUMBER,KEY_BRANDOFGAS,KEY_GALLONSDRAWN,
					KEY_ERRORSLOW,KEY_ERRORFAST,KEY_TOLTABLE,KEY_ACTIONTAKEN,KEY_REMARKS},KEY_STATIONID+"=?",
					new String[]{id},null,null,null,null);
			if(cursor != null)
				cursor.moveToFirst();
			FormLine response = new FormLine(cursor.getString(0),cursor.getString(1), 
					cursor.getString(2),cursor.getString(3),cursor.getString(4),
					cursor.getString(5),cursor.getString(6),cursor.getString(7),
					cursor.getString(8),cursor.getString(9),cursor.getString(10),
					cursor.getString(11),cursor.getString(12),cursor.getString(13),
					cursor.getString(14),cursor.getString(15));
			return response;
		}catch(CursorIndexOutOfBoundsException e){
			return null;
		}
		
		
	}
	
	//Getting All Questions
	public List<FormLine> getAllFormLines(){
		
		List<FormLine> FormLineList = new ArrayList();
		//Select All Query
		String selectQuery = "SELECT * FROM "+ TABLE_FORM;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				FormLine line = new FormLine();
				
				line.setStationID(cursor.getString(0));
				line.setAddress(cursor.getString(1));
				line.setCity(cursor.getString(2));
				line.setState(cursor.getString(3));
				line.setZip(cursor.getString(4));
				line.setPhone(cursor.getString(5));
				line.setMakeOfPump(cursor.getString(6));
				line.setSerialNumber(cursor.getString(7));
				line.setPumpNumber(cursor.getString(8));
				line.setBrandOfGas(cursor.getString(9));
				line.setGallonsDrawn(cursor.getString(10));
				line.setErrorSlow(cursor.getString(11));
				line.setErrorFast(cursor.getString(12));
				line.setTolTable(cursor.getString(13));
				line.setActionTaken(cursor.getString(14));
				line.setRemarks(cursor.getString(15));
				//Adding contact to list
				FormLineList.add(line);
			}while(cursor.moveToNext());
		}
		
		cursor.close();
		db.close();
		return FormLineList;
		
	}
	

	
	

	////Getting response Count
	public int getFormLineCount(){

			String countQuery = "SELECT * FROM "+ TABLE_FORM;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			int i = cursor.getCount();
			cursor.close();
			
			//return count
			return i;

		
		
	}
	
	
	//Updating single response
	public int updateFormLine(FormLine line){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_STATIONID, line.getStationID());
		values.put(KEY_ADDRESS,line.getAddress());
		values.put(KEY_CITY,line.getCity());
		values.put(KEY_STATE, line.getState());
		values.put(KEY_ZIP, line.getZip());
		values.put(KEY_PHONE, line.getPhone());
		values.put(KEY_MAKEOFPUMP, line.getMakeOfPump());
		values.put(KEY_SERIALNUMBER, line.getSerialNumber());
		values.put(KEY_PUMPNUMBER, line.getPumpNumber());
		values.put(KEY_BRANDOFGAS, line.getBrandOfGas());
		values.put(KEY_GALLONSDRAWN, line.getGallonsDrawn());
		values.put(KEY_ERRORSLOW, line.getErrorSlow());
		values.put(KEY_ERRORFAST, line.getErrorFast());
		values.put(KEY_TOLTABLE, line.getTolTable());
		values.put(KEY_ACTIONTAKEN, line.getActionTaken());
		values.put(KEY_REMARKS, line.getRemarks());
		
		return db.update(TABLE_FORM, values, KEY_STATIONID+" = ?", new String[]{line.getStationID()});
		
	}
	
	//Deleting single response
	public void deleteFormLine(FormLine line){
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FORM, KEY_STATIONID+" = ?", new String[]{line.getStationID()});
		
	}
	//returns the database version
	public int getVersion() {
		return DATABASE_VERSION;
	}
	
	//Identifies what type of database this is
		public String getType(){
			return "FormHandler";
		}
	
}
