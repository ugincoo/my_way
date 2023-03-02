package myWay.view;

import java.util.ArrayList;
import java.util.Scanner;

import myWay.controller.MemberController;
import myWay.dao.MemberDao;
import myWay.dto.MemberDto;

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
			System.out.println("1.회원가입 2.로그인 3.비밀번호수정 4.회원탈퇴 5.아이디찾기 ");
			int ch=scanner.nextInt();
			if(ch==1) {
				signup();
			}else if(ch==2) {
				login();
			}else if(ch==3) {
				update();
			}else if(ch==4) {
				delete();
			}else if(ch==5) {
				findId();
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
			
			System.out.println( MemberController.getInstance().dto().getMemberId()+"님 환영합니다");
			if( MemberController.getInstance().dto().getMemberId().equals("admin") ) {
				StockFront.getInstance().managerpage();
			}else { BoardFront.getInstance().boardIndex();}
		}else if(result==false) {
			System.out.println("아이디와 비밀번호가 일치하지 않습니다");
		}
		
	}
	//비밀번호수정 개어려움 ㅠ 로그인되야 가능하게 설게함 로그인 후 뒤로가기하삼 ㅇㅇ 
	public void update() {
		if(MemberController.getInstance().checkLogin()) {
			System.out.println("--------비밀번호수정-------");
			/*
			 * System.out.print("회원아이디를 입력하세요 ::: "); String memberid=scanner.next();
			 */
			System.out.print("회원비밀번호를 입력하세요 ::: ");
			int memberpassword = scanner.nextInt();
			
			boolean result = MemberController.getInstance().checkPassword(memberpassword);
			
			if (result) {
				int memberNo = MemberController.getInstance().getMemberNo();
				
				// 비밀번호가 세션의 비밀번호와 일치
				System.out.print("새로운 비밀번호를 입력하세요 ::: ");
				 
				int newpassword = scanner.nextInt();
				
				int updateResult = MemberController.getInstance().update(memberNo, newpassword);
				
				if (updateResult == 0) {
					System.out.println("회원의 비밀번호가 수정되었습니다.");
				} else {
					System.out.println("비밀번호 변경 중 오류가 발생하였습니다. 재시도 부탁드립니다.");
				}
			} else {
				System.out.println("세션의 값이 잘못되었습니다.");
			}
		}

		
	}
	
	//아이디찾기
	public void findId() {
		System.out.println("---------아이디찾기-----------");
		System.out.print("회원이름을 입력하세요 : ");
		String membername = scanner.next();
		System.out.print("전화번호를 입력하세요 : ");
		String memberphone = scanner.next();
		
		MemberDto result = MemberController.getInstance().checknamephone(membername,memberphone);
		
		if (result.getMemberId() != null) {
			System.out.println("회원의 아이디는 " + result.getMemberId() + " 입니다.");
		} else {
			System.out.println("입력하신 내용에 해당되는 회원의 정보가 없습니다.");
		}
	}
	
	
	//회원탈퇴
	public void delete() {
		System.out.println("---------회원탈퇴-----------");
		System.out.println("회원아이디를 입력하세요");
		String memberId = scanner.next();
		System.out.println("비밀번호를 입력하세요");
		int memberpw = scanner.nextInt();
		
		
		boolean result = MemberController.getInstance().delete(memberId,memberpw);

		
		if(result) {
			System.out.println("회원탈퇴 성공");
			
		}else  {
			System.out.println("회원탈퇴 실패");
		}
	}
	
	
	
	//전체회원조회
	public void Allprint() {
		System.out.println("전체회원입니다");
		System.out.printf("%3s \t %10s \t %10s \t %10s %10s\n","번호","아이디","비밀번호","휴대폰","이름");
		ArrayList<MemberDto> result = 
				MemberController.getInstance().Allprint();
		
		
		for(int i=0; i<result.size(); i++) {
			System.out.printf("%3s \t %10s \t %10s \t %10s \t %10s \n",
					result.get(i).getMemberNo(),
					result.get(i).getMemberId(),
					result.get(i).getMemberPw(),
					result.get(i).getMemberphone(),
					result.get(i).getMembername());
		}
		
	}
	
	
	
	
	
	
}//class e
