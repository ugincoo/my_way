package myWay.controller;

import java.util.ArrayList;

import myWay.dao.CouponDao;
import myWay.dto.CouponDto;
import myWay.dto.MemberDto;

public class CouponController {
	// 싱글톤
	private static CouponController couponcontroller = new CouponController();
	public static CouponController getInstance() {
		return couponcontroller;
	}
	
	public ArrayList<CouponDto> couponList(){
		return CouponDao.getInstance().couponList();
	}
	
	//로그인 세션
	//private MemberDto LogSeasion = null;
	
	//MemberDto dto = new MemberDto();
	
	// 로그인 세션 값 불러오기
	//public MemberDto dto() {
	//	return LogSeasion;
	//}
	
	
	
	
}
