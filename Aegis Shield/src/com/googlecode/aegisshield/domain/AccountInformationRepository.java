/*
 * Feb 28, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.domain;

import android.content.ContentResolver;
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
	 * 	Save an AccountInformation object in the application database.
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public Uri save(AccountInformation account) {
		Log.d("aegis", "begin: AccountInformationRepository.save");
		
		Uri returnUri = null;
		ContentValues values = new ContentValues();
		
		values.put(AccountInformationProvider.KEY_ACCOUNT_NAME, account.getAccountName());
		values.put(AccountInformationProvider.KEY_PASSWORD, account.getPassword());  // should be encrypted
		values.put(AccountInformationProvider.KEY_USER_NAME, account.getUserName());
		values.put(AccountInformationProvider.KEY_DESCRIPTION, account.getDescription());
		
		returnUri = resolver.insert(AccountInformationProvider.CONTENT_URI, values);
		
		Log.d("aegis", "return: " + returnUri);
		Log.d("aegis", "end: AccountInformationRepository.save");
		return returnUri;
	}
	
	/**
	 * 
	 * @see com.googlecode.aegisshield.domain.DataRepository#loadAll()
	 */
	@Override
	public AccountInformation [] loadAll() {
		Log.d("aegis", "start: AccountInformationRepository.loadAll");
		
		Cursor cursor = resolver.query(AccountInformationProvider.CONTENT_URI, null, null, null, null);
		AccountInformation [] acctInfoList = new AccountInformation[cursor.getCount()];
		AccountInformation acctInfo = null;
		if (cursor.moveToFirst()) {
			do {
				acctInfo = new AccountInformation();
				acctInfo.setId(cursor.getInt(AccountInformationProvider.ID_COLUMN));
				acctInfo.setAccountName(cursor.getString(AccountInformationProvider.ACCOUNT_NAME_COLUMN));
				acctInfo.setPassword(cursor.getString(AccountInformationProvider.PASSWORD_COLUMN));
				acctInfo.setUserName(cursor.getString(AccountInformationProvider.USER_NAME_COLUMN));
				acctInfo.setDescription(cursor.getString(AccountInformationProvider.DESCRIPTION_COLUMN));
				acctInfoList[cursor.getPosition()] = acctInfo;
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
	public int delete(AccountInformation domainObject) {
		Uri deleteRow = Uri.parse(AccountInformationProvider.CONTENT_URI.toString() 
				+ "/#" + domainObject.getId());
		return resolver.delete(deleteRow,null, null);
	}
	
}
