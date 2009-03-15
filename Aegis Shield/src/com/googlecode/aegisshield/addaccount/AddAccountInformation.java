/*
 * Nov 23, 2008
 * Copyright (C) 2007 Mihai Campean.
 */
package com.googlecode.aegisshield.addaccount;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;

/**
 * 	Activity for adding a new user account password information.
 * 
 * @author Mihai Campean
 */
public class AddAccountInformation extends Activity {
	/**
	 * 	Constant for not available strings.
	 */
	private static final String N_A = "n/a";

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
			 * @param view
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View view) {
				// add account button was pressed, we try to save the data
				if (R.id.add_account_button == view.getId()) {
					AccountInformationRepository acctRepository = 
							new AccountInformationRepository(getContentResolver());
					AccountInformation acctInformation = new AccountInformation();
					EditText acctNameEdit = (EditText) findViewById(R.id.account_name_edit);
					EditText acctUserEdit = (EditText) findViewById(R.id.account_user_edit);
					EditText acctPassEdit = (EditText) findViewById(R.id.account_password_edit);
					EditText acctDescEdit = (EditText) findViewById(R.id.account_description_edit);
					
					String acctName = acctNameEdit.getText().toString();
					String acctUser = acctUserEdit.getText().toString();
					String acctPass = acctPassEdit.getText().toString();
					String acctDesc = acctDescEdit.getText().toString();
					
					//TODO add some validation code, and return a proper message if fields are not filled.
					if (acctName == null || "".equals(acctName.trim())) {
						acctName = N_A;
					}
					if (acctUser == null || "".equals(acctUser.trim())) {
						acctUser = N_A;
					}
					if (acctPass == null || "".equals(acctPass.trim())) {
						acctPass = N_A;
					}
					if (acctDesc == null || "".equals(acctDesc.trim())) {
						acctDesc = N_A;
					}
					
					acctInformation.setAccountName(acctName);
					acctInformation.setUserName(acctUser);
					acctInformation.setPassword(acctPass);
					acctInformation.setDescription(acctDesc);
					
					acctRepository.save(acctInformation);
					
					// empty the controls...
					acctNameEdit.setText("");
					acctUserEdit.setText("");
					acctPassEdit.setText("");
					acctDescEdit.setText("");
				}
			}
		});
	}
	
}
