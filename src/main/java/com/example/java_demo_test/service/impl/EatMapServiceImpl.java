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

@Service
public class EatMapServiceImpl implements EatMapService {
	@Autowired
	private EatMapDao eatMapDao;

	@Autowired
	private EatMenuDao eatMenuDao;

	@Override
	public EatMapResponse addShop(EatMapRequest eatMapRequest) {
		EatMap reqShop = eatMapRequest.getEatMap();
		// 防呆
		if (!StringUtils.hasText(reqShop.getShop())||!StringUtils.hasText(reqShop.getCity())) {
			return new EatMapResponse("資料不能空");
		}
		if (eatMapDao.existsById(reqShop.getShop())) {
			return new EatMapResponse("此店鋪已存在");
		}
		// 存入店鋪
		eatMapDao.save(reqShop);
		return new EatMapResponse(reqShop, "新增店鋪成功");
	}

	@Override
	public EatMapResponse editShop(UpdateEatMapRequest updateReq) {
		String reqShop = updateReq.getShop();
		String reqCity = updateReq.getCity();
		String reqNewCity = updateReq.getNewcity();

		// 防呆
		if (!StringUtils.hasText(reqShop) || !StringUtils.hasText(reqCity) || !StringUtils.hasText(reqNewCity)) {
			return new EatMapResponse("修改內容不能為空");
		}

		Optional<EatMap> shop = eatMapDao.findById(reqShop);
		if (!shop.isPresent()) {
			return new EatMapResponse(reqShop, "沒有此店家");
		}
		
		EatMap eatMap = shop.get();
		
		if (!reqCity.equals(eatMap.getCity())) {
			return new EatMapResponse(reqShop, "該店家不在此地區");
		}
		
		// 存入新店址
		eatMap.setCity(reqNewCity);
		eatMapDao.save(eatMap);
		return new EatMapResponse(reqShop, "修改店址成功");
	}
	
	@Override
	public List<ListEatMapResponse> getMapByCity(String City) {

		List<EatMap> findShop = eatMapDao.findByCity(City);
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		for (EatMap cityMap : findShop) {
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
		return responseList;
	}
	
	@Override
	public List<ListEatMapResponse> getMapByRate(EatMapRequest eatMapRequest) {

		List<EatMap> findShop = eatMapDao.findByRateGreaterThanEqualOrderByRateDesc(eatMapRequest.getRate());
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();
			
		for (EatMap rateMap : findShop) {
			
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
		return responseList;
	}

	@Override
	public List<ListEatMapResponse> getMapDetailByRate(ListEatMapResponse listReq) {

		List<EatMap> findShop = eatMapDao.findByRateGreaterThanEqualOrderByRateDesc(listReq.getRate());
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		for (EatMap rateMap : findShop) {
			
			List<EatMenu> findMenu = eatMenuDao.findByShopAndRateGreaterThanEqualOrderByRateDesc(rateMap.getShop(),
					listReq.getMenurate());
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
		return responseList;
	}

	// 限定刪除範圍
	@Transactional
	public EatMapResponse deleteData(UpdateEatMapRateRequest request) {
		// 防呆
		if (!StringUtils.hasText(request.getShop())) {
			return new EatMapResponse("輸入不能為空");
		}
		
		Optional<EatMap> shop = eatMapDao.findById(request.getShop());
		if (!shop.isPresent()) {
			return new EatMapResponse(request.getShop(), "沒有此店家");
		}
		
		eatMapDao.deleteById(request.getShop());

		if (eatMenuDao.existsByShop(request.getShop())) {
			eatMenuDao.deleteAllByShop(request.getShop());
		}
		return new EatMapResponse(request.getShop(), "刪除成功");
	}

}
