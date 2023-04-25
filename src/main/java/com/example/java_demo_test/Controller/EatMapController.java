package com.example.java_demo_test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.EatMapService;
import com.example.java_demo_test.vo.EatMapRequest;
import com.example.java_demo_test.vo.EatMapResponse;
import com.example.java_demo_test.vo.ListEatMapResponse;
import com.example.java_demo_test.vo.UpdateEatMapRateRequest;
import com.example.java_demo_test.vo.UpdateEatMapRequest;
@CrossOrigin
@RestController
public class EatMapController {

	@Autowired
	private EatMapService eatMapService;
	
	//新しい店舗を追加する
	//POST:eat_map{shop,city}
	@PostMapping("/add_shop")
	public EatMapResponse addShop(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.addShop(eatMapRequest);
	}

	//店舗の所在地を変更する
	//POST:shop,city,newcity
	@PostMapping("/edit_shop")
	public EatMapResponse editShop(@RequestBody UpdateEatMapRequest updateRequest) {
		return eatMapService.editShop(updateRequest);
	}

	//店舗を地域で絞る
	//POST:city
	@PostMapping("/by_city")
	public List<ListEatMapResponse> findByCity(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.getMapByCity(eatMapRequest.getCity());
	}

	//店舗を評価で絞る、そして評価の高い方から並びます
	//POST:rate
	@PostMapping("/by_rate")
	public List<ListEatMapResponse> getMapByRate(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.getMapByRate(eatMapRequest);
	}

	//店舗とメニューを評価で絞る、そして評価の高い方から並びます
	//POST:rate,menurate
	@PostMapping("/by_wrate")
	public List<ListEatMapResponse> getMapDetailByRate(@RequestBody ListEatMapResponse listReq) {
		return eatMapService.getMapDetailByRate(listReq);
	}

	//店舗とそのメニューを削除
	//POST:shop
	@PostMapping("/delete_data")
	public EatMapResponse deleteData(@RequestBody UpdateEatMapRateRequest request) {
		return eatMapService.deleteData(request);
	}

}
