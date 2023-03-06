package myWay.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import myWay.controller.SalesController;
import myWay.controller.StockController;
import myWay.dao.StockDao;
import myWay.dto.DmaterialDto;
import myWay.dto.SalesDto;

public class StockFront {

	// 싱글톤
	private static StockFront stockFront = new StockFront();
	private StockFront() {}
	public static StockFront getInstance() { return stockFront; }
	
	// 필드
	Scanner scanner = new Scanner(System.in);
	
	// 관리자메인페이지
	public void managerpage() {
		while(true) {
			try {
				//관리자가 로그인하면 먼저 알림창 먼저 
				ArrayList<String> notices = StockController.getInstance().noticeMessageList;

				
				if(notices.size() > 0) {
					System.out.println("━━━━━━━━━━━━━━━━━━━━ 알림창 ["+notices.size()+"] ━━━━━━━━━━━━━━━━━━━━");
					for(int i= 0; i < notices.size(); i++) {
						System.out.println(notices.get(i));
					}
					StockController.getInstance().noticeMessageList.clear(); //모두 출력했으면 지워주기
				}
				
				System.out.println("━━━━━━━━━━━━━━━━━━━━━ 관리자 페이지 ━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("[1]재고 관리  [2]커뮤니티  [3]매출현황  [4]회원조회  [5]나가기 :");
				int ch = scanner.nextInt();
				if( ch == 1) { stockpage(); }
				else if( ch == 2 ) { RecomendFront.getInstance().recomendpage(); }
				else if( ch == 3 ) { printCurrentSales();}
				else if( ch == 4 ) { MemberFront.getInstance().Allprint(); }
				else if( ch == 5 ) { return; }
			}catch( InputMismatchException e ) {
				System.out.println("!잘못된 입력입니다!");
				Scanner scanner = new Scanner(System.in);
			}catch( Exception e ) {
				System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
			}
			
		}
		
	}
	
	// 재고관리페이지
	public void stockpage() {
		while(true) {
			try {
				System.out.println("━━━━━━━━━━━━━ 재고관리 페이지 ━━━━━━━━━━━━━");
				System.out.println("[1]빵 [2]치즈 [3]고기 [4]채소 [5]소스 [6]음료 ");
				System.out.print("카테고리를 입력해주세요 : ");
				int categoryNo = scanner.nextInt();
				
				System.out.println("-------------------- 재고 현황 --------------------");
				System.out.printf("%2s%15s \t %7s \t %5s \n" , "no" , "재료명" , "재료가격" , "재고" );
				// 재료 카테고리 데이터 전달 및 출력
				ArrayList<DmaterialDto> result = StockController.getInstance().Materials(categoryNo);
				for( int i = 0 ; i<result.size() ; i++ ) {
					System.out.printf("%2d%15s \t %7d \t %5d \n" , result.get(i).getMaterNo() , result.get(i).getMaterName() , result.get(i).getMaterPrice() , result.get(i).getMaterStock() );
				}
				System.out.println("-------------------------------------------------");
				System.out.println("[1]재료 등록 [2]재료 수정 [3]재료 삭제 [4]나가기 : ");
				int ch = scanner.nextInt();
				if( ch == 1) { materialSignup( categoryNo ); }
				else if( ch == 2 ) { materialUpdate( categoryNo ); }
				else if( ch == 3 ) { materialDelete();}
				else if( ch == 4 ) { return; }
			}catch( InputMismatchException e ) {
				System.out.println("!잘못된 입력입니다!");
				Scanner scanner = new Scanner(System.in);
			}catch( Exception e ) { 
				System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
			}
		}
	}
	
	// 재료 등록
	public void materialSignup( int categoryNo ) {
		try {
			System.out.print("〖 제품명 〗: ");		String materName = scanner.next();
			System.out.print("〖 제품 가격 〗: ");	int materprice = scanner.nextInt();
			System.out.print("〖 제품 재고 〗: ");	int materStock = scanner.nextInt();
			
			// 데이터 전달
			 boolean result = StockController.getInstance().materialSignup(categoryNo, materName, materprice, materStock);
			
			 if( result ) { System.out.println(" 【 제품 등록 】 "); }
			 else { System.out.println(" 【 제품 등록 실패 】 "); }
		}catch( InputMismatchException e ) {
			System.out.println("!잘못된 입력입니다!");
			Scanner scanner = new Scanner(System.in);
		}catch( Exception e ) { 
			System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
		}
	}
	
	// 재료 수정
	public void materialUpdate( int categoryNo ) {
		try {
			System.out.println("〖 ! 변경하실 재료번호와 구분을 입력해주세요. 〗");
			System.out.println("[1]재료명 [2]재료가격 [3]재고 ");
			System.out.print("- 재료번호 : "); 	int materNo = scanner.nextInt();
			System.out.print("- 구분 : "); 		int ch = scanner.nextInt();
			if( ch == 1 ) { // 재료명 수정
				System.out.print("- 재료명 : "); 	String materName = scanner.next(); 
				StockController.getInstance().materNameUpdate(materNo, materName);
			}
			else if( ch == 2 ) { // 재료가격 수정 
				System.out.print("- 재료가격 : "); 	int materPrice = scanner.nextInt();
				StockController.getInstance().materPriceUpdate(materNo, materPrice);
			}
			else if( ch == 3 ) { // 재료 재고 수정
				System.out.print("- 재고 : "); 	int materStock = scanner.nextInt(); 
				StockController.getInstance().materStockUpdate(materNo, materStock);
			}
			System.out.println(" 【 수정 완료 】 ");
		}catch( InputMismatchException e ) {
			System.out.println("!잘못된 입력입니다!");
			Scanner scanner = new Scanner(System.in);
		}catch( Exception e ) { 
			System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
		}
	}

	// 재료 삭제
	public void materialDelete( ) {
		try {
			System.out.println("〖 삭제하실 재료번호를 입력해주세요. 〗");
			int materNo = scanner.nextInt();
			
			boolean result = StockController.getInstance().materialDelete(materNo);
			if( result ) { System.out.println(" 【 삭제 완료 】 "); }
			else { System.out.println( " 【 삭제 실패 】 " ); }
		}catch( InputMismatchException e ) {
			System.out.println("!잘못된 입력입니다!");
			Scanner scanner = new Scanner(System.in);		
		}catch( Exception e ) { 
			System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
		}
	}
	
	//매출현황
	public void printCurrentSales() {
		try {
			System.out.println();
			SalesFront.getInstance().Calendar( 0 );
			while(true) {
				System.out.println("[-1]이전달 [0]월 매출보기 [1]다음달 [2]일 매출보기 [3]나가기 : ");
				int choice = scanner.nextInt();
				
				if(choice == -1){ // 이전달
					SalesFront.getInstance().Calendar( choice );
				}else if(choice == 0) { //달력에 보여진 월의 매출
					SalesFront.getInstance().printMCurrentSales();
				}else if(choice == 1) {
					SalesFront.getInstance().Calendar( choice );
				}else if(choice == 2) {
					System.out.print("일 입력 : ");
					int selectDay = scanner.nextInt();
					SalesFront.getInstance().printDCurrentSales(selectDay);
				}else if(choice == 3) {
					return;
				}
			}
		}catch( InputMismatchException e ) {
			System.out.println("!잘못된 입력입니다!");
			Scanner scanner = new Scanner(System.in);		
		}catch( Exception e ) { 
			System.out.println("!!프로그램내 오류 발생 : 관리자 문의!!");
		}
		
	}
	
	// 날짜를 문자열로 변환하는 함수 yyyy-mm-dd
	

}












