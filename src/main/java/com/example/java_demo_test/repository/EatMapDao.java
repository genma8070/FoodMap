package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.EatMap;

@Repository
public interface EatMapDao extends JpaRepository<EatMap, String> {

	List<EatMap> findByCity(String City);
	
	List<EatMap> findByPointGreaterThanEqualOrderByPointDesc(Integer point);
}
