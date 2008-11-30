/*
 * Nov 27, 2008
 * Copyright (C) 2008 Rares Barbantan.
 */
package com.googlecode.aegisshield.password;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.utils.PasswordStrength;

/**
 * 	Activity for verifying a password's strength.
 * 
 * @author Rares Barbantan
 */
public class VerifyPassword extends Activity {

	@Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.verify_password);
        final EditText textBox = (EditText) findViewById(R.id.left_text_edit);
        final ImageView iw = (ImageView) findViewById(R.id.icon);
        
        //verify the password's strength after each character
        textBox.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_UP) {
					int strength = PasswordStrength.evaluate(textBox.getText().toString());
					
					if(strength >= 8) {
						iw.setImageResource(R.drawable.check);
		                iw.setAdjustViewBounds(true);
					}else {
						iw.setImageResource(R.drawable.cross);
						iw.setAdjustViewBounds(true);
					}
				}
				return false;
			}
        });
        
        //terminate activity and return to previous screen
        final Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	finish();    
            }
        });
    }
}