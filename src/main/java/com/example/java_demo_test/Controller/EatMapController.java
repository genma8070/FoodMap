package com.example.java_demo_test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.EatMapService;
import com.example.java_demo_test.vo.EatMapRequest;
import com.example.java_demo_test.vo.EatMapResponse;
import com.example.java_demo_test.vo.ListEatMapResponse;
import com.example.java_demo_test.vo.UpdateEatMapRateRequest;
import com.example.java_demo_test.vo.UpdateEatMapRequest;

@RestController
public class EatMapController {

	@Autowired
	private EatMapService eatMapService;

	//POST:eat_map{shop,city}
	@PostMapping("/add_shop")
	public EatMapResponse addShop(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.addShop(eatMapRequest);
	}

	//POST:shop,city,newcity
	@PostMapping("/edit_shop")
	public EatMapResponse editShop(@RequestBody UpdateEatMapRequest updateRequest) {
		return eatMapService.editShop(updateRequest);
	}

	//POST:shop,city
	@PostMapping("/edit_rate")
	public EatMapResponse editRate(@RequestBody UpdateEatMapRateRequest updateRequest) {
		return eatMapService.editRate(updateRequest);
	}

	//POST:city
	@PostMapping("/by_city")
	public List<ListEatMapResponse> findByCity(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.getMapByCity(eatMapRequest.getCity());
	}

	//POST:rate
	@PostMapping("/by_rate")
	public List<ListEatMapResponse> getMapByRate(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.getMapByRate(eatMapRequest);
	}

	//POST:rate,menurate
	@PostMapping("/by_wrate")
	public List<ListEatMapResponse> getMapDetailByRate(@RequestBody ListEatMapResponse listReq) {
		return eatMapService.getMapDetailByRate(listReq);
	}

	//POST:shop
	@PostMapping("/delete_data")
	public EatMapResponse deleteData(@RequestBody UpdateEatMapRateRequest request) {
		return eatMapService.deleteData(request);
	}

}
