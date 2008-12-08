package com.googlecode.aegisshield.password.utils;

import java.util.Date;
import java.util.Random;

/**
 * Utility class for generating passwords.
 * 
 * @author Christian Stefanescu
 */
public class PasswordGenerator {
	
	//TODO add upper case characters, digits, special chars (in separate arrays)
	final private static char[] dict = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	private static Random rand = new Random(new Date().getTime());
	
	public static String getPassword(int length) {
		final StringBuffer password = new StringBuffer();
		for (int i=0; i<length; i++) {
			//TODO take chars from separate arrays, in configurable proportions
			password.append(dict[rand.nextInt(dict.length)]);
		}
		return password.toString();
	}
	
}
