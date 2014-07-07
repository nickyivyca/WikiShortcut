package com.nickivy.wikishortcut.dialog;

import com.nickivy.wikishortcut.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;

public class WikipediaActivity extends Activity{
	EditText URLedit;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_standard);
		
		URLedit = (EditText)findViewById(R.id.urlend);
		
		URLedit.setOnEditorActionListener(new OnEditorActionListener(){
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_GO){
					gotoURL();
					handled = true;
				}
				return handled;
			}
		});
	}
	
	protected void onResume(){
		URLedit.setText("");
		super.onResume();
		//TODO actually make it bring up the keyboard when returning from browser
	}
	
	public void gotoURL(View view){
		gotoURL();
	}
	
	public void gotoURL(){
		URLedit = (EditText)findViewById(R.id.urlend);
		//TODO preferences to switch based on language, different wikis
		String address = "http://en.m.wikipedia.org/wiki/";
		
		address += URLedit.getText().toString();
		
		Intent url = new Intent(Intent.ACTION_VIEW);
		url.setData(Uri.parse(address));
		startActivity(url);
	}

}
