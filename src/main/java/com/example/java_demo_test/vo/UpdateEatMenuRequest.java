package com.example.java_demo_test.vo;

//メニュー更新用リクエスト
public class UpdateEatMenuRequest {

	public UpdateEatMenuRequest() {
		
	}

	private String name;
	private String shop;
	private Integer rate;
	private Integer price;
	
	
	private Integer newrate;
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
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getNewrate() {
		return newrate;
	}
	public void setNewrate(Integer newrate) {
		this.newrate = newrate;
	}
	public Integer getNewprice() {
		return newprice;
	}
	public void setNewprice(Integer newprice) {
		this.newprice = newprice;
	}

}
