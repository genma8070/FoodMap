package com.example.java_demo_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		MapMenu reqMmenu = new MapMenu(menuReq.getName(), menuReq.getShop());
		EatMenu reqMenu = new EatMenu(menuReq.getName(), menuReq.getShop(), menuReq.getPoint(),
				menuReq.getPrice());
		// 防呆
		if (!StringUtils.hasText(menuReq.getName()) || !StringUtils.hasText(menuReq.getShop())
				|| menuReq.getPrice() == null || menuReq.getPoint() == null) {
			return new EatMenuResponse("資料不能空拉");

		}
		
		if (!eatMapDao.existsById(menuReq.getShop())) {
			{
				return new EatMenuResponse("無此店家");

			}
		}
		
		if (!(menuReq.getPoint() <= 5) || !(menuReq.getPoint() > 0)) {
			return new EatMenuResponse("評分必須是1~5");
		}

		if (menuReq.getPrice() <= 0) {
			return new EatMenuResponse("價格不得小於等於0");
		}

		if (eatMenuDao.existsById(reqMmenu)) {
			return new EatMenuResponse("該店家已有該資料");
		}

		if (eatMenuDao.countByShop(menuReq.getShop()) >= 3) {
			return new EatMenuResponse("資料已達上限");
		}

		eatMenuDao.save(reqMenu);
		return new EatMenuResponse(reqMenu, "新增菜單成功");

	}

	public EatMenuResponse henshuuMenu(UpdateEatMenuRequest updateReq) {
		MapMenu kaemenu = new MapMenu(updateReq.getName(), updateReq.getShop());
		EatMenu reqMenu = new EatMenu(updateReq.getName(), updateReq.getShop(), updateReq.getNewpoint(),
				updateReq.getNewprice());
		EatMenu oldMenu = eatMenuDao.findByNameAndShop(updateReq.getName(), updateReq.getShop());
		// 防呆
		if (reqMenu.getPrice() == null || reqMenu.getPoint() == null) {
			return new EatMenuResponse("資料不能空拉");

		}
		if (!(reqMenu.getPoint() <= 5) || !(reqMenu.getPoint() > 0)) {
			return new EatMenuResponse("評分必須是1~5");
		}

		if (reqMenu.getPrice() < 1) {
			return new EatMenuResponse("價格不得小於等於0");
		}

		if (!eatMenuDao.existsById(kaemenu)) {
			return new EatMenuResponse("無該項資料可修改");
		}

		if (oldMenu.getPoint() == reqMenu.getPoint() && oldMenu.getPrice() == reqMenu.getPrice()) {
			return new EatMenuResponse("一模一樣");

		}
		oldMenu.setPoint(reqMenu.getPoint());
		oldMenu.setPrice(reqMenu.getPrice());

		eatMenuDao.save(oldMenu);
		return new EatMenuResponse(oldMenu, "修改菜單成功");

	}
}
