package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.EatMap;
import com.example.java_demo_test.entity.EatMenu;
import com.example.java_demo_test.repository.EatMapDao;
import com.example.java_demo_test.repository.EatMenuDao;
import com.example.java_demo_test.service.ifs.EatMapService;
import com.example.java_demo_test.vo.EatMapRequest;
import com.example.java_demo_test.vo.EatMapResponse;
import com.example.java_demo_test.vo.ListEatMapResponse;
import com.example.java_demo_test.vo.UpdateEatMapRateRequest;
import com.example.java_demo_test.vo.UpdateEatMapRequest;

//宣告したメゾットを定義するクラス
@Service
public class EatMapServiceImpl implements EatMapService {
	@Autowired
	private EatMapDao eatMapDao;

	@Autowired
	private EatMenuDao eatMenuDao;

	//新しい店舗を追加する
	@Override
	public EatMapResponse addShop(EatMapRequest eatMapRequest) {
		//リクエスト内容をセットします
		EatMap reqShop = eatMapRequest.getEatMap();
		//リクエストは空白にしてはなりません
		if (!StringUtils.hasText(reqShop.getShop())||
				!StringUtils.hasText(reqShop.getCity())) {
			return new EatMapResponse("資料不能空");
		}
		//既に同じ名前の店舗が存在しています
		if (eatMapDao.existsById(reqShop.getShop())) {
			return new EatMapResponse("此店鋪已存在");
		}
		//追加したい店鋪データをセーブします
		eatMapDao.save(reqShop);
		return new EatMapResponse(reqShop, "新增店鋪成功");
	}

	//店舗の所在地を変更する
	@Override
	public EatMapResponse editShop(UpdateEatMapRequest updateReq) {
		//リクエスト内容をセットします
		String reqShop = updateReq.getShop();
		String reqCity = updateReq.getCity();
		String reqNewCity = updateReq.getNewcity();

		//リクエストは空白にしてはなりません
		if (!StringUtils.hasText(reqShop) || 
				!StringUtils.hasText(reqCity) || 
				!StringUtils.hasText(reqNewCity)) {
			return new EatMapResponse("修改內容不能為空");
		}

		//変更したい店舗は存在していません
		Optional<EatMap> shop = eatMapDao.findById(reqShop);
		if (!shop.isPresent()) {
			return new EatMapResponse(reqShop, "沒有此店家");
		}
		//存在している場合は店舗データをセットする
		EatMap eatMap = shop.get();
		
		//店舗の現所在地を間違ってはなりません
		if (!reqCity.equals(eatMap.getCity())) {
			return new EatMapResponse(reqShop, "該店家不在此地區");
		}
		
		//新し住所をセットしてセーブ
		eatMap.setCity(reqNewCity);
		eatMapDao.save(eatMap);
		return new EatMapResponse(reqShop, "修改店址成功");
	}
	
	//店舗を地域で絞る
	@Override
	public List<ListEatMapResponse> getMapByCity(String City) {
		
		//リクエスト内容によって取ったデータをセットします
		List<EatMap> findShop = eatMapDao.findByCity(City);
		//結果を入れる用のリスト
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		//絞った店舗のデータ全部見る
		for (EatMap cityMap : findShop) {
			//店舗のメニューを探し出す、結果用リストに入れる
			List<EatMenu> findMenu = eatMenuDao.findByShop(cityMap.getShop());
			for (EatMenu shopMenu : findMenu) {
				ListEatMapResponse allMenu = new ListEatMapResponse();
				allMenu.setShop(cityMap.getShop());
				allMenu.setRate(cityMap.getRate());
				allMenu.setMenu(shopMenu.getName());
				allMenu.setMenurate(shopMenu.getRate());
				allMenu.setPrice(shopMenu.getPrice());
				responseList.add(allMenu);
			}
		}
		//積み終わった結果を出します
		return responseList;
	}
	
	//店舗を評価で絞る、そして評価の高い方から並びます
	@Override
	public List<ListEatMapResponse> getMapByRate(EatMapRequest eatMapRequest) {

		//リクエスト内容によって取ったデータをセットします
		List<EatMap> findShop = 
				eatMapDao.findByRateGreaterThanEqualOrderByRateDesc
				(eatMapRequest.getRate());
		//結果を入れる用のリスト
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();
		//絞った店舗のデータ全部見る
		for (EatMap rateMap : findShop) {
			//店舗のメニューを探し出す、結果用リストに入れる
			List<EatMenu> findMenu = eatMenuDao.findByShop(rateMap.getShop());
			for (EatMenu shopMenu : findMenu) {
				ListEatMapResponse allMenu = new ListEatMapResponse();
				allMenu.setCity(rateMap.getCity());
				allMenu.setShop(rateMap.getShop());
				allMenu.setRate(rateMap.getRate());
				allMenu.setMenu(shopMenu.getName());
				allMenu.setMenurate(shopMenu.getRate());
				allMenu.setPrice(shopMenu.getPrice());
				responseList.add(allMenu);
			}
		}
		//積み終わった結果を出します
		return responseList;
	}

	//店舗とメニューを評価で絞る、そして評価の高い方から並びます
	@Override
	public List<ListEatMapResponse> getMapDetailByRate(ListEatMapResponse listReq) {

		//リクエスト内容によって取ったデータをセットします
		List<EatMap> findShop = 
				eatMapDao.findByRateGreaterThanEqualOrderByRateDesc
				(listReq.getRate());
		//結果を入れる用のリスト
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		//絞った店舗のデータ全部見る
		for (EatMap rateMap : findShop) {
			//店舗のメニューを探し出す、結果用リストに入れる
			List<EatMenu> findMenu = 
					eatMenuDao.findByShopAndRateGreaterThanEqualOrderByRateDesc
					(rateMap.getShop(),	listReq.getMenurate());
			for (EatMenu rateMenu : findMenu) {
				ListEatMapResponse allMenu = new ListEatMapResponse();
				allMenu.setCity(rateMap.getCity());
				allMenu.setShop(rateMap.getShop());
				allMenu.setRate(rateMap.getRate());
				allMenu.setMenu(rateMenu.getName());
				allMenu.setMenurate(rateMenu.getRate());
				allMenu.setPrice(rateMenu.getPrice());
				responseList.add(allMenu);
			}
		}
		//積み終わった結果を出します
		return responseList;
	}

	//店舗とそのメニューを削除
	//削除範囲を限定する
	@Transactional
	public EatMapResponse deleteData(UpdateEatMapRateRequest request) {
		//リクエストは空白にしてはなりません
		if (!StringUtils.hasText(request.getShop())) {
			return new EatMapResponse("輸入不能為空");
		}
		
		//削除したい店舗は存在していません
		Optional<EatMap> shop = eatMapDao.findById(request.getShop());
		if (!shop.isPresent()) {
			return new EatMapResponse(request.getShop(), "沒有此店家");
		}
		
		//店舗が存在していれば店舗データを削除
		eatMapDao.deleteById(request.getShop());

		//さらにその店舗にメニューデータがあれば削除
		if (eatMenuDao.existsByShop(request.getShop())) {
			eatMenuDao.deleteAllByShop(request.getShop());
		}
		//削除成功したメッセジーを出します
		return new EatMapResponse(request.getShop(), "刪除成功");
	}

}
