package edu.cmu.allegheny.data;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.internal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
 * Class that handles CRUD based calls to the internal SQLite JobAid database
 */
public class UserHandler extends SQLiteOpenHelper{
	
	//All Static variables
		//Database Version
	private static final int DATABASE_VERSION = 1;
	
	
	
	//Database Name
	private static final String DATABASE_NAME = "userManager";
	
	private static final String TABLE_USER = "users";
	
	//Table Column names
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";


	
		
	public UserHandler(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	//starts the activity .. for creation
	public void onCreate(SQLiteDatabase db) {
		String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +TABLE_USER+
				"("+USERNAME+" TEXT PRIMARY KEY,"+PASSWORD+" TEXT"+")";
		db.execSQL(CREATE_QUESTIONS_TABLE);
		
	}
	



	@Override

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//drop older table if existing
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
		
		//Create tables again
		onCreate(db);
	}
	//adds a response to the database
	public void addUser (User user){
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(USERNAME, user.getUsername());
			values.put(PASSWORD, user.getPassword());

			//Inserting Row
			db.insert(TABLE_USER, null, values);
			db.close();
	}

	
	//START HERE!!!!!
	
	//Getting single question
	public User getUser(String username){
		
		try{
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_USER, new String[]{
					USERNAME,PASSWORD},USERNAME+"=?",
					new String[]{username},null,null,null,null);
			if(cursor != null)
				cursor.moveToFirst();
			User response = new User(cursor.getString(0),cursor.getString(1));
			db.close();
			return response;
		}catch(CursorIndexOutOfBoundsException e){
			return null;
		}
		
		
	}
	
	//Getting All Questions
	public List<User> getAllUsers(){
		
		List<User> userList = new ArrayList();
		//Select All Query
		String selectQuery = "SELECT * FROM "+ TABLE_USER;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				User user = new User();
				
				user.setUsername(cursor.getString(0));
				user.setPassword(cursor.getString(1));
				
				//Adding contact to list
				userList.add(user);
			}while(cursor.moveToNext());
		}
		
		
		return userList;
		
	}
	

	
	

	////Getting response Count
	public int getUserCount(){

			String countQuery = "SELECT * FROM "+ TABLE_USER;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			int i = cursor.getCount();
			cursor.close();
			
			//return count
			return i;

		
		
	}
	
	
	//Updating single response
	public int updateUser(User user){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USERNAME, user.getUsername());
		values.put(PASSWORD,user.getPassword());

		
		return db.update(TABLE_USER, values, USERNAME+" = ?", new String[]{user.getUsername()});
		
	}
	
	//Deleting single response
	public void deleteUser(User user){
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER, USERNAME+" = ?", new String[]{user.getUsername()});
		
	}
	//returns the database version
	public int getVersion() {
		return DATABASE_VERSION;
	}
	
	//Identifies what type of database this is
		public String getType(){
			return "UserHandler";
		}
	
}