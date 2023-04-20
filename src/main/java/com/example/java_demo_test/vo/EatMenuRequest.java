package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMenu;

public class EatMenuRequest {
	private EatMenu eatMenu;
	private String name;
	private String shop;
	private Integer price;
	private Integer rate;
	public EatMenu getEatMenu() {
		return eatMenu;
	}
	public void setEatMenu(EatMenu eatMenu) {
		this.eatMenu = eatMenu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public EatMenuRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EatMenuRequest(EatMenu eatMenu, String name, String shop, Integer price, Integer rate) {
		super();
		this.eatMenu = eatMenu;
		this.name = name;
		this.shop = shop;
		this.price = price;
		this.rate = rate;
	}

	
	
}
