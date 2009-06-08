/*
 * Jun 8, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.security.crypto;

/**
 * 
 * @author Mihai Campean
 */
public class CryptoServiceException extends RuntimeException {

	/**
	 *  Generated version serial id.
	 */
	private static final long serialVersionUID = -8337736714023409477L;
	
	/**
	 *	Default exception message. 
	 */
	public static final String DEFAULT_MESSAGE = "Error in encryption/decryption service.";

	/**
	 * 	No-arg provided constructor.
	 */
	public CryptoServiceException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 *  Constructor with a String message argument.
	 * 
	 * @param detailMessage
	 */
	public CryptoServiceException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * 	Constructor with a Throwable cause argument.
	 * 
	 * @param throwable
	 */
	public CryptoServiceException(Throwable throwable) {
		super(DEFAULT_MESSAGE, throwable);
	}

	/**
	 * 	Constructor with a String message and a Throwable cause as arguments.
	 * 
	 * @param detailMessage
	 * @param throwable
	 */
	public CryptoServiceException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
