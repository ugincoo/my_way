package myWay.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import myWay.dao.SalesDao;
import myWay.dto.SalesDto;

public class SalesController {
	
	//싱글톤
	private static SalesController sc = new SalesController();
	public static SalesController getInstance() { return sc; }
	// 생성자
	private SalesController() {}
	
	// 달력 일 매출
	public int findPrice(String date) {
		
		return SalesDao.getInstance().findPrice(date);
	}
	
	
	// 월 매출 출력 or 일 매출 출력
	public ArrayList<SalesDto> printSales(String date){
		return SalesDao.getInstance().printSales(date);
	}
	
	
}
