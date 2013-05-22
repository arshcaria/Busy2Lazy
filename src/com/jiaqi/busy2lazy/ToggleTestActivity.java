package com.jiaqi.busy2lazy;

import com.jiaqi.busy2lazy.helper.ToggleHelper;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

public class ToggleTestActivity extends Activity {
	private static final String TAG = "ToggleTestActivity_busy2lazy";
	Spinner wifi, bt, vib, vol;
	ToggleHelper th;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle_test);

		th = new ToggleHelper(this);

		wifi = (Spinner) findViewById(R.id.wifi_status2);
		wifi.setSelection(0);
		wifi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:

					break;
				case 1:
					th.wifiOff();
					break;
				case 2:
					th.wifiOn();
					break;
				default:
					break;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		bt = (Spinner) findViewById(R.id.bluetooth_status2);
		bt.setSelection(0);
		bt.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					break;
				case 1:
					th.btOff();
					break;
				case 2:
					th.btOn();
					break;
				default:
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		vib = (Spinner) findViewById(R.id.vibration_status2);
		vib.setSelection(0);
		vib.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					break;
				case 1:
					th.vibOff();
					break;
				case 2:
					th.vibOn();
					break;
				default:
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		vol = (Spinner) findViewById(R.id.volume_status2);
		vol.setSelection(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toggle_test, menu);
		return true;
	}

}
