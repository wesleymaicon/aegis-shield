/*
 * Mar 10, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.accountoverview;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;

/**
 * 	Activity for displaying all account information in a list. It will have the proper menus for adding,
 * viewing/editing and deleting an account information object from the list and the database.
 * 
 * @author Mihai Campean
 */
public class AccountInfoOverview extends ListActivity {
	
	/**
	 * 	The friggin' account info adapter - this is sort of a model object from the MVC pattern, at least
	 * that's what I think.
	 */
	private AccountInfoAdapter acctInfoListAdapter;
	
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
		
		// we provide context menus
		getListView().setOnCreateContextMenuListener(this);
		
		//load the items in the list
		List<AccountInformation> content = new AccountInformationRepository(getContentResolver()).loadAll();
		acctInfoListAdapter = new AccountInfoAdapter(this, content);
		setListAdapter(acctInfoListAdapter);
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean handled = false;
		int position = getListView().getSelectedItemPosition();
		
		switch(item.getItemId()) {
			case R.id.list_add:
				// TODO forward to add activity
				break;
			case R.id.list_delete:
				if (AdapterView.INVALID_POSITION != position) {
					deleteListItem(position);
				}
				break;
			case R.id.list_edit:
				// TODO forward to edit activity
				break;
			default:
				break;
		}
		
		return handled;
	}
	
}