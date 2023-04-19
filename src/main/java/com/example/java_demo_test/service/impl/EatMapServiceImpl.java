package com.example.java_demo_test.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import com.example.java_demo_test.vo.UpdateHyoukaEatMapRequest;

@Service
public class EatMapServiceImpl implements EatMapService {
	@Autowired
	private EatMapDao eatMapDao;

	@Autowired
	private EatMenuDao eatMenuDao;

	@Override
	public EatMapResponse addTenpo(EatMapRequest eatMapRequest) {
		EatMap reqTenpo = eatMapRequest.getEatMap();
		// 防呆
		if (!StringUtils.hasText(reqTenpo.getName())) {
			return new EatMapResponse("資料不能空拉");
		}
		if (eatMapDao.existsById(reqTenpo.getName())) {
			return new EatMapResponse("此店鋪已存在");
		}
		// 存入店鋪
		eatMapDao.save(reqTenpo);
		return new EatMapResponse(reqTenpo, "新增店鋪成功");
	}

	@Override
	public EatMapResponse tenpoShuusei(UpdateEatMapRequest updatereq) {
		String reqName = updatereq.getName();
		String reqCity = updatereq.getCity();
		String reqNewCity = updatereq.getNewcity();

		// 防呆
		if (!StringUtils.hasText(reqName) || !StringUtils.hasText(reqCity) || !StringUtils.hasText(reqNewCity)) {
			return new EatMapResponse("修改內容不能為空");
		}

		Optional<EatMap> op = eatMapDao.findById(reqName);
		if (!op.isPresent()) {
			return new EatMapResponse(reqName, "沒有此店家");
		}
		EatMap eatMap = op.get();
		if (!reqCity.equals(eatMap.getCity())) {
			return new EatMapResponse(reqName, "該店家不在此地區");
		}
		// 存入新店址
		eatMap.setCity(reqNewCity);
		eatMapDao.save(eatMap);
		return new EatMapResponse(reqName, "修改店址成功");
	}

	public EatMapResponse hyoukaShuusei(UpdateHyoukaEatMapRequest request) {
		// 防呆
		Optional<EatMap> op = eatMapDao.findById(request.getName());
		if (!op.isPresent()) {
			return new EatMapResponse(request.getName(), "沒有此店家");
		}
		EatMap eatMap = op.get();
		if (!request.getCity().equals(eatMap.getCity())) {
			return new EatMapResponse(request.getName(), "該店家不在此地區");
		}
		// 將要找的店所有的菜單放入LIST
		var list = eatMenuDao.findAllByShop(request.getName());
		// 計算店家評價=所有餐點評價的平均
		int sum = 0;
		int hyouka = 0;
		// 餐點可能有1~3樣，當計算完該店鋪擁有的所有菜單後跳出
		for (int limit = 0; limit <= 2; limit++) {
			EatMenu item = list.get(limit);
			sum += item.getPoint();
			if (limit == list.size() - 1) {
				hyouka = sum / list.size();
				break;
			}
		}
		// 存入評價
		eatMap.setPoint(hyouka);
		eatMapDao.save(eatMap);
		return new EatMapResponse(request.getName(), "修改評分成功");

	}

	@Override
	public List<ListEatMapResponse> getMapByCity(String City) {

		List<EatMap> result = eatMapDao.findByCity(City);

		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		for (EatMap item : result) {
			ListEatMapResponse mokuhyo = new ListEatMapResponse();
			mokuhyo.setName(item.getName());
			mokuhyo.setPoint(item.getPoint());
			responseList.add(mokuhyo);

			List<EatMenu> result2 = eatMenuDao.findByShop(item.getName());
			for (EatMenu item2 : result2) {
				ListEatMapResponse mokuhyo2 = new ListEatMapResponse();
				mokuhyo2.setMenu(item2.getName());
				mokuhyo2.setMpoint(item2.getPoint());
				mokuhyo2.setPrice(item2.getPrice());
				responseList.add(mokuhyo2);
			}
		}
		return responseList;
	}

	@Override
	public List<ListEatMapResponse> getMapByPoint(EatMapRequest eatMapRequest) {

		List<EatMap> result = eatMapDao.findByPointGreaterThanEqualOrderByPointDesc(eatMapRequest.getPoint());

		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		for (EatMap item : result) {
			ListEatMapResponse mokuhyo = new ListEatMapResponse();
			mokuhyo.setCity(item.getCity());
			mokuhyo.setName(item.getName());
			mokuhyo.setPoint(item.getPoint());
			responseList.add(mokuhyo);

			List<EatMenu> result2 = eatMenuDao.findByShop(item.getName());
			for (EatMenu item2 : result2) {
				ListEatMapResponse mokuhyo2 = new ListEatMapResponse();
				mokuhyo2.setMenu(item2.getName());
				mokuhyo2.setMpoint(item2.getPoint());
				mokuhyo2.setPrice(item2.getPrice());
				responseList.add(mokuhyo2);
			}
		}
		return responseList;
	}

	@Override
	public List<ListEatMapResponse> getMapDetailByPoint(ListEatMapResponse listReq) {

		List<EatMap> result = eatMapDao.findByPointGreaterThanEqualOrderByPointDesc(listReq.getPoint());

		List<ListEatMapResponse> responseList = new ArrayList<ListEatMapResponse>();

		for (EatMap item : result) {
			ListEatMapResponse mokuhyo = new ListEatMapResponse();
			mokuhyo.setCity(item.getCity());
			mokuhyo.setName(item.getName());
			mokuhyo.setPoint(item.getPoint());
			responseList.add(mokuhyo);

			List<EatMenu> result2 = eatMenuDao.findByShopAndPointGreaterThanEqualOrderByPointDesc(item.getName(),
					listReq.getMpoint());
			for (EatMenu item2 : result2) {
				ListEatMapResponse mokuhyo2 = new ListEatMapResponse();
				mokuhyo2.setMenu(item2.getName());
				mokuhyo2.setMpoint(item2.getPoint());
				mokuhyo2.setPrice(item2.getPrice());
				responseList.add(mokuhyo2);
			}
		}
		return responseList;
	}

	// 限定刪除範圍
	@Transactional
	public EatMapResponse deleteDate(UpdateHyoukaEatMapRequest request) {
		// 防呆
		Optional<EatMap> op = eatMapDao.findById(request.getName());
		if (!op.isPresent()) {
			return new EatMapResponse(request.getName(), "沒有此店家");
		}
		eatMapDao.deleteById(request.getName());

		if (eatMenuDao.existsByShop(request.getName())) {
			eatMenuDao.deleteAllByShop(request.getName());

		}
		return new EatMapResponse(request.getName(), "刪除成功");
	}

}
