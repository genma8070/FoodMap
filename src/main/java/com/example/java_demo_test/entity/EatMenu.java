package com.example.java_demo_test.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


//メニューを代表するクラス
@Entity
@Table(name = "eat_menu")
@IdClass(value = MapMenu.class)
public class EatMenu {
	
	//メニューの名前
	@Id
	@Column(name = "name")
	private String name;
	
	//メニューが属する店舗の名前
	@Id
	@Column(name = "shop")
	private String shop;
	
	//メニューの評価
	@Column(name = "rate")
	private Integer rate;
	
	//メニューの値段
	@Column(name = "price")
	private Integer price;
	

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

	public EatMenu(String name, String shop, Integer rate, Integer price) {
		super();
		this.name = name;
		this.shop = shop;
		this.rate = rate;
		this.price = price;
	}

	public EatMenu() {
		
	}

	

}
