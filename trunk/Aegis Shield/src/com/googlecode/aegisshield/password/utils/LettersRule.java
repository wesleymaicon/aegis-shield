/*
 * Dec 02, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.password.utils;

import android.util.Log;

/**
 * Score:
 * No letters - 1 
 * All lower case - 7
 * Upper case and lower case - 10 
 * 
 * @author Rares Barbantan
 *
 */
public class LettersRule extends Rule{

	@Override
	public int evaluate(String password) {
		int score = 1;
		int lowerCase = contains(password, "[a-z]");
		int upperCase = contains(password, "[A-Z]");
		if(lowerCase > 0) {
			if(upperCase > 0) {
				score = 10;
			}else {
				score = 7;
			}
		}
		Log.d("Letters", "score: "+score);
		return score;
	}
}
