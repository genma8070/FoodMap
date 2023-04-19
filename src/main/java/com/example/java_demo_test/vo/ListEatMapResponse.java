package com.example.java_demo_test.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListEatMapResponse {
	private String name;
	private Integer point;
	private String city;
	private String menu;
	private Integer mpoint;
	private Integer price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public Integer getMpoint() {
		return mpoint;
	}
	public void setMpoint(Integer mpoint) {
		this.mpoint = mpoint;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public ListEatMapResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ListEatMapResponse(String name, Integer point, String menu, Integer price, Integer mpoint) {
		super();
		this.name = name;
		this.point = point;
		this.menu = menu;
		this.price = price;
		this.mpoint = mpoint;
	}
	
	
}
