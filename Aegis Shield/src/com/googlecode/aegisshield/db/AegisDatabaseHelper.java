/*
 *	Code provided under GPL v3 license. 
 */
package com.googlecode.aegisshield.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * 	Custom db helper for the AegisShield application.
 * 
 * @author Mihai Campean
 */
public class AegisDatabaseHelper extends SQLiteOpenHelper {
	/**
	 * Database version - for now we hardcode it.
	 */
	public static final int DB_VERSION = 1;
	
	/**
	 * 	Database name constant value.
	 */
	public static final String DB_NAME = "aegis";
	
	/**
	 * The name of the table that holds the account information.
	 */
	public static final String DB_TABLE_ACCT_INFO = "account_info";
	
	/**
	 * Sql query to create the database account info table.
	 */
	private static final String DB_CREATE_ACCT_INFO = "create table "
			+ DB_TABLE_ACCT_INFO + "(id integer primary key autoincrement, "
			+ "user_name text not null, " 
			+ "password text not null," 
			+ "description text," 
			+ "account_name text not null)";
	
	//TODO add the other table - repeating schedulers.
	
	/**
	 * 	Use superconstructor for this database helper. Uses parameters usually required for this kind of helpers.
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public AegisDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	/**
	 * 	Run the create statements for all the database tables the application needs.
	 * 
	 * TODO add the repeating scheduler table
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE_ACCT_INFO);
	}

	/**
	 * 	Drop and recreate the database tables.
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropQuery = "drop table if exists ";
		db.execSQL(dropQuery + DB_TABLE_ACCT_INFO);
		onCreate(db);
	}
}
