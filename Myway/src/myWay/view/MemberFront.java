package myWay.view;

import java.util.Scanner;

import myWay.controller.MemberController;
import myWay.dao.MemberDao;

public class MemberFront {
	Scanner scanner = new Scanner(System.in);
	//싱글톤
	private static MemberFront memberfrot = new MemberFront();
	public MemberFront() {
	}
	public static MemberFront getInstance() {
		return memberfrot;
	}
	
	
	//메인화면: 회원가입, 로그인 메뉴 분기 처리 (1, 2) 
	public void index() {
		while(true) {
			System.out.println("1.회원가입 2.로그인 3.아이디수정 4.회원탈퇴");
			int ch=scanner.nextInt();
			if(ch==1) {
				signup();
			}else if(ch==2) {
				login();
			}else if(ch==3) {
				
			}else if(ch==4) {
				delete();
			}
		}
	}//index e
	
	
	// 회원가입 선택 했을 때 화면
	public void signup() {
		System.out.println("아이디입력하세요");
		String memberId = scanner.next();
		
		System.out.println("비밀번호입력하세요");
		int memberPw = scanner.nextInt();
		
		System.out.println("비밀번호재입력해주세요");
		int confirmed = scanner.nextInt();
		
		System.out.println("핸드폰번호를입력하세요");
		String memberphone = scanner.next();
		
		System.out.println("이름을입력해주세요");
		String membername = scanner.next();
				
		
		if(memberPw != confirmed) {
			System.out.println("비밀번호가일치하지 않습니다");
		}
		
		
		boolean result = 
				MemberController.getInstance().signup(memberId,memberPw,memberphone,membername);
		
		if(result==true) {
			System.out.println("회원가입이 완료되었습니다");
		}else if(result==false) {
			System.out.println("회원가입이 완료되지 않았습니다.");
		}
		
	}//signup e
	

	// 로그인 선택했을 때 화면 --> 추천메뉴(limit) --> 메인페이지 (추후 취합할 예정할 페이지)
	
	public void login() {
		System.out.println("아이디를입력하세요");
		String memberId = scanner.next();
		
		System.out.println("비밀번호를입력하세요");
		int memberPw = scanner.nextInt();
		
		boolean result= 
				MemberController.getInstance().login(memberId,memberPw);
		
		if(result==true) {
			System.out.println("로그인에 성공하였습니다 추천메뉴페이지로 이동");
			if( MemberController.getInstance().dto().getMemberId().equals("admin") ) {
				StockFront.getInstance().managerpage();
			}else { BoardFront.getInstance().boardIndex();}
		}else if(result==false) {
			System.out.println("아이디와 비밀번호가 일치하지 않습니다");
		}
		
	}
	
	//비밀번호수정
	public void update() {
		System.out.println("--------비밀번호수정-------");
		
	}
	
	
	//회원삭제
	public void delete() {
		System.out.println("---------회원삭제-----------");
		System.out.println("회원아이디를 입력하세요");
		String memberId = scanner.next();
		
		boolean result = MemberController.getInstance().delete(memberId);
		
		if(result) {
			System.out.println("회원탈퇴 성공");
		}else {
			System.out.println("회원탈퇴 실패");
		}
	}
	
	
	
	
}//class e
