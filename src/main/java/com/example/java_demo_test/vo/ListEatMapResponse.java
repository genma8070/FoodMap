package com.example.java_demo_test.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListEatMapResponse {
	private String shop;
	private Integer rate;
	private String city;
	private String menu;
	private Integer menurate;
	private Integer price;
	private String massage;
	
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
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
	public Integer getMenurate() {
		return menurate;
	}
	public void setMenurate(Integer menurate) {
		this.menurate = menurate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public ListEatMapResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ListEatMapResponse(String massage) {
		super();
		this.massage = massage;
	}
	
	
}
