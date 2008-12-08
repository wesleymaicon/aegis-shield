/*
 * Nov 23, 2008
 * Copyright (C) 2007 Mihai Campean.
 */
package com.googlecode.aegisshield.addaccount;

import com.googlecode.aegisshield.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
		
		Button addAccount = (Button) findViewById(R.id.add_account_button);
		addAccount.setOnClickListener(new OnClickListener() {
			/**
			 * 	Add the account information in the the database when the "add account" button
			 * is pressed.
			 * 
			 * @param v
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				//TODO add the stuff in the database.
			}
		});
	}
	
}
