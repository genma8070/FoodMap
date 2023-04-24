package com.example.java_demo_test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.EatMap;
import com.example.java_demo_test.vo.ListEatMapResponse;

@Repository
public interface EatMapDao extends JpaRepository<EatMap, String> {

	List<EatMap> findByCity(String City);
	
	Boolean existsByRate(Integer rate);
	
	List<EatMap> findByRateGreaterThanEqualOrderByRateDesc(Integer rate);

	
	
	
}
