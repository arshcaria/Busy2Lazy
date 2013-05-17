package com.jiaqi.busy2lazy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;

public class EditProfileActivity extends Activity {
	private static final String TAG = "TAG_EditProfileActivity_busy2lazy";

	public BlApplication myApp;
	EditText profileNameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		myApp = (BlApplication) getApplication();
		Intent intent = getIntent();
		int position = (intent.getExtras()).getInt("ProfileID");

		profileNameEditText = (EditText) findViewById(R.id.profile_name_edittext);
		// get profile name to current edit text
		profileNameEditText.setText(myApp.profileList.get(position).toString());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

}
