/*
 * Dec 02, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.password.utils;

import android.util.Log;

/**
 * Score:
 * 	No digit - 1
 * 	Less than 3 digits - 7
 * 	More than 3 digits - 10
 * 
 * @author Rares Barbantan
 *
 */
public class DigitsRule extends Rule {

	@Override
	public int evaluate(String password) {
		int score = 10;
		int digits  = contains(password, "\\d");
		if(digits == 0) {
			score = 1;
		}else if (digits < 3) {
			score = 7;
		}
		Log.d("digits", "score: " +score);
		return score;
	}
}
