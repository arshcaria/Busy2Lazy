package com.jiaqi.busy2lazy;

import java.util.ArrayList;

import com.jiaqi.busy2lazy.model.BlLocation;
import com.jiaqi.busy2lazy.model.BlProfile;
import com.jiaqi.busy2lazy.model.CellInfo;

import android.app.Application;

/**
 * currently, this class is only used for storing global variables or objects.
 */
public class BlApplication extends Application {
	@SuppressWarnings("unused")
	private static final String TAG = "BlApplication_busy2lazy";

	public ArrayList<BlLocation> locationList;
	public ArrayList<BlProfile> profileList;

	/**
	 * current cell. this can be updated by
	 * {@link UpdatCellService.BlPhoneStateListener} when phone location
	 * changes
	 */
	public CellInfo currentCell;

}
