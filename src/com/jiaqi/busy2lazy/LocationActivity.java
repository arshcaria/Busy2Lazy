package com.jiaqi.busy2lazy;

import java.util.ArrayList;

import com.jiaqi.busy2lazy.model.BlLocation;
import com.jiaqi.busy2lazy.model.BlProfile;
import com.jiaqi.busy2lazy.model.CellInfo;

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
	
	Button startServiceButton;
	Button stopServiceButton;
	ListView locationListView;
	Button toProfileActivityButton;
	Button toToggleTestButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		myApp = (BlApplication) getApplication();
		myApp.currentCell = new CellInfo();

		/*
		 * use Application class to store global shared data
		 */

		myApp.locationList = new ArrayList<BlLocation>();
		
		myApp.locationList.add(new BlLocation("Home"));
		myApp.locationList.get(0).cellList.add(new CellInfo());
		myApp.locationList.get(0).cellList.add(new CellInfo());
		myApp.locationList.get(0).cellList.get(0).cid = 779;
		myApp.locationList.get(0).cellList.get(1).cid = 772;
		
		myApp.locationList.add(new BlLocation("Office"));
		myApp.locationList.get(1).cellList.add(new CellInfo());
		myApp.locationList.get(1).cellList.add(new CellInfo());
		myApp.locationList.get(1).cellList.get(0).cid = 23177;
		myApp.locationList.get(1).cellList.get(1).cid = 23179;
		
		myApp.locationList.add(new BlLocation("Out Door"));
		myApp.locationList.get(2).cellList.add(new CellInfo());
		myApp.locationList.get(2).cellList.add(new CellInfo());
		myApp.locationList.get(2).cellList.get(0).cid = 23751;
		

		myApp.profileList = new ArrayList<BlProfile>();

		locationListView = (ListView) findViewById(R.id.location_list);

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

		toProfileActivityButton = (Button) findViewById(R.id.to_profile_activity_button);
		toProfileActivityButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), ProfileActivity.class));
				startActivity(intent);
			}
		});

		toToggleTestButton = (Button) findViewById(R.id.to_toggle_test_button);
		toToggleTestButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), ToggleTestActivity.class));
				startActivity(intent);
			}
		});
		
		startServiceButton = (Button) findViewById(R.id.start_service_button); 
		startServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), UpdateCellService.class));
				startService(intent);
			}
		});
		stopServiceButton = (Button) findViewById(R.id.stop_service_button); 
		stopServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), UpdateCellService.class));
				stopService(intent);
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
