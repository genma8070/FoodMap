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
import com.example.java_demo_test.vo.UpdateEatMapRequest;
import com.example.java_demo_test.vo.UpdateHyoukaEatMapRequest;

@RestController
public class EatMapController {

	@Autowired
	private EatMapService eatMapService;

	//eat_map{name,city}
	@PostMapping("/add_tenpo")
	public EatMapResponse addTenpo(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.addTenpo(eatMapRequest);
	}

	//name,city,newcity
	@PostMapping("/shuusei_tenpo")
	public EatMapResponse tenpoShuusei(@RequestBody UpdateEatMapRequest updateEatMapRequest) {
		return eatMapService.tenpoShuusei(updateEatMapRequest);
	}

	//name,city
	@PostMapping("/shuusei_hyouka")
	public EatMapResponse hyoukaShuusei(@RequestBody UpdateHyoukaEatMapRequest updateHyoukaEatMapRequest) {
		return eatMapService.hyoukaShuusei(updateHyoukaEatMapRequest);
	}

	//city
	@PostMapping("/by_city")
	public List<ListEatMapResponse> findByCity(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.getMapByCity(eatMapRequest.getCity());
	}

	//point
	@PostMapping("/by_point")
	public List<ListEatMapResponse> getMapByPoint(@RequestBody EatMapRequest eatMapRequest) {
		return eatMapService.getMapByPoint(eatMapRequest);
	}

	//point,mpoint
	@PostMapping("/by_ppoint")
	public List<ListEatMapResponse> getMapDetailByPoint(@RequestBody ListEatMapResponse listReq) {
		return eatMapService.getMapDetailByPoint(listReq);
	}

	//name
	@PostMapping("/delete_date")
	public EatMapResponse deleteDate(@RequestBody UpdateHyoukaEatMapRequest request) {
		return eatMapService.deleteDate(request);
	}

}
