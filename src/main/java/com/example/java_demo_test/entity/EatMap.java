package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//店舗を代表するクラス
@Entity
@Table(name = "eat_map")
public class EatMap {

	//店舗の名前
	@Id
	@Column(name = "shop")
	private String shop;

	//店舗の所在地
	@Column(name = "city")
	private String city;

	//店舗の評価
	@Column(name = "rate")
	private int rate;

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public EatMap(String shop, String city, int rate) {
		super();
		this.shop = shop;
		this.city = city;
		this.rate = rate;
	}

	public EatMap() {
		
	}


}