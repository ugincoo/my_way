package myWay.controller;



import myWay.dto.*;
import myWay.dao.*;

import java.util.ArrayList;

import myWay.controller.*;
import myWay.view.*;

public class MemberController {
	// 얘는 MemberDao에 있는 기능들을 호출할 때 쓴다.
	//싱글톤
	private static MemberController memberController = new MemberController();
	public MemberController() {
	}
	public static MemberController getInstance() {
		return memberController;
	}
	
	//로그인 세션
	private MemberDto LogSeasion = null;
	
	MemberDto dto = new MemberDto();
	
	// 로그인 세션 값 불러오기
	public MemberDto dto() {
		return LogSeasion;
	}
	
	// 로그인 세션 값의 유무 판단 (로그인 되어 있는 여부 확인)
	public boolean checkLogin() {
		MemberDto dto = dto();
		
		if (dto != null) { 
			// 세션에 회원 아이디 값이 있는 경우 (즉, 회원이 로그인되어 있는 경우)
			return true;
		} else {
			System.out.println("로그인이 되어 있지 않습니다.");
			return false;
		}
		
	}
	
	//회원가입
	public boolean signup(String memberId, int memberPw, String memberphone, String membername) {
		
		boolean result 
			= MemberDao.getInstance().signup(memberId, memberPw,memberphone,membername);

		return result;
	}
	
	
	//로그인
	public boolean login(String memberId,int memberPw ) {
		
		 MemberDto result
			= MemberDao.getInstance().login(memberId,memberPw);
		
		 if(result!=null) {
			 LogSeasion = result;
			 System.out.println(LogSeasion);
			 return true;
		 }
		 return false;
		
	
				
	}
	
	// 회원이 입력한 비밀번호와 세션의 비밀번호 체크
	public boolean checkPassword (int memberpassword) {
		MemberDto dto = dto();
		
		if (memberpassword == dto.getMemberPw()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getMemberNo () {
		MemberDto dto = dto();
		return dto.getMemberNo();
	}
	
	//비밀번호변경
	public int update(int memberNo, int newpassword) {
		
		return MemberDao.getInstance().update(memberNo, newpassword); 
	}
	
	
	//회원탈퇴
	public boolean delete(String memberId , int memberpw) {
		return MemberDao.getInstance().delete(memberId,memberpw);
	}

	//아이디찾기 유효성검사 핸드폰번호와 DB의 이름이 일치하는지
	public MemberDto checknamephone(String membername, String memberphone ) {
		 return MemberDao.getInstance().checknamephone(membername, memberphone);
		
	}
		
		

	//아이디찾기

	public String findId(int memberNo) {
		
		return MemberDao.getInstance().findId(memberNo);
	}
	
	
	//전체출력
	//전체회원조회
	public ArrayList<MemberDto> Allprint() {
		
		return MemberDao.getInstance().Allprint();
	}

	
	
}//class e
