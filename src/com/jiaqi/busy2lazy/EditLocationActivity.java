package com.jiaqi.busy2lazy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class EditLocationActivity extends Activity {

	BlApplication myApp;
	EditText locationNameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_location);
		myApp = (BlApplication) getApplication();
		Intent intent = getIntent();
		int position = (intent.getExtras()).getInt("LocationID");

		locationNameEditText = (EditText) findViewById(R.id.location_name_edittext);
		// get location name to current edit text
		locationNameEditText.setText(myApp.locationList.get(position)
				.toString());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_location, menu);
		return true;
	}

}
