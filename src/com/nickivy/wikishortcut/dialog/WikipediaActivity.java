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

/**
 * I came up with this idea after countless times of
 * digging into the URL bar trying to reach a specific
 * Wikipedia article on an old, slow phone. It took me
 * until much later to get around to making it.
 * 
 * <p>The point of the app is to, instead of going to Wikipedia
 * and then dealing with searching or editing the URL,
 * just type the title into a little dialog and press go. 
 * 
 * @author Nicky Ivy nickivyca@gmail.com
 *
 */

public class WikipediaActivity extends Activity{
	EditText URLedit;
	Spinner langselect;
	Spinner wikiselect;
	
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
				if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
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

		wikiselect = (Spinner) findViewById(R.id.wikiselect);
		ArrayAdapter<CharSequence> wikiadapter = ArrayAdapter.createFromResource(this,
				R.array.wiki_array, android.R.layout.simple_spinner_item);
		wikiadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		wikiselect.setAdapter(wikiadapter);
				
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
		
		//also maybe save these prefs?
		//currenly they only persist as long as the app stays in memory
		String address = "https://" + getLangPrefix() + ".m." + getWiki() + ".org/wiki/" + URLedit.getText().toString();
		
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
	
	public String getWiki(){
		wikiselect = (Spinner) findViewById(R.id.wikiselect);
		String wiki = wikiselect.getSelectedItem().toString();
		
		//url code is simply title of wiki except using lowercase w
		//this will require some more work if wikispecies is ever implemented
		wiki = wiki.replace("W", "w");
		
		return wiki;
	}

}
