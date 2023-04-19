package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;

public class UpdateHyoukaEatMapRequest {

	private EatMap eatMap;

	private String name;

	private String city;

	public EatMap getEatMap() {
		return eatMap;
	}

	public void setEatMap(EatMap eatMap) {
		this.eatMap = eatMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
