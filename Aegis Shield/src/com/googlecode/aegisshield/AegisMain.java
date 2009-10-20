package com.googlecode.aegisshield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.googlecode.aegisshield.accountoverview.AccountInfoOverview;
import com.googlecode.aegisshield.addaccount.AddAccountInformation;
import com.googlecode.aegisshield.app.utils.ApplicationPreferenceManager;
import com.googlecode.aegisshield.security.auth.AuthenticatorService;
import com.googlecode.aegisshield.security.crypto.CryptoService;

public class AegisMain extends Activity {
	/**
	 * 	Intent extra data key for the hashed password.
	 */
	public static final String HASHED_PASSWORD = "hashed.password";

	/**
	 * 	Application preferences manager.
	 */
	private ApplicationPreferenceManager prefsManager;
	
	/**
	 * 	Flag telling if the application is at it's first run.
	 */
	private boolean firstRun = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initActivityState();
        incrementRunCounter();
        onLogin();
    }
    
    /**
     * 	Increments the run counter
     */
    private void incrementRunCounter() {
    	int runCounter = prefsManager.updateRunCounter();
    	
    	if (runCounter == 1) {
    		firstRun = true;
    		View checkPassWidget = findViewById(R.id.check_password_widget);
    		checkPassWidget.setVisibility(View.VISIBLE);
    	}
    }
    
    /**
     * 	Initializes the global class variables - to be used during the activity life cycle.
     */
    private void initActivityState() {
    	prefsManager = new ApplicationPreferenceManager(getApplicationContext());
    }
    
    /**
     * 	Method to be called when the user logs in the application.
     * 
     */
    private void onLogin() {
    	Button login = (Button) findViewById(R.id.login_button);
    	
    	login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AuthenticatorService auth = new AuthenticatorService(getApplicationContext());
				String password = ((EditText) findViewById(R.id.login_password_edittext)).getText().toString();
				String checkPass = ((EditText) findViewById(R.id.login_passwordcheck_edittext)).getText().toString();
				
				//TODO add the validation for password not empty/check strength
				String hashedPass = CryptoService.buildMessageDigest(password);
				
				if (firstRun) {
					if (password.equals(checkPass)) {
						String encryptedToken = CryptoService.encrypt(
								ApplicationPreferenceManager.CLEAR_VERIFICATION_TOKEN, password);
						prefsManager.saveClearSecurityToken(ApplicationPreferenceManager.CLEAR_VERIFICATION_TOKEN);
						prefsManager.saveEncryptedSecurityToken(encryptedToken);
						// we go to the add account screen.
						Intent addAccount = new Intent(AddAccountInformation.ADD_ACCT_INFO_ACTION);
						addAccount.putExtra(HASHED_PASSWORD, hashedPass);
						startActivity(addAccount);
					} else {
						// TODO show dialog
					}
				} else {
					boolean authenticated = auth.authenticate(password);
					if (authenticated) {
						Intent acctOverview = new Intent(AccountInfoOverview.ACCT_INFO_OVERVIEW_ACTION);
						acctOverview.putExtra(HASHED_PASSWORD, hashedPass);
						startActivity(acctOverview);
					} else { 
						//TODO show dialog
					}
				}
				
			}
		});
    }
}