/*
 * Feb 28, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

/**
 * 	According to the DDD principles, domain objects should be accessed through repository objects. This 
 * repository, has the role of abstracting the access to the AccountInformationProvider, as to separate the
 * activity code from the database access and manipulation code.
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
	public Uri save(AccountInformation account) {
		Uri returnUri = null;
		ContentValues values = new ContentValues();
		
		values.put(AccountInformationProvider.KEY_ACCOUNT_NAME, account.getAccountName());
		values.put(AccountInformationProvider.KEY_PASSWORD, account.getPassword());  // should be encrypted
		values.put(AccountInformationProvider.KEY_USER_NAME, account.getUserName());
		values.put(AccountInformationProvider.KEY_DESCRIPTION, account.getDescription());
		
		returnUri = resolver.insert(AccountInformationProvider.CONTENT_URI, values);
		
		return returnUri;
	}
}
