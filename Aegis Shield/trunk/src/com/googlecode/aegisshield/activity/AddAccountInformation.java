/*
 * Nov 23, 2008
 * Copyright (C) 2007 Mihai Campean.
 */
package com.googlecode.aegisshield.activity;

import com.googlecode.aegisshield.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * 	Activity for adding a new user account password information.
 * 
 * @author Mihai Campean
 */
public class AddAccountInformation extends Activity {

	/**
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_account);
	}
	
}
