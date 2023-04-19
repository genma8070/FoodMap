package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eat_map")
public class EatMap {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "city")
	private String city;

	@Column(name = "point")
	private int point;

	public String getName() {
		return name;
	}

	public EatMap(String name, int point) {
		super();
		this.name = name;
		this.point = point;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public EatMap(String name, String city, int point) {
		super();
		this.name = name;
		this.city = city;
		this.point = point;
	}

	public EatMap() {
		super();
		// TODO Auto-generated constructor stub
	}

}