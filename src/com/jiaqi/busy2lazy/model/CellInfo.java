package com.jiaqi.busy2lazy.model;

import java.io.Serializable;

public class CellInfo implements Serializable{

	public String mcc;
	public String mnc;

	public int lac;
	public int cid;

	public String radioType;

	public CellInfo() {
	}

	@Override
	public String toString() {
		return (lac + " : " + cid);
	}
	
}
