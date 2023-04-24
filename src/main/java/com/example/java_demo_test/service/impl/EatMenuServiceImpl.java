package com.example.java_demo_test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.EatMap;
import com.example.java_demo_test.entity.EatMenu;
import com.example.java_demo_test.entity.MapMenu;
import com.example.java_demo_test.repository.EatMapDao;
import com.example.java_demo_test.repository.EatMenuDao;
import com.example.java_demo_test.service.ifs.EatMenuService;
import com.example.java_demo_test.vo.EatMenuRequest;
import com.example.java_demo_test.vo.EatMenuResponse;
import com.example.java_demo_test.vo.UpdateEatMenuRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Service
public class EatMenuServiceImpl implements EatMenuService {
	@Autowired
	private EatMenuDao eatMenuDao;

	@Autowired
	private EatMapDao eatMapDao;

	@Override
	public EatMenuResponse addMenu(EatMenuRequest menuReq) {
		MapMenu reqMapMenu = new MapMenu(menuReq.getName(), menuReq.getShop());
		EatMenu reqMenu = new EatMenu(menuReq.getName(), menuReq.getShop(), menuReq.getRate(), menuReq.getPrice());
		// 防呆
		if (!StringUtils.hasText(menuReq.getName()) || !StringUtils.hasText(menuReq.getShop())
				|| menuReq.getPrice() == null || menuReq.getRate() == null) {
			return new EatMenuResponse("資料不能空");
		}

		if (!eatMapDao.existsById(menuReq.getShop())) {
			{
				return new EatMenuResponse("無此店家");
			}
		}

		if (!(menuReq.getRate() <= 5) || !(menuReq.getRate() > 0)) {
			return new EatMenuResponse("評分必須是1~5");
		}

		if (menuReq.getPrice() <= 0) {
			return new EatMenuResponse("價格不得小於等於0");
		}

		if (eatMenuDao.existsById(reqMapMenu)) {
			return new EatMenuResponse("該店家已有該餐點");
		}

		if (eatMenuDao.countByShop(menuReq.getShop()) >= 3) {
			return new EatMenuResponse("資料已達上限");
		}

		eatMenuDao.save(reqMenu);

		// 尋找要修改的店舖
		Optional<EatMap> shop = eatMapDao.findById(menuReq.getShop());
		EatMap shopRate = shop.get();
		// 將要找的店所有的菜單放入LIST
		var list = eatMenuDao.findAllByShop(menuReq.getShop());
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
//		EatMap shopRate = new EatMap();
		shopRate.setRate(rate);
		eatMapDao.save(shopRate);
		return new EatMenuResponse(menuReq.getShop(), "追加菜單並修改店家評分成功");

	}

	public EatMenuResponse editMenu(UpdateEatMenuRequest updateReq) {
		MapMenu changeMenu = new MapMenu(updateReq.getName(), updateReq.getShop());
		EatMenu reqMenu = new EatMenu(updateReq.getName(), updateReq.getShop(), updateReq.getNewrate(),
				updateReq.getNewprice());
		EatMenu oldMenu = eatMenuDao.findByNameAndShop(updateReq.getName(), updateReq.getShop());
		// 防呆
		if (reqMenu.getPrice() == null || reqMenu.getRate() == null) {
			return new EatMenuResponse("資料不能空拉");
		}

		if (!(reqMenu.getRate() <= 5) || !(reqMenu.getRate() > 0)) {
			return new EatMenuResponse("評分必須是1~5");
		}

		if (reqMenu.getPrice() < 1) {
			return new EatMenuResponse("價格不得小於等於0");
		}

		if (!eatMenuDao.existsById(changeMenu)) {
			return new EatMenuResponse("沒有這個菜單");
		}

		if (oldMenu.getRate() == reqMenu.getRate() && oldMenu.getPrice() == reqMenu.getPrice()) {
			return new EatMenuResponse("一模一樣");

		}
		oldMenu.setRate(reqMenu.getRate());
		oldMenu.setPrice(reqMenu.getPrice());

		eatMenuDao.save(oldMenu);
		return new EatMenuResponse(oldMenu, "修改菜單成功");

	}
}
