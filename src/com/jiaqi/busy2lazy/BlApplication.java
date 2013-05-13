package com.jiaqi.busy2lazy;

import java.util.ArrayList;

import android.app.Application;

/*
 * currently, this class is only used for storing global variables or objects.
 */
public class BlApplication extends Application {

	public ArrayList<BlLocation> locationList;
	public ArrayList<BlProfile> profileList;

}
