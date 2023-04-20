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
import com.example.java_demo_test.vo.UpdateEatMapRequest;
import com.example.java_demo_test.vo.UpdateEatMapRateRequest;

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
	public EatMapResponse editShop(UpdateEatMapRequest updatereq) {
		String reqShop = updatereq.getShop();
		String reqCity = updatereq.getCity();
		String reqNewCity = updatereq.getNewcity();

		// 防呆
		if (!StringUtils.hasText(reqShop) || !StringUtils.hasText(reqCity) || !StringUtils.hasText(reqNewCity)) {
			return new EatMapResponse("修改內容不能為空");
		}

		Optional<EatMap> op = eatMapDao.findById(reqShop);
		if (!op.isPresent()) {
			return new EatMapResponse(reqShop, "沒有此店家");
		}
		
		EatMap eatMap = op.get();
		
		if (!reqCity.equals(eatMap.getCity())) {
			return new EatMapResponse(reqShop, "該店家不在此地區");
		}
		
		// 存入新店址
		eatMap.setCity(reqNewCity);
		eatMapDao.save(eatMap);
		return new EatMapResponse(reqShop, "修改店址成功");
	}

	public EatMapResponse editRate(UpdateEatMapRateRequest request) {
		// 防呆
		if (!StringUtils.hasText(request.getShop()) || !StringUtils.hasText(request.getCity())) {
			return new EatMapResponse("資料不能為空");
		}
		
		if (!eatMenuDao.existsByShop(request.getShop())) {
			return new EatMapResponse("該店家尚無餐點");
		}
		
		Optional<EatMap> op = eatMapDao.findById(request.getShop());
		if (!op.isPresent()) {
			return new EatMapResponse(request.getShop(), "沒有此店家");
		}
		
		EatMap eatMap = op.get();
		if (!request.getCity().equals(eatMap.getCity())) {
			return new EatMapResponse(request.getShop(), "該店家不在此地區");
		}
		
		// 將要找的店所有的菜單放入LIST
		var list = eatMenuDao.findAllByShop(request.getShop());
		
		// 計算店家評價=所有餐點評價的平均
		int sum = 0;
		int rate = 0;
		
		// 餐點可能有1~3樣，當計算完該店鋪擁有的所有菜單後跳出
		for (int limit = 0; limit <= 2; limit++) {
			EatMenu item = list.get(limit);
			sum += item.getRate();
			if (limit == list.size() - 1) {
				rate = sum / list.size();
				break;
			}
		}
		
		// 存入評價
		eatMap.setRate(rate);
		eatMapDao.save(eatMap);
		return new EatMapResponse(request.getShop(), "修改評分成功");

	}

	@Override
	public List<ListEatMapResponse> getMapByCity(String City) {

		List<EatMap> result = eatMapDao.findByCity(City);
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		for (EatMap item : result) {
			ListEatMapResponse cityMap = new ListEatMapResponse();
			cityMap.setShop(item.getShop());
			cityMap.setRate(item.getRate());
			responseList.add(cityMap);

			List<EatMenu> result2 = eatMenuDao.findByShop(item.getShop());
			for (EatMenu item2 : result2) {
				ListEatMapResponse cityMap2 = new ListEatMapResponse();
				cityMap2.setMenu(item2.getName());
				cityMap2.setMenurate(item2.getRate());
				cityMap2.setPrice(item2.getPrice());
				responseList.add(cityMap2);
			}
		}
		return responseList;
	}

	@Override
	public List<ListEatMapResponse> getMapByRate(EatMapRequest eatMapRequest) {

		List<EatMap> result = eatMapDao.findByRateGreaterThanEqualOrderByRateDesc(eatMapRequest.getRate());
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();
		
		
		for (EatMap item : result) {
			ListEatMapResponse list = new ListEatMapResponse();
			list.setCity(item.getCity());
			list.setShop(item.getShop());
			list.setRate(item.getRate());
			responseList.add(list);

			List<EatMenu> result2 = eatMenuDao.findByShop(item.getShop());
			for (EatMenu item2 : result2) {
				ListEatMapResponse list2 = new ListEatMapResponse();
				list2.setMenu(item2.getName());
				list2.setMenurate(item2.getRate());
				list2.setPrice(item2.getPrice());
				responseList.add(list2);
			}
		}
		return responseList;
	}

	@Override
	public List<ListEatMapResponse> getMapDetailByRate(ListEatMapResponse listReq) {

		List<EatMap> result = eatMapDao.findByRateGreaterThanEqualOrderByRateDesc(listReq.getRate());
		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		for (EatMap item : result) {
			ListEatMapResponse list = new ListEatMapResponse();
			list.setCity(item.getCity());
			list.setShop(item.getShop());
			list.setRate(item.getRate());
			responseList.add(list);

			List<EatMenu> result2 = eatMenuDao.findByShopAndRateGreaterThanEqualOrderByRateDesc(item.getShop(),
					listReq.getMenurate());
			for (EatMenu item2 : result2) {
				ListEatMapResponse list2 = new ListEatMapResponse();
				list2.setMenu(item2.getName());
				list2.setMenurate(item2.getRate());
				list2.setPrice(item2.getPrice());
				responseList.add(list2);
			}
		}
		return responseList;
	}

	// 限定刪除範圍
	@Transactional
	public EatMapResponse deleteData(UpdateEatMapRateRequest request) {
		// 防呆
		if (!StringUtils.hasText(request.getShop())) {
			return new EatMapResponse("修改內容不能為空");
		}
		
		Optional<EatMap> op = eatMapDao.findById(request.getShop());
		if (!op.isPresent()) {
			return new EatMapResponse(request.getShop(), "沒有此店家");
		}
		
		eatMapDao.deleteById(request.getShop());

		if (eatMenuDao.existsByShop(request.getShop())) {
			eatMenuDao.deleteAllByShop(request.getShop());
		}
		return new EatMapResponse(request.getShop(), "刪除成功");
	}

}
