/*
 * Copyright (C) 2008 - 2009 Mihai Campean
 *  
 *	This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.googlecode.aegisshield.editaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.accountoverview.AccountInfoOverview;
import com.googlecode.aegisshield.app.utils.Constants;
import com.googlecode.aegisshield.app.utils.CustomGradient;
import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;
import com.googlecode.aegisshield.password.utils.PasswordGenerator;
import com.googlecode.aegisshield.password.utils.PasswordStrength;
import com.googlecode.aegisshield.security.crypto.CryptoService;

/**
 *	Edit and save account information.
 * 
 * @author Mihai Campean
 */
public class EditAccountInformation extends Activity {
	/**
	 * 	The Intent action for EditAccountInformation activity.
	 */
	public static final String EDIT_ACCT_INFO_ACTION = "com.googlecode.aegisshield.action.EDIT_ACCT_INFO_ACTION";
	
	/**
	 * 	This is the encryption key used to encrypt/decrypt passwords (master password).
	 */
	private String encryptionKey = "";
	
	/**
	 * non-linear gradient.
	 */
	private CustomGradient gradient = new CustomGradient();
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_account);
		Intent intent = getIntent();
		
		if (EDIT_ACCT_INFO_ACTION.equals(intent.getAction())) {
			encryptionKey = intent.getExtras().getString(Constants.HASHED_PASSWORD);
		}
		
		final AccountInformation info = (AccountInformation) getIntent().getExtras().getSerializable(
				AccountInfoOverview.ACC_INFO_TO_EDIT_Key);
		// populate the edit info activity fields
		final EditText accountName = (EditText) findViewById(R.id.account_name_edit);
		final EditText userName = (EditText) findViewById(R.id.account_user_edit);
		final EditText passwd = (EditText) findViewById(R.id.account_password_edit);
		final EditText description =  (EditText) findViewById(R.id.account_description_edit);
		final Button generatePassword = (Button) findViewById(R.id.generate_button_edit);
		final TextView passStrength = (TextView) findViewById(R.id.password_strength);
		
		accountName.setText(info.getAccountName());
		userName.setText(info.getUserName());
		passwd.setText(CryptoService.decrypt(info.getPassword(), encryptionKey));
		verifyPwd(passwd.getText().toString(),passStrength);
		description.setText(info.getDescription());
		
		Button saveEdits = (Button) findViewById(R.id.save_account_button);
		saveEdits.setOnClickListener(new OnClickListener() {
			/**
			 * Save the edits made to the account information.
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				AccountInformationRepository acctRepository = 
						new AccountInformationRepository(getContentResolver());
				AccountInformation editedInfo = new AccountInformation();
				editedInfo.setId(info.getId());
				editedInfo.setAccountName(accountName.getText().toString());
				editedInfo.setUserName(userName.getText().toString());
				editedInfo.setPassword(CryptoService.encrypt(passwd.getText().toString(), encryptionKey));
				editedInfo.setDescription(description.getText().toString());
				
				acctRepository.update(editedInfo);
				Intent intent = new Intent(AccountInfoOverview.ACCT_INFO_OVERVIEW_ACTION);
				intent.putExtra(Constants.HASHED_PASSWORD, encryptionKey);
				startActivity(intent);
			}
		});
		
		//register password strength check
		passwd.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_UP) {
					verifyPwd(passwd.getText().toString(),passStrength);
				}
				return false;
			}

			
        });
		
		generatePassword.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String genPwd = PasswordGenerator.getPassword(Constants.GENERATED_PASSWORD_LENGTH); 
				passwd.setText(genPwd);
				verifyPwd(genPwd, passStrength);
			} 
			
		});
	}
	
	/*
	 * Changes the color of the password according to its strength
	 */
	private void verifyPwd(String passwd, final TextView pwdStrength) {
		int strength = PasswordStrength.evaluate(passwd);
		CharSequence[] strengthLabels = getResources().getTextArray(R.array.password_strength);
		if(strength >= 8) {
			pwdStrength.setText(strengthLabels[2]);
		}else if (strength >= 5){
			pwdStrength.setText(strengthLabels[1]);
		}else {
			pwdStrength.setText(strengthLabels[0]);
		}
		
		gradient.moveCenter(1 - (float) strength/10);
		pwdStrength.setBackgroundDrawable(gradient);
	}
}
