package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;

public class UpdateEatMapRateRequest {

	private EatMap eatMap;

	private String shop;

	private String city;

	public EatMap getEatMap() {
		return eatMap;
	}

	public void setEatMap(EatMap eatMap) {
		this.eatMap = eatMap;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


}
