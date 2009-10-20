/*
 * Jun 8, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.security.auth;

import android.content.Context;

import com.googlecode.aegisshield.app.utils.ApplicationPreferenceManager;
import com.googlecode.aegisshield.security.crypto.CryptoService;


/**
 *  Service for authenticating the master password and allowing the user to view sensitive information.
 * I'm using a static verification token, which will have an encrypted form once the master password is 
 * introduced for the first time (the 2 introduced master passwords match). After that authentication
 * will be done based on the clear token text being the same as the decrypted token, using the entered 
 * password.
 * 
 * @author Mihai Campean
 */
public class AuthenticatorService {
	
	/**
	 * 	Android context.
	 */
	private Context context;
	
	/**
	 * 	Constructor taking an Android context as argument.
	 * 
	 * @param context
	 */
	public AuthenticatorService(Context context) {
		this.context = context;
	}
	
	/**
	 * 	Method used for authentication of the user. The idea behind this procedure is pretty simple. We save a security
	 * text in clear and in encrypted form when the application is first run, then each time a user needs to see the
	 * application private data, he or she must use the password entered when the application first ran. If the password is
	 * correct, it means that the clear security token should be equal to the decrypted security token, that was decrypted
	 * using the password.
	 * 
	 * @param password
	 * @return
	 */
	public boolean authenticate(String password) {
		boolean auth = false;
		ApplicationPreferenceManager prefsManager = new ApplicationPreferenceManager(context);
		String clearToken = prefsManager.loadClearSecurityToken();
		String encryptedToken = prefsManager.loadEncryptedSecurityToken();
		String decryptedToken = CryptoService.decrypt(encryptedToken, password);
		
		if (clearToken.equals(decryptedToken)) {
			auth = true;
		}
		
		return auth;
	}
}
