package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EatMapResponse {
	
	@JsonProperty("eat_map")
	private EatMap eatMap;
	
	private String name;
	
	

	public EatMapResponse(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String message;

	public EatMap getEatMap() {
		return eatMap;
	}

	public void setEatMap(EatMap eatMap) {
		this.eatMap = eatMap;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EatMapResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EatMapResponse(EatMap eatMap, String message) {
		super();
		this.eatMap = eatMap;
		this.message = message;
	}

	public EatMapResponse(String message) {
		super();
		this.message = message;
	}

	public EatMapResponse(EatMap eatMap) {
		super();
		this.eatMap = eatMap;
	}
	
	

}
