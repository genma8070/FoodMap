package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.EatMenu;
import com.example.java_demo_test.entity.MapMenu;
import com.example.java_demo_test.vo.EatMapRequest;
@Repository
public interface EatMenuDao extends JpaRepository<EatMenu, MapMenu>{

	List<EatMenu> findAllByShop(String shop);
	//計算餐點數量
	int countByShop(String shop);
	
	List<EatMenu> findByShop(String shop);
	
	List<EatMenu> findByRateGreaterThan(EatMapRequest eatMapRequest);
	
	List<EatMenu> findByShopAndRateGreaterThanEqualOrderByRateDesc(String shop,Integer rate);
	
	Boolean existsByShop(String shop);
	
	void deleteAllByShop(String shop);
	
	EatMenu findByNameAndShop(String name, String shop);
	
	
	

}

