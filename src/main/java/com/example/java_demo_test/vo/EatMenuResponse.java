package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.EatMenu;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EatMenuResponse {

	private EatMenu eatMenu;
	private List<EatMenu> menuList;
	private String message;
	private String name;

	public List<EatMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<EatMenu> menuList) {
		this.menuList = menuList;
	}

	public EatMenuResponse(EatMenu eatMenu, String message) {
		super();
		this.eatMenu = eatMenu;
		this.message = message;
	}

	public EatMenuResponse(List<EatMenu> menuList, String message) {
		super();
		this.menuList = menuList;
		this.message = message;
	}

	public EatMenuResponse(String message) {
		super();
		this.message = message;
	}

	public EatMenuResponse(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}

	public EatMenuResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
