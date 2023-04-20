package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EatMapRequest {

	@JsonProperty("eat_map")
	private EatMap eatMap;

	private String shop;

	private String city;

	private Integer rate;

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

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public EatMapRequest(EatMap eatMap, String shop, String city, Integer rate) {
		super();
		this.eatMap = eatMap;
		this.shop = shop;
		this.city = city;
		this.rate = rate;
	}

	public EatMapRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}