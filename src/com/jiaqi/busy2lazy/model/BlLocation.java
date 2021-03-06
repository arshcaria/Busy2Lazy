package com.jiaqi.busy2lazy.model;

import java.util.ArrayList;

import android.util.Log;

public class BlLocation {
	private static final String TAG = "BlLocation_busy2lazy";

	public static final int CELL_ALREADY_IN_LIST = 1;
	public static final int CELL_ADDED_SUCCESS = 0;
	public static final int CELL_REMOVED_SUCCESS = 0;
	public static final int CELL_NOT_IN_LIST = -1;

	// location name, like "home", "office" etc.
	String mName;

	// cells that are registered with this location
	public ArrayList<CellInfo> cellList;

	// profile that is associated with this location
	BlProfile mProfile;

	public BlProfile getProfile() {
		return mProfile;
	}

	public void setProfile(BlProfile profile) {
		this.mProfile = profile;
	}

	public BlLocation(String name) {
		mName = name;
		cellList = new ArrayList<CellInfo>();
		mProfile = null;
	}

	public BlLocation(String name, BlProfile profile) {
		mName = name;
		cellList = new ArrayList<CellInfo>();
		mProfile = profile;
	}

	public int addCell(CellInfo cell) {
		for (CellInfo c : cellList) {
			if (c.lac == cell.lac && c.cid == cell.cid) {
				Log.i(TAG, "This cell is already registered in the cell list");
				return CELL_ALREADY_IN_LIST;
			}
		}
		cellList.add(cell);
		return CELL_ADDED_SUCCESS;
	}

	public int removeCell(CellInfo cell) {
		for (CellInfo c : cellList) {
			if (c.lac == cell.lac && c.cid == cell.cid) {
				cellList.remove(c);
				return CELL_REMOVED_SUCCESS;
			}
		}
		Log.e(TAG, "This cell is not in the cell list!");
		return CELL_NOT_IN_LIST;
	}

	@Override
	public String toString() {
		return this.mName;
	}

}
