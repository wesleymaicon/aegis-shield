/*
 * Oct 20, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 	Manages the application shared preferences.
 * 
 * @author Mihai Campean
 */
public class ApplicationPreferenceManager {
	/**
	 * 	Encrypted security token shared preferences key.
	 */
	private static final String AEGIS_ENCRYPTED_SECURITY_TOKEN = "aegis.encrypted.security.token";

	/**
	 * 	Clear security token shared preferences key.
	 */
	private static final String AEGIS_CLEAR_SECURITY_TOKEN = "aegis.clear.security.token";
	
	/**
	 * 	Application run counter shared preferences key.
	 */
	private static final String AEGIS_RUN_COUNT = "aegis.run.count";

	/**
	 * 	Name for the application shared preferences.
	 */
	private static final String APP_SHARED_PREFS = "aegis-preferences";
	
	/**
	 *  Clear text security token string.
	 */
	public static final String CLEAR_VERIFICATION_TOKEN = "security_token";
	
	/**
	 * 	The shared preferences object for the application.
	 */
	private SharedPreferences appSharedPrefs;
	
	/**
	 * 	The shared preferences editor.
	 */
	private Editor prefsEditor;
	
	/**
	 * 	Constructor for the shared preference manager, with an Android context as argument.
	 * 
	 * @param context
	 */
	public ApplicationPreferenceManager(Context context) {
		this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
		this.prefsEditor = appSharedPrefs.edit();
	}
	
	/**
	 *	This method updates the run counter of the application which is a number that represents the number of times
	 * the application was run.
	 * 
	 * @return
	 */
	public int updateRunCounter() {
		int runCount = appSharedPrefs.getInt(AEGIS_RUN_COUNT, 0);
		
		prefsEditor.putInt(AEGIS_RUN_COUNT, ++runCount);
		prefsEditor.commit();
		
		return runCount;
	}
	
	/**
	 * 	Saves the clear security token value as a shared preference for the application.
	 * 
	 * @param token
	 */
	public void saveClearSecurityToken(String token) {
		prefsEditor.putString(AEGIS_CLEAR_SECURITY_TOKEN, token);
		prefsEditor.commit();
	}
	
	/**
	 * 	Loads the clear security token value from the application shared preferences.
	 * 
	 * @return
	 */
	public String loadClearSecurityToken() {
		return appSharedPrefs.getString(AEGIS_CLEAR_SECURITY_TOKEN, CLEAR_VERIFICATION_TOKEN);
	}
	
	/**
	 * 	Saves the encrypted security token value as a shared preference for the application.
	 * 
	 * @param token
	 */
	public void saveEncryptedSecurityToken(String token) {
		prefsEditor.putString(AEGIS_ENCRYPTED_SECURITY_TOKEN, token);
		prefsEditor.commit();
	}
	
	/**
	 * 	Loads the encrypted security token value from the application shared preferences.
	 * @return
	 */
	public String loadEncryptedSecurityToken() {
		return appSharedPrefs.getString(AEGIS_ENCRYPTED_SECURITY_TOKEN, "");
	}
}
