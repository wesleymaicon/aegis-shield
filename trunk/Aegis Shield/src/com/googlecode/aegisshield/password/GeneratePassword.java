package com.googlecode.aegisshield.password;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.password.utils.PasswordGenerator;

public class GeneratePassword extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.generate_password);

		final TextView textView = (TextView) findViewById(R.id.pw_textview);
		final Button genPwButton = (Button) findViewById(R.id.generate_button);
		final Button backButton = (Button) findViewById(R.id.back_button);

		genPwButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				textView.setText(PasswordGenerator.getPassword(8));
			}
			
		});
		
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

}
