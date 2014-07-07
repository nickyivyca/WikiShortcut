package com.nickivy.wikishortcut.dialog;

import com.nickivy.wikishortcut.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;

public class WikipediaActivity extends Activity{
	EditText URLedit;
	Spinner langselect;
	
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

		langselect = (Spinner) findViewById(R.id.langselect);
		ArrayAdapter<CharSequence> langadapter = ArrayAdapter.createFromResource(this,
				R.array.lang_array, android.R.layout.simple_spinner_item);
		langadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		langselect.setAdapter(langadapter);
				
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
		
		//TODO preferences to switch different wikis
		//also maybe save these prefs?
		String address = "https://" + getLangPrefix() + ".m.wikipedia.org/wiki/" + URLedit.getText().toString();
		
		Intent url = new Intent(Intent.ACTION_VIEW);
		url.setData(Uri.parse(address));
		startActivity(url);
	}
	
	public String getLangPrefix(){
		langselect = (Spinner) findViewById(R.id.langselect);
		String langprefix = langselect.getSelectedItem().toString();
		
		//url code is stored in the string user selects, so we parse it from there
		//each url code is surrounded by parentheses
		langprefix = langprefix.substring(langprefix.indexOf("(") + 1,langprefix.indexOf(")"));
		
		return langprefix;		
	}

}
