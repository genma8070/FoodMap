package com.example.java_demo_test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.EatMenuService;
import com.example.java_demo_test.vo.EatMenuRequest;
import com.example.java_demo_test.vo.EatMenuResponse;
import com.example.java_demo_test.vo.UpdateEatMenuRequest;

@RestController
public class EatMenuController {

	@Autowired
	private EatMenuService eatMenuService;

	@PostMapping("/add_menu")
	public EatMenuResponse addMenu(@RequestBody EatMenuRequest eatMenuRequest) {
		return eatMenuService.addMenu(eatMenuRequest);
	}

	@PostMapping("/henshuu_menu")
	public EatMenuResponse henshuuMenu(@RequestBody UpdateEatMenuRequest updateReq) {
		return eatMenuService.henshuuMenu(updateReq);
	}


}
