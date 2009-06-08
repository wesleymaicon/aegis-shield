/*
 * Jun 8, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.security.auth;

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
	 *  Clear text security token string.
	 */
	private static final String CLEAR_VERIFICATION_TOKEN = "security_token";
}
