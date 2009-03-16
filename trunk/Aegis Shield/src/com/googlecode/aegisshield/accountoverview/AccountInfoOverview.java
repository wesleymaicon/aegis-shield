/*
 * Mar 10, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.accountoverview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;

/**
 * 	Activity for displaying all account information in a list.
 * 
 * @author Mihai Campean
 */
public class AccountInfoOverview extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_overview);
		
		//load the items in the list
		ListView itemsView = (ListView) findViewById(R.id.account_list);
		AccountInformation [] content = new AccountInformationRepository(getContentResolver()).loadAll();
		ArrayAdapter<AccountInformation> adapter = new ArrayAdapter<AccountInformation>(this, 
				android.R.layout.simple_list_item_1, content);
		itemsView.setAdapter(adapter);
		
		//delete an item from the list
		Button delete = (Button) findViewById(R.id.delete_account_button);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO add the deletion code here...
				
			}
		});
	}

}
