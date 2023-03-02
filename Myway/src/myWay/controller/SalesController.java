package myWay.controller;

import java.util.ArrayList;
import java.util.Calendar;

import myWay.dao.SalesDao;
import myWay.dto.SalesDto;

public class SalesController {
	
	//싱글톤
	private static SalesController sc = new SalesController();
	public static SalesController getInstance() { return sc; }
	// 생성자
	private SalesController() {}
	
	// 달력 필드
	Calendar cal = Calendar.getInstance();
	int year = cal.get( Calendar.YEAR );
	int month = cal.get( Calendar.MONTH )+1;
	int day = cal.get( Calendar.DAY_OF_MONTH );
		
	// 달력 출력
	public void Calendar( int choice ) {
		
		while(true) {
			
			System.out.printf("=================== %d 년 %d 월 ===================\n" , year , month );
			System.out.println("일\t월\t화\t수\t목\t금\t토");
			cal.set(year, month-1, 1); //  현재 연도/월 의 1일 날짜 형식으로 변경
			int sweek = cal.get( Calendar.DAY_OF_WEEK ); // 해당 월의 시작일
			int eday = cal.getActualMaximum( Calendar.DAY_OF_MONTH ); // 해당 월의 마지막일
			
			for( int i = 1; i<sweek ; i++ ) { System.out.print("\t"); } // 해당 월 1일 전까지 공백으로 채우기
			
			for( int i = 1; i<= eday ; i++ ) {
				System.out.printf("%2d\t" , i);
				if( sweek%7 == 0 ) { System.out.println(); }
				sweek++;
				
			}
			System.out.println("\n===================================================");
			if( choice == -1 ) {
				month--;
				if( month < 1 ) { month = 12; year--; }
			}
			else if( choice == 1 ) {
				month++;
				if( month > 12 ) { month = 1; year++; }
			}
			return;
		}
		
	}
	
	
	// 월 매출 출력 or 일 매출 출력
	public ArrayList<SalesDto> printSales(){
		return SalesDao.getInstance().printSales();
	}
	
}
