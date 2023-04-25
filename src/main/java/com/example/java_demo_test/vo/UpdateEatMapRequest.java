package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;
import com.fasterxml.jackson.annotation.JsonProperty;

//店舗更新用リクエスト
public class UpdateEatMapRequest {
	@JsonProperty("update_eat_map")
	private EatMap eatMap;

	public UpdateEatMapRequest() {
		
	}

	public EatMap getEatMap() {
		return eatMap;
	}

	public void setEatMap(EatMap eatMap) {
		this.eatMap = eatMap;
	}

	private String shop;

	private String city;
	private String newcity;

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

	public String getNewcity() {
		return newcity;
	}

	public void setNewcity(String newcity) {
		this.newcity = newcity;
	}

}
