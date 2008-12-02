/*
 * Nov 27, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.password.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 	Utility for computing a password's strength based on weighted algorithms.
 * 
 * @author Rares Barbantan
 */
public class PasswordStrength {
	
	private static List<Rule> rules = new ArrayList<Rule>();
	
	static {
		rules.add(new PasswordLengthRule());
	}
	
	/**
	 * Evaluates the strength of a given password going by a list of weighted rules.
	 * @param password the password to be checked
	 * @return a grade for the strength (should be between 1..10)
	 */
	public static int evaluate(String password) {
		int result = 0;
		for(Rule rule: rules) {
			result += rule.getWeight() * rule.evaluate(password);
		}
		return result/rules.size();
	}
}
