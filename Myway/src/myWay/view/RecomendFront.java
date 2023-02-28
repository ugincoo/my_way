package myWay.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import myWay.controller.RecomendController;
import myWay.controller.StockController;
import myWay.dto.RecomendDto;

public class RecomendFront {

	// 싱글톤
	private static RecomendFront recomendFront = new RecomendFront();
	private RecomendFront() {}
	public static RecomendFront getInstance() { return recomendFront; }
	
	// 필드
	Scanner scanner = new Scanner(System.in);
	
	// 추천게시판 페이지
	public void recomendpage() {
		while(true) {
			try {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 추천게시판 페이지 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.printf("%2s%10s \t %20s \t %40s \n" , "no" , "제목" , "내용" , "댓글" );
				// 출력
				ArrayList<RecomendDto> result = RecomendController.getInstance().recomendpage();
				for( int i = 0 ; i<result.size(); i++ ) {
					System.out.printf("%2d\t%-10s\t%-40s\t%-3d \n" , result.get(i).getRecomNo() , result.get(i).getRecomTitle() , result.get(i).getRecomContent() , result.get(i).getRecomView() );
				}
				System.out.println("-----------------------------------------------------------------------------------");
				System.out.print("[1]추천메뉴 등록 [2]추천메뉴 수정 [3]추천메뉴 삭제 [4]나가기 : ");
				int ch = scanner.nextInt();
				if( ch == 1 ) { recomendSignup(); }
				else if( ch == 2 ) { recomendUpdate(); }
				else if( ch == 3 ) { recomendDelete(); }
				else if( ch == 4 ) { return; }
			}catch(InputMismatchException e) {
				System.out.println("!잘못된 입력입니다!");
				Scanner scanner = new Scanner(System.in);
			}catch( Exception e ) {
				System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
			}
		}
	}
	
	// 추천메뉴 등록
	public void recomendSignup() {
		scanner.nextLine();
		System.out.print("〖 제목 〗: ");	String recomTitle = scanner.nextLine();
		System.out.print("〖 내용 〗: ");	String recomContent = scanner.nextLine();
		
		// 데이터 전달
		boolean result = RecomendController.getInstance().recomendSignup( recomTitle , recomContent );
		
		if( result ) { System.out.println(" 【 추천메뉴 등록 】 "); }
		else { System.out.println(" 【 추천메뉴 등록 실패 】 "); }
		
	}
	
	// 추천메뉴 수정
	public void recomendUpdate() {
		try {
			System.out.println("〖 변경하실 게시물번호와 구분을 입력해주세요. 〗");
			System.out.println("[1]제목 [2]내용 ");
			System.out.print("- 게시물번호 : ");	int recomNo = scanner.nextInt();
			System.out.print("- 구분 : ");	int	ch = scanner.nextInt();
			scanner.nextLine();
			if( ch == 1 ) {
				System.out.print("- 제목 : ");	String recomTitle = scanner.nextLine();
				RecomendController.getInstance().recomendUpdate(recomNo, "recom_title",recomTitle);
			}
			else if( ch ==2 ) {
				System.out.print("- 내용 : ");	String recomContent = scanner.nextLine();
				RecomendController.getInstance().recomendUpdate(recomNo , "recom_content",recomContent);
			}
			System.out.println(" 【 수정 완료 】 ");
		}catch( InputMismatchException e ) {
			System.out.println("!잘못된 입력입니다!");
			Scanner scanner = new Scanner(System.in);
		}catch( Exception e ) { 
			System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
		}
	}
	
	// 추천메뉴 삭제
	public void recomendDelete() {
		System.out.println("〖 삭제하실 게시물번호를 입력해주세요. 〗");
		int recomNo = scanner.nextInt();
		
		boolean result = RecomendController.getInstance().recomendDelete(recomNo);
		if( result ) { System.out.println(" 【 삭제 완료 】 "); }
		else { System.out.println( " 【 삭제 실패 】 " ); }
	}
	
	
	
	
	
}
