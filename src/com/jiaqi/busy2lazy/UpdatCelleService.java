package com.jiaqi.busy2lazy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

public class UpdatCelleService extends Service {
	private static final String TAG = "CellUpdateService_busy2lazy";

	BlApplication myApp;

	TelephonyManager mTelephonyManager;
	GsmCellLocation mLocation;

	@Override
	public void onCreate() {
		myApp = (BlApplication) getApplication();

		mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		BlPhoneStateListener listener = new BlPhoneStateListener();
		mTelephonyManager.listen(listener, PhoneStateListener.LISTEN_CELL_LOCATION);

		String operator = mTelephonyManager.getNetworkOperator();
		int mcc = Integer.parseInt(operator.substring(0, 3));
		int mnc = Integer.parseInt(operator.substring(3));

		mLocation = (GsmCellLocation) mTelephonyManager.getCellLocation();
		if (mLocation == null) {
			Log.w(TAG, "No signal");
		} else {
			Integer lac = mLocation.getLac();
			Integer cellId = mLocation.getCid();
			Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	class BlPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCellLocationChanged(CellLocation location) {
			mLocation = (GsmCellLocation) location;
			synchronized (myApp.currentCell) {
				if (location == null) {
					Log.w(TAG, "No location info retrieved");
				} else {
					myApp.currentCell.lac = mLocation.getLac();
					myApp.currentCell.cid = mLocation.getCid();
					myApp.currentCell.mcc = mTelephonyManager.getNetworkOperator().substring(0, 3);
					myApp.currentCell.mnc = mTelephonyManager.getNetworkOperator().substring(3, 5);
					Log.i(TAG, "LAC = " + myApp.currentCell.lac + " CID = " + myApp.currentCell.cid);
				}
			}

		}
	}
}
