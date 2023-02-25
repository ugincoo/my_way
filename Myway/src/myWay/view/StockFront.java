package myWay.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import myWay.controller.StockController;
import myWay.dao.StockDao;
import myWay.dto.DmaterialDto;

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
				System.out.println("━━━━━━━━━━ 관리자 페이지 ━━━━━━━━━━");
				System.out.print("[1]재고 관리  [2]커뮤니티  [3]나가기 :");
				int ch = scanner.nextInt();
				if( ch == 1) { stockpage(); }
				else if( ch == 2 ) {}
				else if( ch == 3 ) { return; }
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
				// 재료 번호 데이터 전달 및 출력
				ArrayList<DmaterialDto> result = StockController.getInstance().Materials(categoryNo);
				for( int i = 0 ; i<result.size() ; i++ ) {
					System.out.printf("%2s%15s \t %7s \t %5s \n" , result.get(i).getMaterNo() , result.get(i).getMaterName() , result.get(i).getMaterPrice() , result.get(i).getMaterStock() );
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
		System.out.println("〖 제품명 〗: ");	String materName = scanner.next();
		System.out.println("〖 제품 가격 〗: ");	int materprice = scanner.nextInt();
		System.out.println("〖 제품 재고 〗: ");	int materStock = scanner.nextInt();
		
		// 데이터 전달
		 boolean result = StockController.getInstance().materialSignup(categoryNo, materName, materprice, materStock);
		
		 if( result ) { System.out.println(" 【 제품 등록 】 "); }
		 else { System.out.println(" 【 제품 등록 실패 】 "); }
	}
	
	// 재료 수정
	public void materialUpdate( int categoryNo ) {
		try {
			System.out.println("〖 변경하실 재료번호와 구분을 입력해주세요. 〗");
			System.out.println("[1]재료명 [2]재료가격 [3]재고 ");
			System.out.print("- 재료번호 : "); 	int materNo = scanner.nextInt();
			System.out.print("- 구분 : "); 		int ch = scanner.nextInt();
			
			if( ch == 1 ) { // 재료명 수정
				System.out.print("- 변경 내용 : "); 	String materName = scanner.next(); 
				StockController.getInstance().materNameUpdate(materNo, materName);
			}
			else if( ch == 2 ) { // 재료가격 수정 
				System.out.print("- 변경 내용 : "); 	int materPrice = scanner.nextInt();
				StockController.getInstance().materPriceUpdate(materNo, materPrice);
			}
			else if( ch == 3 ) { // 재료 재고 수정
				System.out.print("- 변경 내용 : "); 	int materStock = scanner.nextInt(); 
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
		System.out.println("〖 삭제하실 재료번호를 입력해주세요. 〗");
		int materNo = scanner.nextInt();
		
		boolean result = StockController.getInstance().materialDelete(materNo);
		if( result ) { System.out.println(" 【 삭제 완료 】 "); }
		else { System.out.println( " 【 삭제 실패 】 " ); }
	}
	
	

}












