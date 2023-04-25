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

//宣告したメゾットを定義するクラス
@Service
public class EatMenuServiceImpl implements EatMenuService {
	@Autowired
	private EatMenuDao eatMenuDao;

	@Autowired
	private EatMapDao eatMapDao;

	//新しいメニューを追加する
	@Override
	public EatMenuResponse addMenu(EatMenuRequest menuReq) {
		//リクエスト内容をセットします
		MapMenu reqMapMenu = new MapMenu(menuReq.getName(), menuReq.getShop());
		EatMenu reqMenu = new EatMenu(menuReq.getName(), menuReq.getShop(),
				menuReq.getRate(), menuReq.getPrice());
		//リクエストは空白にしてはなりません
		if (!StringUtils.hasText(menuReq.getName()) || 
				!StringUtils.hasText(menuReq.getShop())
				|| menuReq.getPrice() == null || menuReq.getRate() == null) {
			return new EatMenuResponse("資料不能空");
		}

		//メニューを追加したい店舗は存在していません
		if (!eatMapDao.existsById(menuReq.getShop())) {
			{
				return new EatMenuResponse("無此店家");
			}
		}

		//評価の範囲は1から5までじゃなければなりません
		if (!(menuReq.getRate() <= 5) || !(menuReq.getRate() > 0)) {
			return new EatMenuResponse("評分必須是1~5");
		}

		//値段は1円以下にしてはなりません
		if (menuReq.getPrice() < 1) {
			return new EatMenuResponse("價格不得小於1");
		}

		//既に同じメニューが存在しています
		if (eatMenuDao.existsById(reqMapMenu)) {
			return new EatMenuResponse("該店家已有該餐點");
		}

		//店舗が所有できるメニュー数が上限に達しています
		if (eatMenuDao.countByShop(menuReq.getShop()) >= 3) {
			return new EatMenuResponse("資料已達上限");
		}

		//メニューをセーブします
		eatMenuDao.save(reqMenu);

		//メニュー更新したら属する店舗のデータを探して、セットします
		Optional<EatMap> shop = eatMapDao.findById(menuReq.getShop());
		EatMap shopRate = shop.get();
		
		// 店内のすべてのメニューデータを探します
		var list = eatMenuDao.findAllByShop(menuReq.getShop());
		
		// 店の評価を算出します、計算式は店の評価＝（すべてのメニューの評価を合計してメニュー数で割ります）
		//SUMはすべてのメニューの評価の合計を意味する
		int sum = 0;
		//RATEは店の評価を意味する
		int rate = 0;

		//LIMITはその店のメニュー数を意味する
		//メニュー数は１から３までの可能性があります,データを取り出せるインデックスの初期値は０のため、０から２まで最大三つ探します
		for (int limit = 0; limit <= 2; limit++) {
			EatMenu item = list.get(limit);
			sum += item.getRate();
			
			//メニュー数は１から数えますけどインデックスは０から数えますため、インデックスの最大値はメニュー数を１引きます
			//上限に達したら店の評価を算出します
			if (limit == list.size() - 1) {
				rate = sum / list.size();
				break;
			}
		}

		//店の評価をセットします
		shopRate.setRate(rate);
		//セットしたデータをセーブします
		eatMapDao.save(shopRate);
		return new EatMenuResponse(menuReq.getShop(), "追加菜單並修改店家評分成功");

	}

	//メニューの評価と値段を編輯する、さらに店舗の評価に反映します
	public EatMenuResponse editMenu(UpdateEatMenuRequest updateReq) {
		MapMenu changeMenu = new MapMenu(updateReq.getName(), updateReq.getShop());
		EatMenu reqMenu = new EatMenu(updateReq.getName(), updateReq.getShop(), updateReq.getNewrate(),
				updateReq.getNewprice());
		EatMenu oldMenu = eatMenuDao.findByNameAndShop(updateReq.getName(), updateReq.getShop());
		//リクエスト内容をセットします
		if (reqMenu.getPrice() == null || reqMenu.getRate() == null) {
			return new EatMenuResponse("資料不能空拉");
		}

		//値段は1円以下にしてはなりません
		if (!(reqMenu.getRate() <= 5) || !(reqMenu.getRate() > 0)) {
			return new EatMenuResponse("評分必須是1~5");
		}
		
		//値段は1円以下にしてはなりません
		if (reqMenu.getPrice() < 1) {
			return new EatMenuResponse("價格不得小於1");
		}

		//変更したいメニューは存在していません
		if (!eatMenuDao.existsById(changeMenu)) {
			return new EatMenuResponse("沒有這個菜單");
		}

		//輸入したデータと元のデータは同じです
		if (oldMenu.getRate() == reqMenu.getRate() && oldMenu.getPrice() == reqMenu.getPrice()) {
			return new EatMenuResponse("一模一樣");

		}
		//輸入したデータをセットします
		oldMenu.setRate(reqMenu.getRate());
		oldMenu.setPrice(reqMenu.getPrice());

		//セットしたデータをセーブします
		eatMenuDao.save(oldMenu);
		return new EatMenuResponse(oldMenu, "修改菜單成功");

	}
}
