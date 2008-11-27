/*
 * Nov 27, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.utils;

/**
 * If the length of the password is less than 4 chars, password is weak (say a 4) else,
 * unless it has over 8 chars (excellent), it is considered good (say a 7).
 * 
 * @author Rares Barbantan
 *
 */
public class PasswordLengthRule extends Rule {

	public PasswordLengthRule()	{
		setWeight(1);
	}
	
	@Override
	public int evaluate(String password) {
		int result = 1;
		if(password != null) {
			if(password.length() <= 4) {
				result = 4;
			}else if (password.length() <= 7) {
				result = 8;
			}else {
				result = 10;
			}
		}
		return result;
	}

}
