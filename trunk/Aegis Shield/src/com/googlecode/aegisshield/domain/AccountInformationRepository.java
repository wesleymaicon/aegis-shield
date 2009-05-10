/*
 * Feb 28, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.domain;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * 	According to the DDD principles, domain objects should be accessed through repository objects. This 
 * repository, has the role of abstracting the access to the AccountInformationProvider, as to separate the
 * activity code from the database access and manipulation code.
 * 
 * TODO - see how this way of designing and working, influences performance of the application.
 * 
 * @author Mihai Campean
 */
public class AccountInformationRepository implements DataRepository<AccountInformation> {
	/**
	 * 	The content resolver is passed to this repository in order to facilitate database access.
	 */
	private ContentResolver resolver;
	
	/**
	 * Public constructor taking the content resolver as argument.
	 * 
	 * @param resolver
	 */
	public AccountInformationRepository(ContentResolver resolver) {
		this.resolver = resolver;
	}
	
	/**
	 * 	Does what the name says.
	 * 
	 * @param acctInfo
	 * @return
	 */
	private ContentValues acctInfoToContentValues(AccountInformation acctInfo) {
		ContentValues values = new ContentValues();
		
		values.put(AccountInformationProvider.KEY_ACCOUNT_NAME, acctInfo.getAccountName());
		values.put(AccountInformationProvider.KEY_PASSWORD, acctInfo.getPassword());  // should be encrypted
		values.put(AccountInformationProvider.KEY_USER_NAME, acctInfo.getUserName());
		values.put(AccountInformationProvider.KEY_DESCRIPTION, acctInfo.getDescription());
		
		return values;
	}
	
	/**
	 * 	Save an AccountInformation object in the application database.
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public Uri save(AccountInformation account) {
		Log.d("aegis", "begin: AccountInformationRepository.save");
		
		Uri returnUri = null;
		returnUri = resolver.insert(AccountInformationProvider.CONTENT_ACCT_INFO_URI, 
				acctInfoToContentValues(account));
		
		Log.d("aegis", "return: " + returnUri);
		Log.d("aegis", "end: AccountInformationRepository.save");
		return returnUri;
	}
	
	/**
	 * 
	 * @see com.googlecode.aegisshield.domain.DataRepository#loadAll()
	 */
	@Override
	public List<AccountInformation> loadAll() {
		Log.d("aegis", "start: AccountInformationRepository.loadAll");
		
		Cursor cursor = resolver.query(AccountInformationProvider.CONTENT_ACCT_INFO_URI, null, null, null, null);
		List<AccountInformation> acctInfoList = new ArrayList<AccountInformation>();
		AccountInformation acctInfo = null;
		if (cursor.moveToFirst()) {
			do {
				acctInfo = new AccountInformation();
				acctInfo.setId(cursor.getInt(AccountInformationProvider.ID_COLUMN));
				acctInfo.setAccountName(cursor.getString(AccountInformationProvider.ACCOUNT_NAME_COLUMN));
				acctInfo.setPassword(cursor.getString(AccountInformationProvider.PASSWORD_COLUMN));
				acctInfo.setUserName(cursor.getString(AccountInformationProvider.USER_NAME_COLUMN));
				acctInfo.setDescription(cursor.getString(AccountInformationProvider.DESCRIPTION_COLUMN));
				acctInfoList.add(acctInfo);
			} while(cursor.moveToNext());
		}
		
		// don't forget to cleanup
		cursor.close();
		cursor = null;
		Log.d("aegis", "return: " + acctInfoList);
		Log.d("aegis", "end: AccountInformationRepository.loadAll");
		
		return acctInfoList;
	}
	
	/**
	 * 
	 * @see com.googlecode.aegisshield.domain.DataRepository#delete(java.lang.Object)
	 */
	@Override
	public int delete(AccountInformation accountInfo) {
		Log.d("aegis", "start: AccountInformationRepository.delete");
		Log.d("aegis", "deleting: " + accountInfo);
		
		Uri deleteRow = ContentUris.withAppendedId(
				AccountInformationProvider.CONTENT_ACCT_INFO_URI, accountInfo.getId());
		
		Log.d("aegis", "return: " + deleteRow);
		Log.d("aegis", "end: AccountInformationRepository.delete");
		
		return resolver.delete(deleteRow, null, null);
	}

	/**
	 * @see com.googlecode.aegisshield.domain.DataRepository#update(java.lang.Object)
	 */
	@Override
	public int update(AccountInformation accountInfo) {
		Log.d("aegis", "start: AccountInformationRepository.update");
		Log.d("aegis", "updating: " + accountInfo);
		
		int rowsAffected = resolver.update(
				ContentUris.withAppendedId(AccountInformationProvider.CONTENT_ACCT_INFO_URI, accountInfo.getId()), 
				acctInfoToContentValues(accountInfo), null, null);
		
		Log.d("aegis", "end: AccountInformationRepository.update");
		return rowsAffected;
	}
	
}
