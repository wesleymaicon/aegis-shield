/*
 * Nov 27, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.password.utils;

/**
 * Abstract rule for determining a password's strength.
 * 
 * @author Rares Barbantan
 *
 */
public abstract class Rule {

	private double weight;
	
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
	 * Implementation for the strength evaluation algorithm.
	 * @param password the password to be evaluated
	 * @return password's strength (1..10)
	 */
	public abstract int evaluate (String password);
}
