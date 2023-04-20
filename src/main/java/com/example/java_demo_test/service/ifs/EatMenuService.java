package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.vo.EatMenuRequest;
import com.example.java_demo_test.vo.EatMenuResponse;
import com.example.java_demo_test.vo.UpdateEatMenuRequest;

public interface EatMenuService {
	public EatMenuResponse addMenu(EatMenuRequest eatMenuRequest);
	
	public EatMenuResponse editMenu(UpdateEatMenuRequest updateReq);
	

}
