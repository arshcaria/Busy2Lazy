package com.jiaqi.busy2lazy.helper;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;

/**
 * Helper class that handles all kinds of toggles
 */
public class ToggleHelper {

	private Context mContext;
	private WifiManager mWifiManager;
	private BluetoothAdapter mBtAdapter;
	private AudioManager mAudioManager;

	public ToggleHelper(Context context) {
		mContext = context;
		init();
	}

	public void init() {
		mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
		mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
	}

	public void wifiOn() {
		if (!mWifiManager.isWifiEnabled())
			mWifiManager.setWifiEnabled(true);
	}

	public void wifiOff() {
		if (mWifiManager.isWifiEnabled())
			mWifiManager.setWifiEnabled(false);
	}

	public void btOn() {
		if (mBtAdapter.getState() == BluetoothAdapter.STATE_OFF)
			mBtAdapter.enable();
	}

	public void btOff() {
		if (mBtAdapter.getState() == BluetoothAdapter.STATE_ON)
			mBtAdapter.disable();
	}

	public void vibOn() {
		mAudioManager.setRingerMode(AudioManager.VIBRATE_SETTING_ON);
	}

	public void vibOff() {
		mAudioManager.setRingerMode(AudioManager.VIBRATE_SETTING_OFF);
	}

}
