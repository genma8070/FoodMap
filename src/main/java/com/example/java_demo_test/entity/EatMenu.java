package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "eat_menu")
@IdClass(value = MapMenu.class)
public class EatMenu {
	
	@Id
	@Column(name = "name")
	private String name;
	
	@Id
	@Column(name = "shop")
	private String shop;
	
	@Column(name = "point")
	private Integer point;
	
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


	public EatMenu(String name, String shop, Integer point, Integer price) {
		super();
		this.name = name;
		this.shop = shop;
		this.point = point;
		this.price = price;
	}


	public EatMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
