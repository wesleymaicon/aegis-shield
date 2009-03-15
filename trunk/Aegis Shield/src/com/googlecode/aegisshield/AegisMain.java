package com.googlecode.aegisshield;

import com.googlecode.aegisshield.accountoverview.AccountInfoOverview;
import com.googlecode.aegisshield.addaccount.AddAccountInformation;
import com.googlecode.aegisshield.password.GeneratePassword;
import com.googlecode.aegisshield.password.VerifyPassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AegisMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button goToStrength = (Button) findViewById(R.id.strength_button);
        goToStrength.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	final Intent myIntent = new Intent(AegisMain.this, VerifyPassword.class);
            	startActivity(myIntent);    
            }
        });
        
        final Button goToGeneratePassword = (Button) findViewById(R.id.genpw_button);
        goToGeneratePassword.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	final Intent myIntent = new Intent(AegisMain.this, GeneratePassword.class);
            	startActivity(myIntent);    
            }
        });
        
        final Button goToAddAccount = (Button) findViewById(R.id.add_account_button);
        goToAddAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent myIntent = new Intent(AegisMain.this, AddAccountInformation.class);
				startActivity(myIntent);
			}
        });
        
        final Button goToAcctOverview = (Button) findViewById(R.id.account_overview_button);
        goToAcctOverview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent myIntent = new Intent(AegisMain.this, AccountInfoOverview.class);
				startActivity(myIntent);
			}
        	
        });
    }
}