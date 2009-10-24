package com.googlecode.aegisshield.password.utils;

import java.util.Date;
import java.util.Random;

/**
 * Utility class for generating passwords.
 * 
 * @author Christian Stefanescu
 */
public class PasswordGenerator {
	
	final private static char[] lower_chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	final private static char[] upper_chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	final private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private static Random rand = new Random(new Date().getTime());
	
	/**
	 * Generates a password of specified length.
	 * 
	 * @param length of the password
	 * @return String with generated password
	 */
	public static String getPassword(int length) {
		final StringBuffer password = new StringBuffer();
		for (int i=0; i<length; i++) {
			int type = rand.nextInt(4);
			switch(type) {
				case 0: password.append(upper_chars[rand.nextInt(upper_chars.length)]); break;
				case 1: password.append(digits[rand.nextInt(digits.length)]); break;
				default: password.append(lower_chars[rand.nextInt(lower_chars.length)]); break;				
			}
		}
		return password.toString();
	}
	
}
