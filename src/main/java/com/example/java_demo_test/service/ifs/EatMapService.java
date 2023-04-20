package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.vo.EatMapRequest;
import com.example.java_demo_test.vo.EatMapResponse;
import com.example.java_demo_test.vo.ListEatMapResponse;
import com.example.java_demo_test.vo.UpdateEatMapRequest;
import com.example.java_demo_test.vo.UpdateEatMapRateRequest;

public interface EatMapService {
	public EatMapResponse addShop(EatMapRequest eatMapRequest);

	public EatMapResponse editShop(UpdateEatMapRequest updateEatMapRequest);

	public EatMapResponse editRate(UpdateEatMapRateRequest updateEatMapRateRequest);

	
	public List<ListEatMapResponse> getMapByCity(String city);
	
	public List<ListEatMapResponse> getMapByRate(EatMapRequest eatMapRequest);
	
	public List<ListEatMapResponse> getMapDetailByRate(ListEatMapResponse listreq) ;
	
	public EatMapResponse deleteData(UpdateEatMapRateRequest request);
	
	
}
