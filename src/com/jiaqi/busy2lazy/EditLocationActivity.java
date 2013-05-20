package com.jiaqi.busy2lazy;

import com.jiaqi.busy2lazy.model.CellInfo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class EditLocationActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = "EditLocationActivity_busy2lazy";

	BlApplication myApp;
	EditText locationNameEditText;
	TextView cellsTextView;

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

		cellsTextView = (TextView) findViewById(R.id.cells_textview);
		cellsTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle data = new Bundle();
	
				for (int i = 1; i <= 5; i++) {
					myApp.locationList.get(position).cellList.add(new CellInfo());
				}
				
				for (CellInfo c: myApp.locationList.get(position).cellList) {
					c.lac = (int) (Math.random() * 100);
					c.cid = (int) (Math.random() * 1000);
				}
				
				//data.putSerializable("CELLS", myApp.locationList.get(position).cellList);
				//intent.putExtras(data);
				intent.putExtra("CELLS", myApp.locationList.get(position).cellList);
				intent.setComponent(new ComponentName(getApplicationContext(), EditCellActivity.class));
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_location, menu);
		return true;
	}

}
