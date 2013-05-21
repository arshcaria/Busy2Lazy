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
	Spinner wifi, bt, vib, nfc, vol;
	ToggleHelper th;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle_test);
		
		th = new ToggleHelper(this);
		
		wifi = (Spinner) findViewById(R.id.wifi_status2);
		wifi.setSelection(2);
		wifi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ToggleTestActivity.this, ""+position, Toast.LENGTH_SHORT).show();
				switch (position) {
				case 0:
					th.wifiOn();
					break;
				case 1:
					th.wifiOff();
					break;
				case 2:
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
		bt.setSelection(2);
		bt.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ToggleTestActivity.this, ""+position, Toast.LENGTH_SHORT).show();
				switch (position) {
				case 0:
					th.btOn();
					break;
				case 1:
					th.btOff();
					break;
				case 2:
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
		vib.setSelection(2);
		vib.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ToggleTestActivity.this, ""+position, Toast.LENGTH_SHORT).show();
				switch (position) {
				case 0:
					th.vibOn();
					break;
				case 1:
					th.vibOff();
					break;
				case 2:
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
