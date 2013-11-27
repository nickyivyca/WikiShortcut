package com.nickivy.wikishortcut.dialog;

import com.nickivy.wikishortcut.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;


public class WikipediaDialog extends DialogFragment{
	
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.dialog_standard, null))
		.setMessage(R.string.dialog_title_wikipedia)
		       .setPositiveButton(R.string.dialog_button_go, new DialogInterface.OnClickListener(){
		    	   public void onClick(DialogInterface dialog, int id){
		    		   //lead to wikipedia
		    	   }
		       })
		       .setNegativeButton(R.string.dialog_button_cancel, new DialogInterface.OnClickListener() {
		    	   public void onClick(DialogInterface dialog, int id){
		    		   //exit
		    	   }
		       });
		return builder.create();
	}

}
