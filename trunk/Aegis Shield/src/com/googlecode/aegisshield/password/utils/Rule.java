/*
 * Nov 27, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.password.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

/**
 * Abstract rule for determining a password's strength.
 * 
 * @author Rares Barbantan
 *
 */
public abstract class Rule {

	private double weight = 1;
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(final double weight) {
		this.weight = weight;
	}
	
	/**
	 * Count how many time the regex appears in the password.
	 * @param password the string to be parsed
	 * @param regex the regular expression to count
	 * @return the number of appearances
	 */
	protected int contains(final String password, final  String regex) {
		int result = 0;
		Matcher m = Pattern.compile(regex).matcher(password);
		while(m.find()) {
			result++;
		}
		Log.d("rule", regex + "is present " + result + " times in " + password);
		return result; 
	}
	
	/**
	 * Implementation for the strength evaluation algorithm.
	 * @param password the password to be evaluated
	 * @return password's strength (1..10)
	 */
	public abstract int evaluate (String password);
}
