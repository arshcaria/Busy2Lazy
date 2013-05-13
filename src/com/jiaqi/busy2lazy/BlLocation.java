package com.jiaqi.busy2lazy;

public class BlLocation {
	// location name, like "home", "office" etc.
	String mName;

	// associated profile with current location
	BlProfile mProfile;

	public BlLocation(String name) {
		mName = name;
		mProfile = null;
	}

	public BlLocation(String name, BlProfile profile) {
		mName = name;
		mProfile = profile;
	}

	@Override
	public String toString() {
		return this.mName;
	}

}
