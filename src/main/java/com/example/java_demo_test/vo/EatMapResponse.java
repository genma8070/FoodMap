package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMap;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EatMapResponse {
	
	
	public EatMapResponse(EatMap eatMap, String message) {
		super();
		this.eatMap = eatMap;
		this.message = message;
	}

	private EatMap eatMap;
	
	private String shop;
	
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EatMapResponse(EatMap eatMap, String shop, String message) {
		super();
		this.eatMap = eatMap;
		this.shop = shop;
		this.message = message;
	}

	public EatMapResponse(String shop, String message) {
		super();
		this.shop = shop;
		this.message = message;
	}

	public EatMapResponse(String message) {
		super();
		this.message = message;
	}

	public EatMapResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
