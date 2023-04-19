package com.example.java_demo_test.vo;

public class UpdateEatMenuRequest {

	public UpdateEatMenuRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String name;
	private String shop;
	private Integer point;
	private Integer price;
	
	
	private Integer newpoint;
	private Integer newprice;

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

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	

	public Integer getNewpoint() {
		return newpoint;
	}

	public void setNewpoint(Integer newpoint) {
		this.newpoint = newpoint;
	}

	public Integer getNewprice() {
		return newprice;
	}

	public void setNewprice(Integer newprice) {
		this.newprice = newprice;
	}

}
