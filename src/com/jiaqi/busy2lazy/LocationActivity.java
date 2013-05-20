package com.jiaqi.busy2lazy;

import java.util.ArrayList;

import com.jiaqi.busy2lazy.model.BlLocation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class LocationActivity extends Activity {
	private static final String TAG = "LocationActivity_busy2lazy";

	public BlApplication myApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		Intent intent = new Intent();
		intent.setClass(LocationActivity.this, UpdatCelleService.class);
		startService(intent);

		/*
		 * use Application class to store global shared data
		 */
		myApp = (BlApplication) getApplication();
		myApp.locationList = new ArrayList<BlLocation>();
		myApp.locationList.add(new BlLocation("Home"));
		myApp.locationList.add(new BlLocation("Office"));
		myApp.locationList.add(new BlLocation("Out Door"));

		ListView locationListView = (ListView) findViewById(R.id.location_list);

		ArrayAdapter<BlLocation> locationAdapter = new ArrayAdapter<BlLocation>(this, R.layout.location_item_lo,
				myApp.locationList);
		locationListView.setAdapter(locationAdapter);

		locationListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d(TAG, "Location: " + position + " clicked");
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), EditLocationActivity.class));
				intent.putExtra("LocationID", position);
				startActivity(intent);
			}
		});

		Button toProfileActivityButton = (Button) findViewById(R.id.to_profile_activity_button);
		toProfileActivityButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), ProfileActivity.class));
				startActivity(intent);
			}
		});

		Button toToggleTestButton = (Button) findViewById(R.id.to_toggle_test_button);
		toToggleTestButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), ToggleTestActivity.class));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location, menu);
		return true;
	}

}
