/*
 * May 10, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.editaccount;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.accountoverview.AccountInfoOverview;
import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 *	Edit and save account information.
 * 
 * @author Mihai Campean
 */
public class EditAccountInformation extends Activity {

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_account);
		final AccountInformation info = (AccountInformation) getIntent().getExtras().getSerializable(
				AccountInfoOverview.ACC_INFO_TO_EDIT_Key);
		// populate the edit info activity fields
		final EditText accountName = (EditText) findViewById(R.id.account_name_edit);
		final EditText userName = (EditText) findViewById(R.id.account_user_edit);
		final EditText passwd = (EditText) findViewById(R.id.account_password_edit);
		final EditText description =  (EditText) findViewById(R.id.account_description_edit);
		accountName.setText(info.getAccountName());
		userName.setText(info.getUserName());
		passwd.setText(info.getPassword());
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
				editedInfo.setPassword(passwd.getText().toString());
				editedInfo.setDescription(description.getText().toString());
				
				acctRepository.save(editedInfo);
				startActivity(new Intent(EditAccountInformation.this, AccountInfoOverview.class));
			}
		});
	}

}