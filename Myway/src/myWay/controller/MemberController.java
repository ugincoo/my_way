package myWay.controller;



import myWay.dao.MemberDao;
import myWay.dto.MemberDto;

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
	private MemberDto logSession = null;	//초기화
	
	MemberDto dto = new MemberDto();
	
	public MemberDto dto() {
		return logSession;
	}
	
	//회원가입
	public boolean signup(String memberId, int memberPw) {
		
		boolean result 
			= MemberDao.getInstance().signup(memberId, memberPw);

		return result;
	}
	
	
	//로그인
	public boolean login(String memberId,int memberPw ) {
		
		 MemberDto result = MemberDao.getInstance().login(memberId,memberPw);
		
		 if(result!=null) {
			 logSession = result;
			 System.out.println(logSession);
			 return true;
		 }
		 return false;
		
	
				
	}
	

	
	
	
}//class e
