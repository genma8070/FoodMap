package com.example.java_demo_test.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MapMenu implements Serializable {

	private String name;
	private String shop;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public MapMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MapMenu(String name, String shop) {
		super();
		this.name = name;
		this.shop = shop;
	}
	
}
