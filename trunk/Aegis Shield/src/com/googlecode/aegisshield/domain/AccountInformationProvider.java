/*
 * Feb 14, 2009
 * 
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.domain;

import com.googlecode.aegisshield.db.AegisDatabaseHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 	Content provider for the AccountInformation data type.
 * 
 * @author Mihai Campean
 */
public class AccountInformationProvider extends ContentProvider {
	/**
	 * 	The Account Information URI provider.
	 */
	public static final Uri CONTENT_URI = Uri.parse("content://com.googlecode.aegisshield/accountinfo");
	
	/**
	 * Don't know how to use this yet...
	 */
	private static final int ALLROWS = 1;
	
	private static final int SINGLE_ROW = 2;
	
	private static final UriMatcher uriMatcher;
	
	//column names
	public static final String KEY_ID = "id";
	
	public static final String KEY_USER_NAME = "user_name";
	
	public static final String KEY_PASSWORD = "password";
	
	public static final String KEY_DESCRIPTION = "description";
	
	public static final String KEY_ACCOUNT_NAME = "account_name";
	
	//column indexes
	public static final int ID_COLUMN = 1;
	
	public static final int USER_NAME_COLUMN = 2;
	
	public static final int PASSWORD_COLUMN = 3;
	
	public static final int DESCRIPTION_COLUMN = 4;
	
	public static final int ACCOUNT_NAME_COLUMN = 5;
	
	/**
	 * 	The SQLiteDatabase instance used for database access in the Aegis Shield application.
	 */
	private SQLiteDatabase aegisDb;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.googlecode.aegisshield", "accountinfo", ALLROWS);
		uriMatcher.addURI("com.googlecode.aegisshield", "accountinfo/#", SINGLE_ROW);
	}
	
	/**
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 	Return the content mimetype for the account info data provider.
	 * 
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case ALLROWS: 
				return "vnd.aegisshield.cursor.dir/accountinfo";
			case SINGLE_ROW:
				return "vnd.aegisshield.cursor.item/accountinfo";
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	/**
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		Context context = getContext();
		AegisDatabaseHelper aegisHelper = new AegisDatabaseHelper(context, AegisDatabaseHelper.DB_NAME,
				null, AegisDatabaseHelper.DB_VERSION);
		aegisDb = aegisHelper.getWritableDatabase();
		
		return (aegisDb == null) ? false : true;
	}

	/**
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
