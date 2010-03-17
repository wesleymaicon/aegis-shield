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
package com.googlecode.aegisshield.utils.ui;

import com.googlecode.aegisshield.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 *	Utility class for displaying alerts and dialogs. Since it is an utility class, it cannot be instantiated nor
 * extended.
 *
 * @author Mihai Campean
 */
public final class Alerts {
	/**
	 * Private constructor so that no one tries to instantiate this.
	 */
	private Alerts() {
		throw new IllegalAccessError("Class cannot be instantiated.");
	}
	
	/**
	 * 	Displays an alert dialog with an OK button and a given message, for a given content context.
	 * 
	 * @param message the message to be displayed in the dialog.
	 * @param context the Android content context.
	 */
	public static void showAlert(String message, Context context) {
		Builder builder = new Builder(context);
		builder.setTitle(R.string.alert_window);
		builder.setPositiveButton(R.string.alert_ok_button, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// we do nothing here yet.
			}
		});
		
		AlertDialog alert = builder.create();
		alert.setMessage(message);
		alert.show();
	}
}
