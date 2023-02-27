package myWay.controller;

import java.util.ArrayList;

import myWay.dao.MemberDao;
import myWay.dto.MemberDto;


public class MemberController {
	//* 싱글톤
	private static MemberController mc = new MemberController();
	private MemberController() {};
	public static MemberController getInstance() {
		return mc;
	}
	
	//로그인한 Member 객체를 담기 위해
	private MemberDto logSeasion = null;

	public MemberDto getLogSeasion() {
		return logSeasion;
	}
	
	
	//로그인
	public int logIn(String id, int pw) {
		ArrayList<MemberDto> memberDB = MemberDao.getInstance().returnMemberDB();
		
		for(int i = 0; i < memberDB.size(); i++) {
			if((memberDB.get(i).getMemberId().equals(id))&&(memberDB.get(i).getMemberPw() == pw)){
				logSeasion = memberDB.get(i);
				return memberDB.get(i).getMemberNo();
			}
		}
		return -1;
	}
}
