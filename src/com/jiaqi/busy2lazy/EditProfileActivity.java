package com.jiaqi.busy2lazy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditProfileActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = "EditProfileActivity_busy2lazy";

	public BlApplication myApp;
	EditText profileNameEditText;
	Spinner wifi, bt, vib, vol;
	int listPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		myApp = (BlApplication) getApplication();
		Intent intent = getIntent();
		listPosition = (intent.getExtras()).getInt("ProfileID");

		profileNameEditText = (EditText) findViewById(R.id.profile_name_edittext);
		// get profile name to current edit text
		profileNameEditText.setText(myApp.profileList.get(listPosition).toString());

		wifi = (Spinner) findViewById(R.id.wifi_status);
		wifi.setSelection(status2spinnerposition(myApp.profileList.get(listPosition).getWifiStatus()));
		wifi.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				myApp.profileList.get(listPosition).setWifi(spinnerposition2status(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		bt = (Spinner) findViewById(R.id.bluetooth_status);
		bt.setSelection(status2spinnerposition(myApp.profileList.get(listPosition).getBluetoothStatus()));
		bt.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				myApp.profileList.get(listPosition).setBluetooth(spinnerposition2status(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		vib = (Spinner) findViewById(R.id.vibration_status);
		vib.setSelection(status2spinnerposition(myApp.profileList.get(listPosition).getVibrationStatus()));
		vib.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				myApp.profileList.get(listPosition).setVibration(spinnerposition2status(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		vol = (Spinner) findViewById(R.id.volume_status);
		vol.setSelection(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

	private int status2spinnerposition(int status) {
		switch (status) {
		case -1:
			return 0;
		case 0:
			return 1;
		case 1:
			return 2;

		default:
			return 0;
		}
	}

	private int spinnerposition2status(int position) {
		switch (position) {
		case 0:
			return -1;
		case 1:
			return 0;
		case 2:
			return 1;

		default:
			return -1;
		}
	}

}
