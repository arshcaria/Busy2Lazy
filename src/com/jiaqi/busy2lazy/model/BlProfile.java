package com.jiaqi.busy2lazy.model;

public class BlProfile {
	@SuppressWarnings("unused")
	private static final String TAG = "BlProfile_busy2lazy";

	String mName;

	/*
	 * for wifi bt vibr and nfc: 1: on 0: off -1: no change
	 */
	int mWifi;
	int mBluetooth;
	int mVibration;
	int mNfc;
	/*
	 * for vol, range from -1 to 7. 0: silent -1: no change
	 */
	int mVolume;

	public String toString() {
		return this.mName;
	}

	/*
	 * Constructor this constructor uses only a name, other values are set to
	 * default
	 */
	public BlProfile(String name) {
		init(name, -1, -1, -1, -1, -1);
	}

	/*
	 * Constructor this constructor uses a full set of values.
	 */

	public BlProfile(String name, int wifi, int bluetooth, int volume, int vibration, int nfc) {
		init(name, wifi, bluetooth, volume, vibration, nfc);
	}

	/*
	 * initialization method. currently only used by constructors
	 */
	private void init(String name, int wifi, int bluetooth, int volume, int vibration, int nfc) {
		this.mName = name;
		this.mWifi = wifi;
		this.mBluetooth = bluetooth;

		if (volume > 7) {
			this.mVolume = 7;
		} else if (volume < -1) {
			this.mVolume = -1;
		} else {
			this.mVolume = volume;
		}

		this.mVibration = vibration;
		this.mNfc = nfc;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public int isWifiON() {
		return mWifi;
	}

	public void setWifi(int wifi) {
		this.mWifi = wifi;
	}

	public int isBluetoothON() {
		return mBluetooth;
	}

	public void setBluetooth(int bluetooth) {
		this.mBluetooth = bluetooth;
	}

	public int getVolume() {
		return mVolume;
	}

	public void setVolume(int volume) {
		if (volume > 7) {
			this.mVolume = 7;
		} else if (volume < -1) {
			this.mVolume = -1;
		} else {
			this.mVolume = volume;
		}
	}

	public int isVibrationON() {
		return mVibration;
	}

	public void setVibration(int vibration) {
		this.mVibration = vibration;
	}

	public int isNfcON() {
		return mNfc;
	}

	public void setNfc(int nfc) {
		this.mNfc = nfc;
	}

}
