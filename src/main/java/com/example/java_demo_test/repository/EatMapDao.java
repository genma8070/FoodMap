package com.example.java_demo_test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.EatMap;
import com.example.java_demo_test.vo.ListEatMapResponse;

@Repository
public interface EatMapDao extends JpaRepository<EatMap, String> {

	//地域によってデータを探す
	List<EatMap> findByCity(String City);
	
	//輸入した評価以上（含む）によってデータを探す、そして評価が高い順から並びます
	List<EatMap> findByRateGreaterThanEqualOrderByRateDesc(Integer rate);

	
	
	
}
