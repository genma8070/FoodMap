package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.EatMenu;

public class EatMenuRequest {
	private EatMenu eatMenu;

	public EatMenu getEatMenu() {
		return eatMenu;
	}

	public void setEatMenu(EatMenu eatMenu) {
		this.eatMenu = eatMenu;
	}

	public EatMenuRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String shop;

	private String name;
	private Integer price;
	private Integer point;

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}
