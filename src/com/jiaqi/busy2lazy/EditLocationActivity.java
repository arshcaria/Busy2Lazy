package com.jiaqi.busy2lazy;

import java.util.ArrayList;

import com.jiaqi.busy2lazy.model.BlProfile;
import com.jiaqi.busy2lazy.model.CellInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditLocationActivity extends Activity {

	private static final String TAG = "EditLocationActivity_busy2lazy";

	BlApplication myApp;
	EditText locationNameEditText;
	TextView cellsTextView;
	TextView profileTextView;
	ArrayAdapter<BlProfile> profileAdapter;
	// current location's positionID in locationList
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_location);
		myApp = (BlApplication) getApplication();
		Intent intent = getIntent();
		position = (intent.getExtras()).getInt("LocationID");

		locationNameEditText = (EditText) findViewById(R.id.location_name_edittext);
		// get location name to current edit text
		locationNameEditText.setText(myApp.locationList.get(position).toString());

//		for (int i = 1; i <= 2; i++) {
//			myApp.locationList.get(position).cellList.add(new CellInfo());
//		}

//		for (CellInfo c : myApp.locationList.get(position).cellList) {
//			c.lac = (int) (Math.random() * 100);
//			c.cid = (int) (Math.random() * 1000);
//		}

		cellsTextView = (TextView) findViewById(R.id.cells_textview);
		cellsTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();

				// data.putSerializable("CELLS",
				// myApp.locationList.get(position).cellList);
				// intent.putExtras(data);
				intent.putExtra("CELLS", myApp.locationList.get(position).cellList);
				intent.setComponent(new ComponentName(getApplicationContext(), EditCellActivity.class));
				startActivityForResult(intent, 0);
			}
		});

		profileTextView = (TextView) findViewById(R.id.profile_textview);
		profileAdapter = new ArrayAdapter<BlProfile>(this, R.layout.profile_item_lo, myApp.profileList);
		profileTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(EditLocationActivity.this).setTitle(
						"Pick a profile").setAdapter(profileAdapter, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						myApp.locationList.get(position).setProfile(myApp.profileList.get(which));
						Toast.makeText(EditLocationActivity.this,
								myApp.locationList.get(position).getProfile().toString(), Toast.LENGTH_LONG).show();
					}
				});
				builder.create().show();
			}
		});

	}

	/**
	 * when EditCellActivity finishes, update modified cellList.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == 0) {
			if (data == null || data.getSerializableExtra("CELLS_RETURN") == null) {
				/*
				 * perhaps user pressed BACK key instead of Delete button, just
				 * forget it
				 */
				Log.i(TAG, "perhaps user pressed BACK key instead of Delete button");
				return;
			}
			Log.i(TAG, "Delete button is pressed, update cellList");
			myApp.locationList.get(position).cellList = (ArrayList<CellInfo>) data.getSerializableExtra("CELLS_RETURN");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_location, menu);
		return true;
	}

}
