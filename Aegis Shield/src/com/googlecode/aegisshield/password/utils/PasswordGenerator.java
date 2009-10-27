/*
 * Copyright (C) 2008 - 2009 Christian Stefanescu
 *  
 *	This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
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
