package com.example.java_demo_test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.EatMenuService;
import com.example.java_demo_test.vo.EatMenuRequest;
import com.example.java_demo_test.vo.EatMenuResponse;
import com.example.java_demo_test.vo.UpdateEatMenuRequest;
@CrossOrigin
@RestController
public class EatMenuController {

	@Autowired
	private EatMenuService eatMenuService;

	//新しいメニューを追加する
	//POST:name,shop,rate,price
	@PostMapping("/add_menu")
	public EatMenuResponse addMenu(@RequestBody EatMenuRequest eatMenuRequest) {
		return eatMenuService.addMenu(eatMenuRequest);
	}

	//メニューの評価と値段を編輯する、さらに店舗の評価に反映します
	//POST:name,shop,newrate,newprice
	@PostMapping("/edit_menu")
	public EatMenuResponse editMenu(@RequestBody UpdateEatMenuRequest updateReq) {
		return eatMenuService.editMenu(updateReq);
	}


}
