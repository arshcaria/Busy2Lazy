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

public class CellUpdateService extends Service {
	private static final String TAG = "TAG_CellUpdateService";
	GsmCellLocation location;
	
	@Override
	public void onCreate() {
		TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// 返回值MCC + MNC
		String operator = mTelephonyManager.getNetworkOperator();
		int mcc = Integer.parseInt(operator.substring(0, 3));
		int mnc = Integer.parseInt(operator.substring(3));
		// 中国移动和中国联通获取LAC、CID的方式
		location = (GsmCellLocation) mTelephonyManager.getCellLocation();
		if (location == null) {
			Log.d(TAG, "No signal");
		} else {
			Integer lac = location.getLac();
			Integer cellId = location.getCid();
			Log.d(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);
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
			GsmCellLocation loc= (GsmCellLocation) location;
			Integer lac = loc.getLac();
			Integer cellId = loc.getCid();
			Log.d(TAG, "LAC = " + lac + "\t CID = " + cellId);
		}
	}
}
