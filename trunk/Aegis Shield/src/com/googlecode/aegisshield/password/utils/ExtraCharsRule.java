/*
 * Dec 02, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.password.utils;

import android.util.Log;

/**
 * Score:
 * 	No extra chars - 1
 * 	One extra char - 6
 * 	More than 1 extra char - 10
 *  
 * @author Rares Barbantan
 *
 */
public class ExtraCharsRule extends Rule {

	public ExtraCharsRule()	{
		// we can override the default(1) weight of the rule
		setWeight(0.5);
	}
	
	@Override
	public int evaluate(String password) {
		int score  = 1;
		int extraChars = contains(password, "[!@#$%^&*?_~]");
		if(extraChars == 1) {
			score = 6;
		}else if (extraChars > 1) {
			score = 10;
		}
		Log.d("extra chars", "score: "+score);
		return score;
	}

}
