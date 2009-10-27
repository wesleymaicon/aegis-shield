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
package com.googlecode.aegisshield.accountoverview;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.addaccount.AddAccountInformation;
import com.googlecode.aegisshield.app.utils.Constants;
import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;
import com.googlecode.aegisshield.editaccount.EditAccountInformation;

/**
 * 	Activity for displaying all account information in a list. It will have the proper menus for adding,
 * viewing/editing and deleting an account information object from the list and the database.
 * 
 * @author Mihai Campean
 */
public class AccountInfoOverview extends ListActivity {
	/**
	 * 	Intent action for the AccountInfoOverview.
	 */
	public static final String ACCT_INFO_OVERVIEW_ACTION = "com.googlecode.aegisshield.action.ACCT_INFO_OVERVIEW_ACTION";
	
	/**
	 * 	Key to identify the account information passed to te edit activity.
	 */
	public static final String ACC_INFO_TO_EDIT_Key = "accountInfoToEdit";
	/**
	 * 	The friggin' account info adapter - this is sort of a model object from the MVC pattern, at least
	 * that's what I think.
	 */
	private AccountInfoAdapter acctInfoListAdapter;
	
	/**
	 * 	Encryption key is the master password. We need to pass it on to the activities that use it.
	 */
	private String encryptionKey = "";
	
	/**
	 * 	Delete an item from the list.
	 * 
	 * @param position
	 */
	private void deleteListItem(int position) {
		if (AdapterView.INVALID_POSITION != position) {
			AccountInformationRepository acctRepository = new AccountInformationRepository(
					getContentResolver());
			acctRepository.delete((AccountInformation) acctInfoListAdapter.getItem(position));
			acctInfoListAdapter.remove(position);
			acctInfoListAdapter.notifyDataSetChanged();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		if (ACCT_INFO_OVERVIEW_ACTION.equals(intent.getAction())) {
			encryptionKey = intent.getExtras().getString(Constants.HASHED_PASSWORD);
		}
		
		// we provide context menus
		getListView().setOnCreateContextMenuListener(this);
		
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("aegis", "AccountInfoOverview.onPause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("aegis", "AccountInfoOverview.onRestart");
	}
	
	/**
	 * 	Since this method is called on all states of running an activity (new, paused and stopped), we populate
	 * the list here.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("aegis", "AccountInfoOverview.onResume");
		//load the items in the list
		List<AccountInformation> content = new AccountInformationRepository(getContentResolver()).loadAll();
		acctInfoListAdapter = new AccountInfoAdapter(this, content);
		setListAdapter(acctInfoListAdapter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("aegis", "AccountInfoOverview.onStart");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.overview_list_menu, menu);
	    return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		int position = getListView().getSelectedItemPosition();
		
		menu.findItem(R.id.list_add).setVisible(true);
		menu.findItem(R.id.list_edit).setVisible(position != AdapterView.INVALID_POSITION);
		menu.findItem(R.id.list_delete).setVisible(position != AdapterView.INVALID_POSITION);
		
		return true;
	}
	/**
	 * 	Reacts on users clicking on menu items.
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean handled = false;
		int position = getListView().getSelectedItemPosition();
		
		switch(item.getItemId()) {
			case R.id.list_add:
				Intent addIntent = new Intent(AddAccountInformation.ADD_ACCT_INFO_ACTION);
				addIntent.putExtra(Constants.HASHED_PASSWORD, encryptionKey);
				startActivity(addIntent);
				break;
			case R.id.list_delete:
				if (AdapterView.INVALID_POSITION != position) {
					deleteListItem(position);
				}
				break;
			case R.id.list_edit:
				if (AdapterView.INVALID_POSITION != position) {
	 				AccountInformation info = (AccountInformation) acctInfoListAdapter.getItem(position);
					Intent editIntent = new Intent(EditAccountInformation.EDIT_ACCT_INFO_ACTION);
					editIntent.putExtra(ACC_INFO_TO_EDIT_Key, info);
					editIntent.putExtra(Constants.HASHED_PASSWORD, encryptionKey);
					startActivity(editIntent);
				}
				break;
			default:
				break;
		}
		
		return handled;
	}
	
}
