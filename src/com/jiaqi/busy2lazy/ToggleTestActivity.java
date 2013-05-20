package com.jiaqi.busy2lazy;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
	
	private WifiManager mWifiManager;
	private WifiInfo mWifiInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle_test);

		mWifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		wifi = (Spinner) findViewById(R.id.wifi_status2);
		wifi.setSelection(2);
		bt = (Spinner) findViewById(R.id.bluetooth_status2);
		bt.setSelection(2);
		vib = (Spinner) findViewById(R.id.vibration_status2);
		vib.setSelection(2);
		nfc = (Spinner) findViewById(R.id.nfc_status2);
		nfc.setSelection(2);
		vol = (Spinner) findViewById(R.id.volume_status2);
		vol.setSelection(0);
		wifi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ToggleTestActivity.this, ""+position, Toast.LENGTH_SHORT).show();
				switch (position) {
				case 0:
					if (!mWifiManager.isWifiEnabled()){  
			            mWifiManager.setWifiEnabled(true);  
			        }
					break;
				case 1:
					if (mWifiManager.isWifiEnabled()){  
			            mWifiManager.setWifiEnabled(false);  
			        }
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toggle_test, menu);
		return true;
	}

}
