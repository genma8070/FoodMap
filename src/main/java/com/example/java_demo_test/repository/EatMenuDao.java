package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.EatMenu;
import com.example.java_demo_test.entity.MapMenu;
import com.example.java_demo_test.vo.EatMapRequest;
@Repository
public interface EatMenuDao extends JpaRepository<EatMenu, MapMenu>{

	//店舗名によって全てのデータを探す
	List<EatMenu> findAllByShop(String shop);
	
	//追加したメニュー数をカウントする
	int countByShop(String shop);
	
	//店舗名によってデータを探す
	List<EatMenu> findByShop(String shop);
	
	//店舗名と輸入した評価以上（含む）によってデータを探す、そして評価が高い順にから並びます
	List<EatMenu> findByShopAndRateGreaterThanEqualOrderByRateDesc(String shop,Integer rate);
	
	//店舗がいるかどうか確認する
	Boolean existsByShop(String shop);
	
	//店舗と関連するデータをすべて削除
	void deleteAllByShop(String shop);
	
	//店舗とメニュー名によってデータを探す
	EatMenu findByNameAndShop(String name, String shop);
}

