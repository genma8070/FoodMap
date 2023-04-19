package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateEatMapRequest {
	@JsonProperty("update_eat_map")
	private EatMap eatMap;

	public UpdateEatMapRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EatMap getEatMap() {
		return eatMap;
	}

	public void setEatMap(EatMap eatMap) {
		this.eatMap = eatMap;
	}

	private String name;

	private String city;
	private String newcity;

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

	public String getNewcity() {
		return newcity;
	}

	public void setNewcity(String newcity) {
		this.newcity = newcity;
	}

}
