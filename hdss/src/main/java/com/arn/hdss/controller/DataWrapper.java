package com.arn.hdss.controller;

import java.io.Serializable;
import java.util.List;

public class DataWrapper<T> implements Serializable {
	
	private static final long serialVersionUID=1l;
	private List<T> data;

	public List <T> getData(){
		return data;
	}
	
	public void setData(List<T> data) {
		this.data= data;
	}

	
	
	

}
