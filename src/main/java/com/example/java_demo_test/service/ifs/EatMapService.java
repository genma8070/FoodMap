package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.vo.EatMapRequest;
import com.example.java_demo_test.vo.EatMapResponse;
import com.example.java_demo_test.vo.ListEatMapResponse;
import com.example.java_demo_test.vo.UpdateEatMapRateRequest;
import com.example.java_demo_test.vo.UpdateEatMapRequest;

//メゾットを宣告するインターフェース
public interface EatMapService {
	
	//新しい店舗を追加する
	public EatMapResponse addShop(EatMapRequest eatMapRequest);

	//店舗の所在地を変更する
	public EatMapResponse editShop(UpdateEatMapRequest updateEatMapRequest);
	
	//店舗を地域で絞る
	public List<ListEatMapResponse> getMapByCity(String city);
	
	//店舗を評価で絞る、そして評価の高い方から並びます
	public List<ListEatMapResponse> getMapByRate(EatMapRequest eatMapRequest);
	
	//店舗とメニューを評価で絞る、そして評価の高い方から並びます
	public List<ListEatMapResponse> getMapDetailByRate(ListEatMapResponse listreq) ;
	
	//店舗とそのメニューを削除
	public EatMapResponse deleteData(UpdateEatMapRateRequest request);
	
	
}
