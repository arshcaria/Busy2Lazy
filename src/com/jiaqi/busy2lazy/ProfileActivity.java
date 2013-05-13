package com.jiaqi.busy2lazy;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ProfileActivity extends Activity {
	private static final String TAG = "TAG_ProfileActivity";
	public BlApplication myApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		/*
		 * use Application class to store global shared data
		 */
		myApp = (BlApplication) getApplication();
		myApp.profileList = new ArrayList<BlProfile>();
		myApp.profileList.add(new BlProfile("Loud"));
		myApp.profileList.add(new BlProfile("Normal"));
		myApp.profileList.add(new BlProfile("Vibrate"));

		ListView profileListView = (ListView) findViewById(R.id.profile_list);

		ArrayAdapter<BlProfile> profileAdapter = new ArrayAdapter<BlProfile>(
				this, R.layout.profile_item_lo, myApp.profileList);
		profileListView.setAdapter(profileAdapter);

		profileListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d(TAG, "Profile: " + position + " clicked");
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(),
						EditProfileActivity.class));
				intent.putExtra("ProfileID", position);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
