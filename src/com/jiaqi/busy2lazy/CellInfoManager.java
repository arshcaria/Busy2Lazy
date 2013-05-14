package com.jiaqi.busy2lazy;

import java.util.ArrayList;
import java.util.List;

import com.jiaqi.busy2lazy.model.CellInfo;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

public class CellInfoManager {
	private TelephonyManager manager;
	private PhoneStateListener listener;
	private GsmCellLocation gsm;
	private CdmaCellLocation cdma;
	int lac;
	String current_ci, mcc, mnc;

	public CellInfoManager() {
	}

	public ArrayList<CellInfo> getCellInfo(Context context) {
		manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		listener = new PhoneStateListener();
		manager.listen(listener, 0);
		ArrayList<CellInfo> CellID = new ArrayList<CellInfo>();
		CellInfo currentCell = new CellInfo();

		int type = manager.getNetworkType();

		if (type == TelephonyManager.NETWORK_TYPE_GPRS
				|| type == TelephonyManager.NETWORK_TYPE_EDGE
				|| type == TelephonyManager.NETWORK_TYPE_HSDPA) {
			gsm = ((GsmCellLocation) manager.getCellLocation());
			if (gsm == null)
				return null;
			lac = gsm.getLac();
			mcc = manager.getNetworkOperator().substring(0, 3);
			mnc = manager.getNetworkOperator().substring(3, 5);

			currentCell.cellId = gsm.getCid();
			currentCell.mobileCountryCode = mcc;
			currentCell.mobileNetworkCode = mnc;
			currentCell.locationAreaCode = lac;
			currentCell.radioType = "gsm";
			CellID.add(currentCell);

			List<NeighboringCellInfo> list = manager.getNeighboringCellInfo();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				CellInfo info = new CellInfo();
				info.cellId = list.get(i).getCid();
				info.mobileCountryCode = mcc;
				info.mobileCountryCode = mnc;
				info.locationAreaCode = lac;
				CellID.add(info);
			}
			return CellID;

		} else if (type == TelephonyManager.NETWORK_TYPE_CDMA
				|| type == TelephonyManager.NETWORK_TYPE_1xRTT) {
			cdma = ((CdmaCellLocation) manager.getCellLocation());
			if (cdma == null)
				return null;

			if ("460".equals(manager.getSimOperator().substring(0, 3)))
				return null;
		}
		return null;
	}
}
