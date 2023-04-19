package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EatMapRequest {

	@JsonProperty("eat_map")
	private EatMap eatMap;

	private String name;

	private String city;

	private Integer point;

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

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	
}