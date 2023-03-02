package myWay.controller;

import java.util.ArrayList;

import myWay.dao.SalesDao;
import myWay.dto.SalesDto;

public class SalesController {
	
	//싱글톤
	private static SalesController sc = new SalesController();
	
	
	public static SalesController getInstance() {
		return sc;
	}

	// 생성자
	private SalesController() {}
		
	// 달력 출력
	
	
	// 월 매출 출력 or 일 매출 출력
	public ArrayList<SalesDto> printSales(){
		return SalesDao.getInstance().printSales();
	}
	
}
