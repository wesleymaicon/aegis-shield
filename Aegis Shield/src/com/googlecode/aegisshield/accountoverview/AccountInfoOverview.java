/*
 * Mar 10, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.accountoverview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;

/**
 * 	Activity for displaying all account information in a list. It will have the proper menus for adding,
 * viewing/editing and deleting an account information object from the list and the database.
 * 
 * @author Mihai Campean
 */
public class AccountInfoOverview extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// we provide context menus
		getListView().setOnCreateContextMenuListener(this);
		
		//load the items in the list
		AccountInformation [] content = new AccountInformationRepository(getContentResolver()).loadAll();
		ArrayAdapter<AccountInformation> adapter = new ArrayAdapter<AccountInformation>(this, 
				android.R.layout.simple_list_item_1, content);
		setListAdapter(adapter);
	}

}
