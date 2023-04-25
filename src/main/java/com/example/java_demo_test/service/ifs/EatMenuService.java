package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.vo.EatMenuRequest;
import com.example.java_demo_test.vo.EatMenuResponse;
import com.example.java_demo_test.vo.UpdateEatMenuRequest;

//メゾットを宣告するインターフェース
public interface EatMenuService {
	
	//新しいメニューを追加する
	public EatMenuResponse addMenu(EatMenuRequest eatMenuRequest);
	
	//メニューの評価と値段を編輯する、さらに店舗の評価に反映します
	public EatMenuResponse editMenu(UpdateEatMenuRequest updateReq);
	

}
