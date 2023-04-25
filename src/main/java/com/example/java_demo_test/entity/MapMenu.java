package com.example.java_demo_test.entity;

import java.io.Serializable;

//どいつの店舗のメニューを意味するクラス
@SuppressWarnings("serial")
public class MapMenu implements Serializable {

	//メニューの名前
	private String name;
	//店舗の名前
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
		
	}
	public MapMenu(String name, String shop) {
		super();
		this.name = name;
		this.shop = shop;
	}
	
}
