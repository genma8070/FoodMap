package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.vo.EatMapRequest;
import com.example.java_demo_test.vo.EatMapResponse;
import com.example.java_demo_test.vo.ListEatMapResponse;
import com.example.java_demo_test.vo.UpdateEatMapRequest;
import com.example.java_demo_test.vo.UpdateHyoukaEatMapRequest;

public interface EatMapService {
	public EatMapResponse addTenpo(EatMapRequest eatMapRequest);

	public EatMapResponse tenpoShuusei(UpdateEatMapRequest updateEatMapRequest);

	public EatMapResponse hyoukaShuusei(UpdateHyoukaEatMapRequest updateHyoukaEatMapRequest);

	
	public List<ListEatMapResponse> getMapByCity(String City);
	
	public List<ListEatMapResponse> getMapByPoint(EatMapRequest eatMapRequest);
	
	public List<ListEatMapResponse> getMapDetailByPoint(ListEatMapResponse listreq) ;
	
	public EatMapResponse deleteDate(UpdateHyoukaEatMapRequest request);
	
	
}
